package route;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class RouteMap {

    private final Set<Line> lines;
    private final Map<String, Intersection> connections;

    public RouteMap(Set<Line> lines, Map<String, Intersection> connections) {
        this.lines = lines;
        this.connections = connections;
    }

    public Line getLine(String id) {
        return lines.stream()
            .filter(line -> line.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("No line found with ID"));
    }

}