package presentation.models.messages;
import route.model.Intersection;

import java.util.Map;
import java.util.Set;

public class DrawMessageModel extends EmptyMessageModel {
    private Map<Integer, Intersection> intersections;
    private Set<Intersection> route;

    public DrawMessageModel() {
        setType("drawMap");
    }

    public DrawMessageModel(Map<Integer, Intersection> intersections, Set<Intersection> route) {
        this.intersections = intersections;
        this.route = route;

        setType("drawMap");
    }

    public Map<Integer, Intersection> getIntersections() {
        return intersections;
    }

    public Set<Intersection> getRoute() {
        return route;
    }
}
