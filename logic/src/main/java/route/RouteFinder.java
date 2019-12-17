package route;

import java.util.List;

public class RouteFinder {
    private final RouteMap routeMap;
//    private final Scorer nextNodeScorer;
//    private final Scorer targetScorer;

    public RouteFinder(RouteMap routeMap) {
        this.routeMap = routeMap;
    }

    public List<Intersection> findRoute(Intersection from, Intersection to) {
        throw new IllegalStateException("No route found");
    }
}