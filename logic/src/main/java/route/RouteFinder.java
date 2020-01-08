package route;

import route.model.Intersection;
import route.model.Line;

import java.util.*;

public class RouteFinder {
    private final RouteMap routeMap;
    private final Intersection from;
    private final Intersection destination;


    private List<Intersection> pathToDestiny;

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
        sortAndFind(from.getLines());
    }

    private void sortAndFind(List<Line> lines){
        Collections.sort(lines);

        for(Line line : lines) {
            var intersection = routeMap.getIntersection(line.getTo());
            pathToDestiny.add(intersection);
            if(destination == intersection) {
                return ;
            }

            var connections = intersection.getLines();

            sortAndFind(connections);
        }
    }


    public List<Intersection> getPathToDestiny() {
        return pathToDestiny;
    }
}