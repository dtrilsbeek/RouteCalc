package route;

import route.model.Intersection;
import route.model.Line;

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
        var connections = from.getLines();

        Line lowestNextLine = null;

        for(Line line : connections) {
            if (lowestNextLine != null) {
                if (lowestNextLine.getScore() < line.getScore()) {
                    lowestNextLine = line;
                }
            } else lowestNextLine = line;
        }

        return resultList;
    }
}