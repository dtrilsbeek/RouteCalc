import org.junit.jupiter.api.Test;
import route.RouteFinder;
import route.RouteMap;
import route.RouteMapExample;
import route.model.Intersection;

import static org.junit.jupiter.api.Assertions.*;

public class RouteMapTest {

    @Test
    void testGenerateRandomLines() {
        //Arrange
        var routeMap = new RouteMap(800, 800, 20);

        //Act
        routeMap.generateRandomIntersections(50);

        var result = routeMap.getIntersections().size() > 0;

        //Assert
        assertTrue(result);
    }

    @Test
    void testFindRoute()
    {
        var routeMap = new RouteMapExample().getRouteMap();
        var from = routeMap.getIntersection(29);
        var to = routeMap.getIntersection(48);
        var routeFinder = new RouteFinder(routeMap, from, to);
        var result = routeFinder.getFinalRoute();

        for (Intersection i : result) {
            System.out.println(i.getId());
        }
    }
}
