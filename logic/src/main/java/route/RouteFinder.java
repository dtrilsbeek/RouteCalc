package route;

import route.model.Intersection;
import java.util.ArrayList;
import java.util.List;

public class RouteFinder {
    private final RouteMap routeMap;
    private List<Intersection> queue;

    public RouteFinder(RouteMap routeMap) {
        this.routeMap = routeMap;
        this.queue = new ArrayList<>();
    }

    public List<Intersection> findRoute(Intersection from, Intersection to) {
        var resultList = new ArrayList<Intersection>();
        var fromLines = from.getLines();
        var toLines = to.getLines();

        toLines.forEach((value) -> {


                }
        );

        return resultList;
    }
}