package presentation.models;

import io.javalin.websocket.WsConnectContext;
import io.javalin.websocket.WsContext;
import io.javalin.websocket.WsMessageContext;

import route.Intersection;
import route.Line;
import route.RouteMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Room {

    private int id;
    private Map<WsContext, User> userMap;
    private RouteMap routeMap;

    public Room(int id) {
        this.id = id;
        this.userMap = new ConcurrentHashMap<>();
        this.routeMap = new RouteMap(800, 800, 20);

        routeMap.generateRandomIntersections(50);
        routeMap.generateRandomLines(50);
    }

    public Map<Integer, Intersection> getIntersections() {
        return routeMap.getIntersections();
    }

    public Set<Line> getLines() {
        return routeMap.getLines();
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

    public void leave(WsContext ctx){
        userMap.remove(ctx);
    }

    public User getUser(WsContext ctx) {
        return userMap.get(ctx);
    }
}
