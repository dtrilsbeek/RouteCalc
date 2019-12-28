package presentation.models;

import io.javalin.websocket.WsConnectContext;
import io.javalin.websocket.WsContext;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Room {

    private int id;
    private Map<WsContext, User> userMap;

    public Room(int id) {
        this.id = id;
        this.userMap = new ConcurrentHashMap<>();
    }

    public int getId() {
        return id;
    }

    public Map<WsContext, User> getUserMap() {
        return userMap;
    }

    public void join(WsConnectContext ctx, User user) {
        userMap.put(ctx, user);
    }
}
