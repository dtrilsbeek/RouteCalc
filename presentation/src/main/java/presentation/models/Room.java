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

    private String id;
    private Map<WsContext, User> userMap;
    private RouteMap routeMap;
    private Intersection destination;

    public Room(String id) {
        this.id = id;
        this.userMap = new ConcurrentHashMap<>();
        this.routeMap = new RouteMap(800, 800, 20);

        routeMap.generateRandomIntersections(50);
        routeMap.generateRandomLines(50);
    }

    public Intersection getUserStartPoint(WsContext ctx) {
        return getUser(ctx).getStartPoint();
    }

    public void setUserStartPoint(WsContext ctx, int id) {
        var startPoint = getUserStartPoint(ctx);
        if (startPoint != null) {
            startPoint.setStart(false);
        }
        startPoint = routeMap.getIntersection(id);
         getUser(ctx).setStartPoint(startPoint);
    }

    public Intersection getDestination() {
        return destination;
    }

    public void setDestination(int intersectionId) {
        System.out.println(intersectionId);
        if (this.destination != null) {
            this.destination.setDest(false);
        }
        this.destination = routeMap.getIntersection(intersectionId);
        this.destination.setDest(true);
    }

    public Map<Integer, Intersection> getIntersections() {
        return routeMap.getIntersections();
    }

    public Set<Line> getLines() {
        return routeMap.getLines();
    }

    public String getId() {
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
