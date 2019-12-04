package presentation;

import com.fasterxml.jackson.databind.DeserializationFeature;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.plugin.json.JavalinJackson;
import io.javalin.websocket.WsContext;
import io.javalin.websocket.WsMessageContext;
import logic.Player;
import logic.room.Room;
import presentation.models.messages.DrawMessageModel;
import presentation.models.messages.EmptyMessageModel;
import presentation.models.messages.FillMessageModel;
import presentation.models.messages.UserMessageModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

//        String homeHtml = getResourceFileAsString("/web/home.html");

        JavalinJackson.configure(JavalinJackson.getObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));

        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("presentation/src/static-dev", Location.EXTERNAL);
        }).start(80);


        app.ws("/chat/:id", ws -> {
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

            });

            ws.onClose(ctx -> {
                Integer roomId = wrapException(() -> ctx.pathParam("id", Integer.class).getOrNull());

            });

            ws.onMessage(ctx -> {
                Integer roomId = wrapException(() -> ctx.pathParam("id", Integer.class).getOrNull());

                handleMessage(ctx, room);
            });
        });

    }

    private static void handleMessage(WsMessageContext ctx, Room room) {
        var message = ctx.message(EmptyMessageModel.class);
        String sender = room.getPlayer(ctx).getName();

        switch (message.getType()){
            case "draw":
                break;

        }
    }


    // Sends a message from one user to all users
    private static void broadcastMessage(EmptyMessageModel message, Room room) {
        room.getUsernameMap().keySet().stream().filter(ctx -> ctx.session.isOpen()).forEach(session -> session.send(message));
    }
    // Sends a message from one user to all users except the given user.
    private static void broadcastMessageExcept(EmptyMessageModel message, Room room, WsContext exclude) {
        room.getUsernameMap().keySet().stream().filter(ctx -> ctx.session.isOpen() && exclude.session != ctx.session).forEach(session -> session.send(message));
    }
    // Sends a message to one user
    private static void broadcastMessageTo(Player receiver, EmptyMessageModel message, Room room) {
        WsContext ctx = getContextFromName(receiver.getName(), room);
        if(ctx != null) {
            ctx.send(message);
        }
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
     * @param  method method that will be wrapped
     * @return the result of the wrapped value or null if it failed
     */
    private static <T> T wrapException(Supplier<T> method) {
        try {
            return  method.get();
        }
        catch (Exception e) {
            return null;
        }
    }
}