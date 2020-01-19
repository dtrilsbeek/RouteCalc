package route.interfaces;

import route.model.Intersection;

import java.util.Map;

public interface IRouteMap {
    Intersection getIntersection(int id);

    void generateRandomIntersections(int amount);

    void generateRandomConnections(int amount);

    int getRandomIntersection(int intersectionId);

    Map<Integer, Intersection> getIntersections();

    void addIntersection(int x, int y);

    void addConnection(int id1, int id2);
}
