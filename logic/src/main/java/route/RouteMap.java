package route;

import route.model.Intersection;
import route.model.Line;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RouteMap {

    private final int radius;
    private final int boundX;
    private final int boundY;
    private final ThreadLocalRandom random;
    private Set<Line> lines;
    private Map<Integer, Intersection> intersections;

    public RouteMap(int boundX, int boundY, int radius) {
        this.boundX = boundX;
        this.boundY = boundY;
        this.radius = radius;
        this.lines = new HashSet<>();
        this.intersections = new HashMap<>();
        this.random = ThreadLocalRandom.current();
    }

    public Intersection getIntersection(int id) {
        return intersections.get(id);
    }

    public Line getLine(int id) {
        return lines.stream()
                .filter(line -> line.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No line found with ID"));
    }

    public void generateRandomIntersections(int amount) {
        for (int i = 0; i < amount; i++) {
            int random1 = random.nextInt(radius, boundX - radius);
            int random2 = random.nextInt(radius, boundY - radius);
            addIntersection(random1, random2);
        }
    }

    public void generateRandomLines(int amount) {
        var iAmount = intersections.size();

        for (int i = 0; i < amount; ) {
            var intersection1 = intersections.get(random.nextInt(0, iAmount - 1));
            var intersection2 = intersections.get(random.nextInt(0, iAmount - 1));

            try {
                var line = new Line(intersection1, intersection2);
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

    public void addIntersection(int x, int y) {
        var intersection = getNewIntersection(x, y);
        this.intersections.put(intersection.getId(), intersection);
    }

    public void addConnection(int id1, int id2) {
        try {
            var intersection1 = getIntersection(id1);
            var intersection2 = getIntersection(id2);

            intersection1.addConnection(intersection2);
            intersection2.addConnection(intersection1);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addLine(int id1, int id2) {
        try {
            var intersection1 = getIntersection(id1);
            var intersection2 = getIntersection(id2);
            var line = new Line(intersection1, intersection2);
            intersection1.addLine(line);
            intersection2.addLine(line);
            this.lines.add(line);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Intersection getNewIntersection(int x, int y) {
        var id = intersections.size();
        return new Intersection(id, x, y);
    }
}