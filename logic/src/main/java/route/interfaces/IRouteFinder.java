package route.interfaces;

import route.model.Intersection;

import java.util.Map;

public interface IRouteFinder {
    void findRoute();

    Map<Integer, Intersection> getExplored();

    Map<Integer, Intersection> getFinalRoute();
}
