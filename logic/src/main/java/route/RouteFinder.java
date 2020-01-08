package route;

import route.model.Intersection;
import route.model.Line;

import java.util.*;

public class RouteFinder {
    private final RouteMap routeMap;
    private final Intersection from;
    private final Intersection destination;


    private List<Intersection> pathToDestiny;
    private List<Intersection> checkedIntersections;

    public RouteFinder(RouteMap routeMap, Intersection from, Intersection destination) {
        this.routeMap = routeMap;
        this.from = from;
        this.destination = destination;
        this.pathToDestiny = new ArrayList<>();
        generateScores();
        findRoute();
    }

    private void generateScores() {
        for (Map.Entry<Integer, Intersection> pair : routeMap.getIntersections().entrySet()) {
            var currentIntersection = pair.getValue();
            currentIntersection.setScore(destination);
        }
    }

    public void findRoute() {
        pathToDestiny.add(from);
        sortAndFind(from.getLines(), null);
    }

    private void sortAndFind(List<Line> lines, Intersection prev){
        Collections.sort(lines);

        for(Line line : lines) {

            var next = routeMap.getIntersection(line.getTo());
            if(prev != null) {
                if (prev.getId() == next.getId()) {
                    next = routeMap.getIntersection(line.getFrom());
                }
            }
            pathToDestiny.add(next);

            var connections = next.getLines();

            sortAndFind(connections, next);
        }
    }


    public List<Intersection> getPathToDestiny() {
        return pathToDestiny;
    }
}