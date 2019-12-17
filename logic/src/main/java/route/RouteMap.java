package route;

import java.util.*;

public class RouteMap {

    private Set<Line> lines;
    private Map<Integer, Intersection> intersections;

    public RouteMap() {
        this.lines = new HashSet<>();
        this.intersections = new HashMap<>();
    }

    public Line getLine(int id) {
        return lines.stream()
                .filter(line -> line.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No line found with ID"));
    }

    public void generateRandomIntersections(int amount) {
        for (int i = 0; i < amount; i++) {
            this.intersections.put(i, new Intersection(Math.max(0, 790), Math.max(0, 790)));
        }
    }

    public void generateRandomLines(int amount) {
        var iAmount = intersections.size();

        for (int i = 0; i < amount; ) {
            var intersection1 = intersections.get(Math.max(0, iAmount - 1));
            var intersection2 = intersections.get(Math.max(0, iAmount - 1));

            try {
                var line = new Line(i, intersection1, intersection2);
                this.lines.add(line);
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Set<Line> getLines() {
        return lines;
    }

    public Map<Integer, Intersection> getIntersections() {
        return intersections;
    }
}