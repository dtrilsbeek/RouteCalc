package route;

import org.jetbrains.annotations.NotNull;
import route.interfaces.IRouteFinder;
import route.interfaces.IRouteMap;
import route.model.Intersection;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.*;

public class RouteRunner implements Callable<Set<Intersection>> {
    private Set<Intersection> explored;
    private Set<Intersection> queue;
    private IRouteFinder routeFinder;
    private IRouteMap routeMap;
    private Intersection current;

    public RouteRunner(IRouteFinder routeFinder, IRouteMap routeMap, Intersection current, Set<Intersection> explored) {
        this.routeFinder = routeFinder;
        this.routeMap = routeMap;
        this.current = current;
        this.explored = explored;
        this.queue = new HashSet<>();
        checkConnections();
    }

    private void checkConnections() {
        for (Integer id : current.getConnections()) {

            Intersection adjacent = routeMap.getIntersection(id);

            if (explored.contains(adjacent)) {
                continue;
            }

            current.setTotalScore(current.getTotalScore() + adjacent.getScore(current));

            adjacent.setParent(current.getId());
            queue.add(adjacent);
        }
    }

    @Override
    public Set<Intersection> call() throws Exception {
        return queue;
    }
}
