package route.interfaces;

import route.model.Intersection;

import java.util.Map;

public interface IRouteFinder {
    /**
     * @return All the intersections the algorithm took to find the destination
     */
    Map<Integer, Intersection> getExplored();

    /**
     * @return Only the intersections on the fastest route from start to finish
     */
    Map<Integer, Intersection> getFinalRoute();
}
