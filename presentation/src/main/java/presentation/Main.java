package presentation;

import com.fasterxml.jackson.databind.DeserializationFeature;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.plugin.json.JavalinJackson;
import io.javalin.websocket.WsContext;
import io.javalin.websocket.WsMessageContext;
import presentation.models.Room;
import presentation.models.User;
import presentation.models.messages.EmptyMessageModel;
import presentation.models.messages.HostMessageModel;
import presentation.models.messages.SystemMessageModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {

    private static HashMap<Integer, Room> rooms;
    private static int userCount = 0;

/*
 Road
 Ways
 Route
 Direction
 Track
 Path
 In transit
 Together
 In Sync
 Connected
 */

    public static void main(String[] args) {

        rooms = new HashMap<>();
        rooms.put(1, new Room(1));

        JavalinJackson.configure(JavalinJackson.getObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));

        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("presentation/src/static-dev", Location.EXTERNAL);
        }).start(80);

        app.ws("/travel/:id", ws -> {
            ws.onConnect(ctx -> {
                //https://github.com/eclipse/jetty.project/issues/865

                /*
                I've discovered that jetty rejects (with exception) attempts to close with 'out of scope' codes.
                The only valid close status are 1000-1015 (taken from org.eclipse.jetty.websocket.api.StatusCode ).
                Calling the close (code, reason) with invalid code (For example 5xx),
                the method will throw exception that is subject to be ignored in case you are calling it in a scope of websocket hooks,
                for example onWebSocketConnect. In this case close frame will not be sent and socket will stay open.
                 */
                /*
                Per RFC6455, you can only use those status codes that are allowed to be used over the protocol.
                https://tools.ietf.org/html/rfc6455#section-7.4.2
                If you want to use custom close codes (not the standard ones), you are restricted to the 4000-4999 range.
                 */

                Integer roomId = wrapException(() -> ctx.pathParam("id", Integer.class).getOrNull());
                var room = rooms.get(roomId);
                if (room == null) {
                    ctx.send(new EmptyMessageModel("no_room"));
                    ctx.session.close(4000, "No room found");
                    return;
                }

                var username = "User " + userCount;
                var user = new User(userCount, username);
                userCount++;
                System.out.println(username);

                room.join(ctx, user);
                broadcastMessage(new SystemMessageModel(username + " joined the chat"), room);
            });

            ws.onClose(ctx -> {
                Integer roomId = wrapException(() -> ctx.pathParam("id", Integer.class).getOrNull());

            });

            ws.onMessage(ctx -> {
                Integer roomId = wrapException(() -> ctx.pathParam("id", Integer.class).getOrNull());

                handleMessage(ctx, rooms.get(roomId));
            });
        });

    }

    private static void handleMessage(WsMessageContext ctx, Room room) {
        var message = ctx.message(EmptyMessageModel.class);
//        String sender = room.getPlayer(ctx).getName();

        var stringMessage = ctx.message();
        System.out.println(stringMessage);
        System.out.println(message.getType());

        switch (message.getType()) {
            case "draw":
                break;

        }
    }


    // Sends a message to all users
    private static void broadcastMessage(EmptyMessageModel message) {
        rooms.forEach((key, room) -> room.getUserMap().forEach((ctx, user) -> {
                    if (ctx.session.isOpen()) {
                        ctx.send(message);
                    }
                }
                )
        );
    }

    private static void broadcastMessage(EmptyMessageModel message, Room room) {
        room.getUserMap().forEach((ctx, user) -> {
                    if (ctx.session.isOpen()) {
                        ctx.send(message);
                    }
                }
        );
    }

    // Sends a message from one user to all users except the given user.
    private static void broadcastMessageExcept(EmptyMessageModel message, Room room, WsContext exclude) {
//        room.getUsernameMap().keySet().stream().filter(ctx -> ctx.session.isOpen() && exclude.session != ctx.session).forEach(session -> session.send(message));
    }

    // Sends a message to one user
    private static void broadcastMessageTo(User receiver, EmptyMessageModel message, Room room) {
//        WsContext ctx = getContextFromName(receiver.getName(), room);
//        if(ctx != null) {
//            ctx.send(message);
//        }
    }

    /**
     * Reads given resource file as a string.
     *
     * @param fileName path to the resource file
     * @return the file's contents
     * @throws IOException if read fails for any reason
     */
    private static String getResourceFileAsString(String fileName) throws IOException {
        try (InputStream is = Main.class.getResourceAsStream(fileName)) {
            if (is == null) return null;
            try (InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader reader = new BufferedReader(isr)) {
                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }
    }

    /**
     * wraps a method that can throw an exception
     *
     * @param method method that will be wrapped
     * @return the result of the wrapped value or null if it failed
     */
    private static <T> T wrapException(Supplier<T> method) {
        try {
            return method.get();
        } catch (Exception e) {
            return null;
        }
    }
}