import org.junit.jupiter.api.Test;
import route.ExampleMapReal;
import route.RouteFinder;
import route.RouteMap;
import route.interfaces.IRouteMap;
import route.interfaces.IRouteMapExample;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RouteFinderSpeedRandomTest {

    private IRouteMap routeMap = new RouteMap(800, 800, 20);
    private IRouteMapExample routeMapExample;

    @Test
    void shouldFindRoute_BetweenStartAndDest_ExampleMap()
    {
        //Arrange
        routeMap = new ExampleMapReal(routeMap).getRouteMap();
        var from = routeMap.getIntersection(29);
        var to = routeMap.getIntersection(48);

        //Act
        var routeFinder = new RouteFinder(routeMap, from, to);

        //Assert
        var route = routeFinder.getFinalRoute();
        assertFalse(route.isEmpty());
    }

    @Test
    void shouldFindRoute_BetweenStartAndDest_GeneratedMap_25()
    {
        //Arrange
        routeMap.generateRandomIntersections(25);
        routeMap.generateRandomConnections(25);
        var from = routeMap.getIntersection(0);
        var to = routeMap.getIntersection(19);

        //Act
        var routeFinder = new RouteFinder(routeMap, from, to);

        //Assert
        var route = routeFinder.getFinalRoute();
        assertFalse(route.isEmpty());
    }

    @Test
    void shouldFindRoute_BetweenStartAndDest_GeneratedMap_250()
    {
        //Arrange
        routeMap.generateRandomIntersections(200);
        routeMap.generateRandomConnections(200);
        var from = routeMap.getIntersection(0);
        var to = routeMap.getIntersection(19);

        //Act
        var routeFinder = new RouteFinder(routeMap, from, to);

        //Assert
        var route = routeFinder.getFinalRoute();
        assertFalse(route.isEmpty());
    }

    @Test
    void shouldFindRoute_BetweenStartAndDest_GeneratedMap_500()
    {
        //Arrange
        routeMap.generateRandomIntersections(500);
        routeMap.generateRandomConnections(500);
        var from = routeMap.getIntersection(0);
        var to = routeMap.getIntersection(19);

        //Act
        var routeFinder = new RouteFinder(routeMap, from, to);

        //Assert
        var route = routeFinder.getFinalRoute();
        assertFalse(route.isEmpty());
    }

    @Test
    void shouldFindRoute_BetweenStartAndDest_GeneratedMap_1000()
    {
        //Arrange
        routeMap.generateRandomIntersections(1000);
        routeMap.generateRandomConnections(1000);
        var from = routeMap.getIntersection(0);
        var to = routeMap.getIntersection(19);

        //Act
        var routeFinder = new RouteFinder(routeMap, from, to);

        //Assert
        var route = routeFinder.getFinalRoute();
        assertFalse(route.isEmpty());
    }

    @Test
    void shouldFindRoute_BetweenStartAndDest_GeneratedMap_5000()
    {
        //Arrange
        routeMap.generateRandomIntersections(5000);
        routeMap.generateRandomConnections(5000);

        //Act
        var result = false;
        long startTime = System.nanoTime();
        System.out.println(startTime);

        for (int i = 0; i < 200; i++) {
            var from = routeMap.getIntersection(0);
            var to = routeMap.getIntersection(routeMap.getRandomIntersection(0));
            var routeFinder = new RouteFinder(routeMap, from, to);

            var route = routeFinder.getFinalRoute();

            if (!route.isEmpty()) {
                result = true;
            }

        }

        long elapsedTime = System.nanoTime() - startTime;
        long milli = (elapsedTime/1000000);
        double avg = (double) milli/200000;

        System.out.println("Average execution time in millis: "+ avg );

        //Assert
        assertTrue(result);
    }
}
