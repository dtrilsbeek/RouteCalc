package route;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class RouteMap {
    private final Set<Line> lines;
    private final Map<String, Set<String>> connections;

    public RouteMap(Set<Line> lines, Map<String, Set<String>> connections) {
        this.lines = lines;
        this.connections = connections;
    }

    public Line getLine(String id) {
        return lines.stream()
            .filter(line -> line.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("No node found with ID"));
    }
 
    public Set<Line> getConnections(Line line) {
        return connections.get(line.getId()).stream()
            .map(this::getLine)
            .collect(Collectors.toSet());
    }
}