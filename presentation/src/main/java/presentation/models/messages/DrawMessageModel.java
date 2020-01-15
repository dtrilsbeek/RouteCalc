package presentation.models.messages;
import route.model.Intersection;

import java.util.Map;

public class DrawMessageModel extends EmptyMessageModel {
    private Map<Integer, Intersection> intersections;
    private Map<Integer, Intersection> route;

    public DrawMessageModel(Map<Integer, Intersection> intersections, Map<Integer, Intersection> route) {
        this.intersections = intersections;
        this.route = route;
        setType("drawMap");
    }

    public Map<Integer, Intersection> getIntersections() {
        return intersections;
    }

    public Map<Integer, Intersection> getRoute() {
        return route;
    }
}
