package presentation.models.messages;
import route.Intersection;
import route.Line;

import java.util.Map;
import java.util.Set;

public class DrawMessageModel extends EmptyMessageModel {
    private Map<Integer, Intersection> intersections;
    private Set<Line> lines;

    public DrawMessageModel() {
        setType("drawMap");
    }

    public DrawMessageModel(Map<Integer, Intersection> intersections, Set<Line> lines) {
        this.intersections = intersections;
        this.lines = lines;

        setType("drawMap");
    }

    public Map<Integer, Intersection> getIntersections() {
        return intersections;
    }

    public Set<Line> getLines() {
        return lines;
    }
}
