package route;

import route.model.Intersection;
import route.model.Line;

import java.util.*;

public class RouteFinder {
    private final RouteMap routeMap;
    private final Intersection from;
    private final Intersection destination;
    private List<Intersection> finalRoute;
    private List<Intersection> checkedIntersections;

    // maybe use PriorityQueue;

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
        finalRoute.add(from);
        sortAndFind(from.getConnections(), null);
    }

    private void sortAndFind(List<Intersection> connections, Intersection prev){
        Collections.sort(connections);

        for(Intersection connection : connections) {
            if(prev != null) {
                if (prev == connection) {
                    continue;
                }
            }
            finalRoute.add(connection);

            sortAndFind(connection.getConnections(), connection);
        }
    }


    public List<Intersection> getFinalRoute() {
        return finalRoute;
    }
}