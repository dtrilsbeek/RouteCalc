package presentation.handlers;

import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import presentation.models.Room;

import java.util.HashMap;

public class RoomHandler {


    private static final int MAX_ROOM_NAME_LENGTH = 20;
    private static final int MIN_ROOM_NAME_LENGTH = 2;
    private static HashMap<String, Room> rooms = new HashMap<>();

    public static void getAllRooms(Context ctx) {
        ctx.json(rooms);
    }

    public static HashMap<String, Room> getRooms() {
        return rooms;
    }

    private static Room addRoom(String roomId) {
        var room = new Room(roomId);
        rooms.put(roomId, room);
        return room;
    }

    public static Room getRoom(String id) {
        return rooms.get(id);
    }

    public static void deleteRoom(String id) {
        var room = getRoom(id);

        if (room != null) {
            rooms.remove(id);
            System.out.println("room removed");
        }
    }

    public static void createRoom(Context ctx) {
        String roomId = ctx.formParam("name");
        if (roomId == null) throw new BadRequestResponse("Missing parameter");

        roomId = roomId.trim();
        if(roomId.length() < MIN_ROOM_NAME_LENGTH || roomId.length() > MAX_ROOM_NAME_LENGTH) {
            throw new BadRequestResponse("Invalid room name");
        }

        var room = addRoom(roomId);
        ctx.redirect("/travel/" + room.getId());
    }
}
