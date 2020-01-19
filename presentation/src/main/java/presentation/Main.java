package presentation;

import com.fasterxml.jackson.databind.DeserializationFeature;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.plugin.json.JavalinJackson;
import org.eclipse.jetty.server.session.SessionHandler;
import presentation.handlers.RoomHandler;
import presentation.handlers.TravelHandler;
import presentation.handlers.UserHandler;
import presentation.handlers.WebSocketHandler;

public class Main {

    private static Javalin app;

    public static void main(String[] args) {
        JavalinJackson.configure(JavalinJackson.getObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));

        app = Javalin.create(config -> {
            config.addStaticFiles("presentation/src/main/resources/static-dev", Location.EXTERNAL);
            config.addStaticFiles("static-dev");
        }).start(80);

        app.ws("/travel/:id", ws -> {
            ws.onConnect(WebSocketHandler::onConnect);
            ws.onClose(WebSocketHandler::onClose);
            ws.onMessage(WebSocketHandler::onMessage);
        });

        app.get("/rooms", RoomHandler::getAllRooms);
        app.post("/create-room", RoomHandler::createRoom);
        app.get("/travel/:id", TravelHandler::getTravel);
        app.get("/getUser", UserHandler::getUsername);
        app.post("/register", UserHandler::register);
        app.post("/login", UserHandler::login);
    }

    public static SessionHandler getSessionHandler() {
        return app.config.inner.sessionHandler;
    }
}