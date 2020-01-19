package presentation.models;

import io.javalin.websocket.WsConnectContext;
import io.javalin.websocket.WsContext;

import presentation.models.view.UserViewModel;
import route.RouteFinder;
import route.interfaces.IRouteMap;
import route.model.Intersection;
import route.RouteMapExample;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Room {

    private String id;
    private Map<WsContext, UserViewModel> userMap;
    private IRouteMap routeMap;
    private Intersection destination;
    private Map<Integer, Intersection> finalRoute;
    private Map<Integer, Intersection> explored;
    private Intersection startPoint;

    public Room(String id) {
        this.id = id;
        this.userMap = new ConcurrentHashMap<>();
        this.routeMap = new RouteMapExample().getRouteMap();
    }

    public Intersection getUserStartPoint(WsContext ctx) {
        return getUser(ctx).getStartPoint();
    }

    public void setUserStartPoint(WsContext ctx, int id) {
        if (startPoint != null) {
            startPoint.setStart(false);
        }
        startPoint = routeMap.getIntersection(id);
        startPoint.setStart(true);
    }

    public void setUserStartPointMultiple(WsContext ctx, int id) {
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
        if (this.destination != null) {
            this.destination.setDest(false);
        }
        this.destination = routeMap.getIntersection(intersectionId);
        this.destination.setDest(true);
    }

    public Map<Integer, Intersection> getIntersections() {
        return routeMap.getIntersections();
    }

    public String getId() {
        return id;
    }

    public Map<WsContext, UserViewModel> getUserMap() {
        return userMap;
    }

    public void join(WsConnectContext ctx, UserViewModel user) {
        userMap.put(ctx, user);
    }

    public void leave(WsContext ctx) {
        userMap.remove(ctx);
    }

    public UserViewModel getUser(WsContext ctx) {
        return userMap.get(ctx);
    }

    public boolean findRoute(WsContext ctx) {
        if (destination == null) return false;
        if (startPoint == null) return false;

        var finder = new RouteFinder(routeMap, startPoint, destination);
        finalRoute = finder.getFinalRoute();
        explored = finder.getExplored();
        return true;
    }

    public Map<Integer, Intersection> getFinalRoute() {
        return finalRoute;
    }

    public Map<Integer, Intersection> getExplored() {
        return explored;
    }
}
