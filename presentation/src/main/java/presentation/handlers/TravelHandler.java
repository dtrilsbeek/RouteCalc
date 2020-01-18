package presentation.handlers;

import io.javalin.http.Context;
import java.util.Objects;

import static presentation.handlers.ExceptionHandler.wrapException;
import static presentation.handlers.ResourceHandler.getErrorHtml;
import static presentation.handlers.ResourceHandler.getTravelHtml;
import static presentation.handlers.RoomHandler.getRoom;

public class TravelHandler {

    public static void getTravel(Context ctx) {
        String roomId = wrapException(() -> ctx.pathParam("id", String.class).getOrNull());
        var room = getRoom(roomId);

        ctx.contentType("text/html");
        if (room == null) {
            ctx.result(Objects.requireNonNull(getErrorHtml()));
        }
        else {
            ctx.result(Objects.requireNonNull(getTravelHtml()));
        }
    }
}
