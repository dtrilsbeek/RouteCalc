package route;

import route.model.Intersection;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RouteMap {

    private final int radius;
    private final int boundX;
    private final int boundY;
    private final ThreadLocalRandom random;
    private Map<Integer, Intersection> intersections;

    public RouteMap(int boundX, int boundY, int radius) {
        this.boundX = boundX;
        this.boundY = boundY;
        this.radius = radius;
        this.intersections = new HashMap<>();
        this.random = ThreadLocalRandom.current();
    }

    public Intersection getIntersection(int id) {
        return intersections.get(id);
    }

    public void generateRandomIntersections(int amount) {
        for (int i = 0; i < amount; i++) {
            int random1 = random.nextInt(radius, boundX - radius);
            int random2 = random.nextInt(radius, boundY - radius);
            addIntersection(random1, random2);
        }
    }

    public void generateRandomConnections(int amount) {
        for (int i = 0; i < amount; i++) {
            if(i > intersections.size()-1) {
                var randomNumber = getRandomIntersection(random.nextInt(0, intersections.size() -1));
                addConnection(randomNumber, getRandomIntersection(i));
            }
            else {
                addConnection(i, getRandomIntersection(i));
            }
        }
    }

    public int getRandomIntersection(int intersectionId) {
        int number = random.nextInt(0, intersections.size() -1);
        while (number == intersectionId) {
            number = random.nextInt(0, intersections.size() -1);
        }
        return number;
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

    private Intersection getNewIntersection(int x, int y) {
        var id = intersections.size();
        return new Intersection(id, x, y);
    }
}