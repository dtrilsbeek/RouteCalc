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
        routeMap.generateRandomIntersections(500);
        routeMap.generateRandomConnections(750);

        //Act
        var result = false;
        long startTime = System.nanoTime();
        System.out.println(startTime);

        for (int i = 0; i < 200000; i++) {
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
        long avg = milli/2000;

        System.out.println("Average execution time in millis: "+ avg );


        //Assert
        assertFalse(result);
    }
}
