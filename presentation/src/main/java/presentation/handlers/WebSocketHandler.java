package presentation.handlers;

import io.javalin.websocket.WsCloseContext;
import io.javalin.websocket.WsConnectContext;
import io.javalin.websocket.WsMessageContext;
import model.User;
import presentation.UserModule;
import presentation.models.Room;
import presentation.models.UserViewModel;
import presentation.models.messages.*;

import java.util.HashMap;

import static presentation.handlers.ClientMessageHandler.chatMessage;
import static presentation.handlers.ExceptionHandler.wrapException;
import static presentation.handlers.MessageHandler.*;
import static presentation.handlers.ClientMessageHandler.*;
import static presentation.handlers.RoomHandler.*;

public class WebSocketHandler {

    private static int userCount = 1;

    public static void onConnect(WsConnectContext ctx) {
        String roomId = wrapException(() -> ctx.pathParam("id", String.class).getOrNull());

        var room = getRoom(roomId);
        if (room == null) {
            ctx.send(new EmptyMessageModel("no_room"));
            ctx.session.close(4000, "No room found");
            return;
        }

        var username = "User " + userCount;
        var user = new UserViewModel(userCount, username);
        userCount++;
        System.out.println(username);

        room.join(ctx, user);

        if(room.getFinalRoute() == null) {
            broadcastMessageTo(ctx, new DrawMessageModel(room.getIntersections()));
        }
        else {
            broadcastRouteFinder(ctx, room);
        }

        var message = "Welcome "+username+". Send the following url to your friends to " +
                "join you: http://"+ ctx.host()+"/travel/"+roomId;
        broadcastMessageTo(ctx, new SystemMessageModel(message));
        broadcastMessageExcept(ctx, new SystemMessageModel(username+" joined the room"), room);
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

        broadcastMessage(new SystemMessageModel(user.getName() + " left the chat"), room);
        room.leave(ctx);

        if (room.getUserMap().size() < 1) {
            deleteRoom(roomId);
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
}
