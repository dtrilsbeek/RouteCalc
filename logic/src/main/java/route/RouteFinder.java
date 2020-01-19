package route;

import route.interfaces.IRouteFinder;
import route.interfaces.IRouteMap;
import route.model.Intersection;
import java.util.*;
import java.util.stream.Collectors;

public class RouteFinder implements IRouteFinder {
    private final IRouteMap routeMapInterface;
    private final Intersection from;
    private final Intersection destination;
    private Map<Integer, Intersection> finalRoute;
    Set<Intersection> explored = new HashSet<>();
    PriorityQueue<Intersection> queue = new PriorityQueue<>(20,
            (i, j) -> Double.compare(i.getScore(j), j.getScore(i))
    );

    public RouteFinder(IRouteMap routeMapInterface, Intersection from, Intersection destination) {
        this.routeMapInterface = routeMapInterface;
        this.from = from;
        this.destination = destination;
        this.finalRoute = new HashMap<>();
        generateScores();
        findRoute();
    }

    private void generateScores() {
        for (Map.Entry<Integer, Intersection> pair : routeMapInterface.getIntersections().entrySet()) {
            var currentIntersection = pair.getValue();
            currentIntersection.setLengthToDest(destination);
        }
    }

    private void findRoute() {
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

                Intersection adjacent = routeMapInterface.getIntersection(id);

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

    @Override
    public Map<Integer, Intersection> getExplored() {
        return explored.stream().collect(Collectors.toMap(Intersection::getNth, e -> e));
    }

    @Override
    public Map<Integer, Intersection> getFinalRoute() {
        var i = 0;
        var current = destination;
        while(current != from) {
            if (current == null) break;

            finalRoute.put(i, current);
            var parentId = current.getParent();

            current = routeMapInterface.getIntersection(parentId);
            i++;
        }
        finalRoute.put(i, from);

        return finalRoute;
    }
}