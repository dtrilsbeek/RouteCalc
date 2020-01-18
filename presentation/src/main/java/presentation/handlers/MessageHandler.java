package presentation.handlers;

import io.javalin.websocket.WsContext;
import presentation.models.Room;
import presentation.models.messages.DrawMessageModel;
import presentation.models.messages.EmptyMessageModel;
import static presentation.handlers.RoomHandler.getRooms;

public class MessageHandler {

    public static void broadcastMessage(EmptyMessageModel message) {
        getRooms().forEach((key, room) -> room.getUserMap().forEach((ctx, user) -> {
                    if (ctx.session.isOpen()) {
                        ctx.send(message);
                    }
                }
            )
        );
    }

    public static void broadcastMessage(EmptyMessageModel message, Room room) {
        room.getUserMap().forEach((ctx, user) -> {
                    if (ctx.session.isOpen()) {
                        ctx.send(message);
                    }
                }
        );
    }

    public static void broadcastMessageExcept(WsContext exclude, EmptyMessageModel message, Room room) {
        room.getUserMap().keySet().stream().filter(ctx -> ctx.session.isOpen() && exclude.session != ctx.session).forEach(session -> session.send(message));
    }

    public static void broadcastMessageTo(WsContext ctx, EmptyMessageModel message) {
        if (ctx != null) {
            if (ctx.session.isOpen()) {
                ctx.send(message);
            }
        }
    }

    public static void broadcastRouteFinder(WsContext ctx, Room room){
        if (room.findRoute(ctx)) {
            broadcastMessage(new DrawMessageModel(room.getIntersections(), room.getFinalRoute(), room.getExplored()), room);
        }
        else {
            broadcastMessage(new DrawMessageModel(room.getIntersections()), room);
        }
    }

}
