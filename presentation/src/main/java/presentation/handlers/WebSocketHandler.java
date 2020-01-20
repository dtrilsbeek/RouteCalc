package presentation.handlers;

import org.eclipse.jetty.server.session.SessionHandler;
import io.javalin.websocket.WsCloseContext;
import io.javalin.websocket.WsConnectContext;
import io.javalin.websocket.WsMessageContext;
import presentation.models.Room;
import presentation.models.view.UserViewModel;
import presentation.models.messages.*;

import static presentation.Main.getSessionHandler;
import static presentation.handlers.ClientMessageHandler.chatMessage;
import static presentation.handlers.ExceptionHandler.wrapException;
import static presentation.handlers.MessageHandler.*;
import static presentation.handlers.ClientMessageHandler.*;
import static presentation.handlers.RoomHandler.*;

public class WebSocketHandler {

    private static int userCount = 1;
    private static SessionHandler sessionHandler = getSessionHandler();

    private static Integer getUserId(WsConnectContext ctx) {
        var userIdString = ctx.cookie("userId");
        if ( userIdString != null ) {
            return Integer.parseInt(userIdString);
        }

        return null;
    }

    public static void onConnect(WsConnectContext ctx) {
        String roomId = wrapException(() -> ctx.pathParam("id", String.class).getOrNull());
        Integer userId = getUserId(ctx);

        var room = RoomHandler.getRoom(roomId);
        if (room == null) {
            ctx.send(new EmptyMessageModel("no_room"));
            ctx.session.close(4000, "No room found");
            return;
        }

        String username;
        if (userId == null) {
            username = getGuestUsername();
        }
        else {
            username = getUsername(userId);
        }

        var user = new UserViewModel(userCount, username);
        System.out.println(username);

        room.join(ctx, user);
        if (room.getFinalRoute() == null) {
            MessageHandler.broadcastMessageTo(ctx, new DrawMessageModel(room.getIntersections()));
        } else {
            MessageHandler.broadcastRouteFinder(ctx, room);
        }

        var message = "Welcome " + username + ". Send the following url to your friends to " +
                "join you: http://" + ctx.host() + "/travel/" + roomId;
        broadcastMessageTo(ctx, new SystemMessageModel(message));
        broadcastMessageExcept(ctx, new SystemMessageModel(username + " joined the room"), room);
    }

    public static void onMessage(WsMessageContext ctx) {
        String roomId = wrapException(() -> ctx.pathParam("id", String.class).getOrNull());
        var room = getRoom(roomId);
        if (room == null) return;

        handleClientMessage(ctx, room);
    }

    public static void onClose(WsCloseContext ctx) {
        String roomId = wrapException(() -> ctx.pathParam("id", String.class).getOrNull());
        var room = getRoom(roomId);
        if (room == null) return;
        var user = room.getUser(ctx);
        if (user == null) return;

        MessageHandler.broadcastMessage(new SystemMessageModel(user.getName() + " left the chat"), room);
        room.leave(ctx);

        if (room.getUserMap().size() < 1) {
            RoomHandler.deleteRoom(roomId);
        }
    }

    private static void handleClientMessage(WsMessageContext ctx, Room room) {
        var message = ctx.message(EmptyMessageModel.class);

        switch (message.getType()) {
            case "PULSE":
                pulseMessage(ctx);
                break;

            case "START":
                setStartPointMessage(ctx, room);
                break;

            case "DEST":
                setDestinationMessage(ctx, room);
                break;

            case "CHAT":
                chatMessage(ctx, room);
                break;

        }
    }

    private static String getUsernameSession(WsConnectContext ctx) {
        var session = sessionHandler.getHttpSession(ctx.getSessionId());

        return (String) session.getAttribute("username");
    }

    private static String getUsername(Integer userId) {
        var user = UserHandler.getUser(userId);

        if (user != null) {
            return user.getName();
        }

        return getGuestUsername();
    }

    private static String getGuestUsername() {
        var username = "User " + userCount;
        userCount++;

        return username;
    }
}
