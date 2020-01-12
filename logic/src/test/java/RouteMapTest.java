import org.junit.jupiter.api.Test;
import route.RouteFinder;
import route.RouteMap;
import route.RouteMapExample;

import static org.junit.jupiter.api.Assertions.*;

public class RouteMapTest {

    @Test
    void testGenerateRandomLines() {
        //Arrange
        var routeMap = new RouteMap(800, 800, 20);

        //Act
        routeMap.generateRandomIntersections(50);
        routeMap.generateRandomLines(50);

        var result = routeMap.getLines().size() > 0;

        var intersections = routeMap.getIntersections();

        intersections.forEach((key, value) -> {
                if (value.getLines().size() > 0) {
                    var i = value;
                }
            }
        );

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
    }
}
