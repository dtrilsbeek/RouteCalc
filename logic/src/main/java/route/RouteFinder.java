package route;

import route.model.Intersection;
import java.util.*;
import java.util.stream.Collectors;

public class RouteFinder {
    private final RouteMap routeMap;
    private final Intersection from;
    private final Intersection destination;
    private Map<Integer, Intersection> finalRoute;
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
        this.finalRoute = new HashMap<>();
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

        int i = 0;
        boolean found = false;
        while ((!queue.isEmpty()) && (!found)) {

            Intersection current = queue.poll();
            explored.add(current);

            if (current.getId() == destination.getId()) {
                found = true;
            }

            current.setNth(i);
            i++;


            from.setTotalScore(from.getTotalScore() + current.getScore(destination));
            current.setTotalScore(from.getTotalScore());

            for (Integer id : current.getConnections()) {

                Intersection adjacent = routeMap.getIntersection(id);

                if (explored.contains(adjacent)) {
                    continue;
                }


                current.setTotalScore(current.getTotalScore() + adjacent.getScore(current));

                queue.remove(current);
                adjacent.setParent(current.getId());
                queue.add(adjacent);
            }

        }
    }

    public Map<Integer, Intersection> getExplored() {
        return explored.stream().collect(Collectors.toMap(Intersection::getNth, e -> e));
    }

    public Map<Integer, Intersection> getFinalRoute() {
        var i = 0;
        var current = destination;
        while(current != from) {
            finalRoute.put(i, current);
            var parentId = current.getParent();

            current = routeMap.getIntersection(parentId);
            i++;
        }
        finalRoute.put(i, from);

        return finalRoute;
    }
}