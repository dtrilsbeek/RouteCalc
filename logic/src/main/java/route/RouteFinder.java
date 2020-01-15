package route;

import route.model.Intersection;
import java.util.*;

public class RouteFinder {
    private final RouteMap routeMap;
    private final Intersection from;
    private final Intersection destination;
    private Set<Intersection> finalRoute;
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
        this.finalRoute = new HashSet<>();
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


    public Set<Intersection> getFinalRoute() {
        return getFinalShortestRoute();
//        return explored;
    }

    public Set<Intersection> getFinalShortestRoute() {
        //ArrayList<Intersection> list = new ArrayList<>(explored);
        //list.sort(Collections.reverseOrder());

        var current = destination;
        while(current != from) {

            finalRoute.add(current);
            var parentId = current.getParent();

            current = routeMap.getIntersection(parentId);
        }
        finalRoute.add(from);

        return finalRoute;
    }

/*    public Set<Intersection> getFinalShortestRoute() {
        ArrayList<Intersection> list = new ArrayList<>(explored);
        Collections.sort(list);
        list.sort(Collections.reverseOrder());

        Integer prev = null;
        for (Intersection intersection: list) {

            if (prev == null) {
                finalRoute.add(intersection);
                prev = intersection.getNth();
            }

            var connections = intersection.getConnections();
            for(Integer i : connections) {
                var current = routeMap.getIntersection(i);
                if(explored.contains(current)) {
                    var nth = current.getNth();
                    if(nth < prev) {
                        finalRoute.add(intersection);
                        prev = nth;
                    }
                }
            }

            if(intersection == from) finalRoute.add(intersection);
        }

        return finalRoute;
    }*/

    /* public Set<Intersection> getFinalShortestRoute() {
        ArrayList<Intersection> list = new ArrayList<>(explored);
        Collections.sort(list);
        list.sort(Collections.reverseOrder());

        Integer prevScore = null;
        for (Intersection intersection: list) {

            var score = intersection.getTotalScore();

            if (intersection.isDest())
            {
                finalRoute.add(intersection);
                continue;
            }
            if(prevScore == null) {
                prevScore = score;
                finalRoute.add(intersection);
                continue;
            }

            if (score < prevScore) {
                finalRoute.add(intersection);
                prevScore = score;
            }

            if(intersection == from) break;
        }

        return finalRoute;
    }*/
}