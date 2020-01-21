package route;

import route.interfaces.IRouteMap;
import route.model.Intersection;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RouteMap implements IRouteMap {

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

    @Override
    public Intersection getIntersection(int id) {
        return intersections.get(id);
    }

    @Override
    public void generateSquareMap(int size) {
        var steps = radius + 10;
        var x = 100;
        var y = 80;

        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {
                addIntersection(x, y);

                x = x + steps;
            }
            x = 100;

            y = y + steps;
        }

        setSquareMapConnections(size);
    }

    private void setSquareMapConnections(int size) {
        var x = 0;
        var y = 0;

        for (int i = 0; i < size; i++) {
            boolean isBottom = i == size - 1;

            for (int j = 0; j < size; j++) {
                boolean isRightEdge = j == size - 1;
                int from = x + (y * size);

                Integer toRight = null;
                if (!isRightEdge) {
                    toRight = (x + 1) + (y * size);
                }

                Integer toBottom = null;
                if (!isBottom) {
                    toBottom = x + ((y + 1) * size);
                }

                if (toRight != null) {
                    addConnection(from, toRight);
                }
                if (toBottom != null) {
                    addConnection(from, toBottom);
                }

                x++;
            }
            x = 0;
            y++;
        }
    }


    @Override
    public void generateRandomIntersections(int amount) {
        for (int i = 0; i < amount; i++) {
            int random1 = random.nextInt(radius, boundX - radius);
            int random2 = random.nextInt(radius, boundY - radius);
            addIntersection(random1, random2);
        }
    }

    @Override
    public void generateRandomConnections(int amount) {
        for (int i = 0; i < amount; i++) {
            if (i > intersections.size() - 1) {
                var randomNumber = getRandomIntersection(random.nextInt(0, intersections.size() - 1));
                addConnection(randomNumber, getRandomIntersection(i));
            } else {
                addConnection(i, getRandomIntersection(i));
            }
        }
    }

    @Override
    public int getRandomIntersection(int intersectionId) {
        int number = random.nextInt(0, intersections.size() - 1);
        while (number == intersectionId) {
            number = random.nextInt(0, intersections.size() - 1);
        }
        return number;
    }

    @Override
    public Map<Integer, Intersection> getIntersections() {
        return intersections;
    }

    @Override
    public void addIntersection(int x, int y) {
        var intersection = getNewIntersection(x, y);
        this.intersections.put(intersection.getId(), intersection);
    }

    @Override
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