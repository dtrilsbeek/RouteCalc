package route;

import route.model.Intersection;
import java.util.*;

public class RouteFinder {
    private final RouteMap routeMap;
    private final Intersection from;
    private final Intersection destination;
    private List<Intersection> finalRoute;
    Set<Intersection> explored = new HashSet<>();

    PriorityQueue<Intersection> queue = new PriorityQueue<>(20,
            new Comparator<Intersection>() {
                //override compare method
                public int compare(Intersection i, Intersection j) {
                    if (i.getScore(j) > j.getScore(i)) {
                        return 1;
                    } else if (i.getScore(j) < j.getScore(i)) {
                        return -1;
                    } else {
                        return 0;
                    }
                }

            }
    );

    public RouteFinder(RouteMap routeMap, Intersection from, Intersection destination) {
        this.routeMap = routeMap;
        this.from = from;
        this.destination = destination;
        this.finalRoute = new ArrayList<>();
        generateScores();
        findRoute();
    }

    private void generateScores() {
        for (Map.Entry<Integer, Intersection> pair : routeMap.getIntersections().entrySet()) {
            var currentIntersection = pair.getValue();
            currentIntersection.setLengthToDest(destination);
        }
    }

    public void findRoute() {
        from.setTotalScore(0);
        queue.add(from);

        boolean found = false;

        while ((!queue.isEmpty()) && (!found)) {

            Intersection current = queue.poll();
            explored.add(current);

            if (current.getId() == destination.getId()) {
                found = true;
            }

            for (Integer id : current.getConnections()) {

                Intersection adjacent = routeMap.getIntersection(id);
                var tempTotal = from.getTotalScore() + adjacent.getScore(current);

                if (explored.contains(adjacent)) {
                    continue;
                }

                queue.remove(current);
                queue.add(adjacent);
            }

        }
        var result = explored;
    }


    public Set<Intersection> getFinalRoute() {
        return this.explored;
    }
}