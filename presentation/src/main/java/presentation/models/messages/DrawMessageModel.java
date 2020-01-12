package presentation.models.messages;
import route.model.Intersection;
import route.model.Line;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class DrawMessageModel extends EmptyMessageModel {
    private Map<Integer, Intersection> intersections;
    private List<Intersection> route;

    public DrawMessageModel() {
        setType("drawMap");
    }

    public DrawMessageModel(Map<Integer, Intersection> intersections, List<Intersection> route) {
        this.intersections = intersections;
        this.route = route;

        setType("drawMap");
    }

    public Map<Integer, Intersection> getIntersections() {
        return intersections;
    }

    public List<Intersection> getRoute() {
        return route;
    }
}
