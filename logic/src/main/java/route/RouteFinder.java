package route;

import route.interfaces.IRouteFinder;
import route.interfaces.IRouteMap;
import route.model.Intersection;
import route.timeutil.TimeStamp;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class RouteFinder implements IRouteFinder {

    private final IRouteMap routeMap;
    private final Intersection from;
    private final Intersection destination;
    private TimeStamp tsCalc;
    private Map<Integer, Intersection> finalRoute;
    private Set<Intersection> explored;
    private PriorityQueue<Intersection> queue;
    private ExecutorService threadPool;
    private List<RouteRunner> runners;
    private int runnerCount;
    private AtomicInteger finishCount;

    public RouteFinder(IRouteMap routeMap, Intersection from, Intersection destination) {
        this.routeMap = routeMap;
        this.from = from;
        this.destination = destination;
        this.tsCalc = new TimeStamp();
        this.finalRoute = new HashMap<>();
        this.explored = new HashSet<>();
        this.queue = new PriorityQueue<>(20,
            (i, j) -> Double.compare(i.getScore(j), j.getScore(i))
        );

        this.threadPool = Executors.newCachedThreadPool();
        this.runners = new ArrayList<>();
        this.runnerCount = 0;
        this.finishCount = new AtomicInteger();
        init();
        generateScores();
        findRoute();
    }

    private void init() {
        tsCalc.init();
        tsCalc.setBegin("Begin calculating");
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

            runnerCount++;

            var runner = new RouteRunner(this, routeMap, current, explored);
            Future<Set<Intersection>> connections = this.threadPool.submit(runner);

            try {
                queue.addAll(connections.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private void mergeLists() {
    }

    public void runnerFinished() {
        if(finishCount.incrementAndGet() == runnerCount) {
            mergeLists();
        }
    }

    private void generateScores() {
        for (Map.Entry<Integer, Intersection> pair : routeMap.getIntersections().entrySet()) {
            var currentIntersection = pair.getValue();
            currentIntersection.setLengthToDest(destination);
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

            current = routeMap.getIntersection(parentId);
            i++;
        }
        finalRoute.put(i, from);

        return finalRoute;
    }
}