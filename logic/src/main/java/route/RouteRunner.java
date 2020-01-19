package route;

import route.interfaces.IRouteFinder;
import route.model.Intersection;

import java.util.HashSet;

public class RouteRunner {
    private final HashSet<Intersection> explored;
    IRouteFinder routeFinder;
    Intersection current;


    public RouteRunner(IRouteFinder routeFinder, Intersection current) {
        this.routeFinder = routeFinder;
        this.current = current;
        this.explored = new HashSet<>();
    }

    private void checkConnections() {
        for (Integer id : current.getConnections()) {

            Intersection adjacent = routeFinder.getIntersection(id);

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
