package route.interfaces;

import route.model.Intersection;

import java.util.Map;

public interface IRouteMap {
    Intersection getIntersection(int id);

    /**
     * This function can be used to easily generate a lot of intersections. This is useful when testing the speed of
     * path finding algorithm
     *
     * @param amount The amount of intersections to be generated
     */
    void generateRandomIntersections(int amount);

    /**
     * This function can be used to easily generate a lot of connections. Generate the intersections first! Else there
     * wont be any connections possible.
     *
     * @param amount The amount of connections to be generated
     */
    void generateRandomConnections(int amount);


    /**
     * @param intersectionId provide an intersection id so the returned id will not be the same as the given.
     *
     * @return A random intersection id from the list of intersections.
     */
    int getRandomIntersection(int intersectionId);

    /**
     * @return Get all intersections on this map.
     */
    Map<Integer, Intersection> getIntersections();

    /**
     * Add an intersection to the map.
     *
     * @param x Horizontal position of the intersection
     * @param y Vertical position of the intersection
     */
    void addIntersection(int x, int y);

    /**
     * @param id1 The id of the first intersection
     * @param id2 The id of the second intersection
     */
    void addConnection(int id1, int id2);
}
