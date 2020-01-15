import org.junit.jupiter.api.Test;
import route.RouteFinder;
import route.RouteMap;
import route.RouteMapExample;

import java.util.Timer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RouteFinderTest {

    private RouteMap routeMap = new RouteMap(800, 800, 20);

    @Test
    void shouldFindRoute_BetweenStartAndDest_ExampleMap()
    {
        //Arrange
        routeMap = new RouteMapExample(routeMap).getRouteMap();
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
        routeMap.generateRandomIntersections(2500);
        routeMap.generateRandomConnections(5000);

        //Act
        var result = false;
        long startTime = System.nanoTime();

        for (int i = 0; i < 2; i++) {
            var from = routeMap.getIntersection(i);
            var to = routeMap.getIntersection(50 + i);
            var routeFinder = new RouteFinder(routeMap, from, to);

            var route = routeFinder.getFinalRoute();

            if (!route.isEmpty()) {
                result = true;
            }

        }

        long elapsedTime = System.nanoTime() - startTime;

        System.out.println("Total execution time to create 1000K objects in Java in millis: "
                + elapsedTime/1000000);


        //Assert
        assertFalse(result);
    }
}
