package presentation.models;

import io.javalin.websocket.WsContext;

import java.util.HashMap;
import java.util.Map;

public class Room {

    private int id;
    private Map<WsContext, User> userMap;

    public Room(int id) {
        this.id = id;
        this.userMap = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public Map<WsContext, User> getUserMap() {
        return userMap;
    }

}
