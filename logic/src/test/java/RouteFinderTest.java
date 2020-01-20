import org.junit.jupiter.api.Test;
import route.RouteFinder;
import route.RouteMap;
import route.RouteMapExample;
import route.interfaces.IRouteMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RouteFinderTest {

    private IRouteMap routeMapInterface = new RouteMap(800, 800, 20);

    @Test
    void shouldFindRoute_BetweenStartAndDest_ExampleMap()
    {
        //Arrange
        routeMapInterface = new RouteMapExample(routeMapInterface).getRouteMap();
        var from = routeMapInterface.getIntersection(29);
        var to = routeMapInterface.getIntersection(48);

        //Act
        var routeFinder = new RouteFinder(routeMapInterface, from, to);

        //Assert
        var route = routeFinder.getFinalRoute();
        assertFalse(route.isEmpty());
    }

    @Test
    void shouldFindRoute_BetweenStartAndDest_GeneratedMap_25()
    {
        //Arrange
        routeMapInterface.generateRandomIntersections(25);
        routeMapInterface.generateRandomConnections(25);
        var from = routeMapInterface.getIntersection(0);
        var to = routeMapInterface.getIntersection(19);

        //Act
        var routeFinder = new RouteFinder(routeMapInterface, from, to);
        //Assert

        var route = routeFinder.getFinalRoute();
        assertFalse(route.isEmpty());
    }

    @Test
    void shouldFindRoute_BetweenStartAndDest_GeneratedMap_250()
    {
        //Arrange
        routeMapInterface.generateRandomIntersections(200);
        routeMapInterface.generateRandomConnections(200);
        var from = routeMapInterface.getIntersection(0);
        var to = routeMapInterface.getIntersection(19);

        //Act
        var routeFinder = new RouteFinder(routeMapInterface, from, to);
        //Assert

        var route = routeFinder.getFinalRoute();
        assertFalse(route.isEmpty());
    }

    @Test
    void shouldFindRoute_BetweenStartAndDest_GeneratedMap_500()
    {
        //Arrange
        routeMapInterface.generateRandomIntersections(500);
        routeMapInterface.generateRandomConnections(500);
        var from = routeMapInterface.getIntersection(0);
        var to = routeMapInterface.getIntersection(19);

        //Act
        var routeFinder = new RouteFinder(routeMapInterface, from, to);
        //Assert

        var route = routeFinder.getFinalRoute();
        assertFalse(route.isEmpty());
    }

    @Test
    void shouldFindRoute_BetweenStartAndDest_GeneratedMap_1000()
    {
        //Arrange
        routeMapInterface.generateRandomIntersections(1000);
        routeMapInterface.generateRandomConnections(1000);
        var from = routeMapInterface.getIntersection(0);
        var to = routeMapInterface.getIntersection(19);

        //Act
        var routeFinder = new RouteFinder(routeMapInterface, from, to);
        //Assert

        var route = routeFinder.getFinalRoute();
        assertFalse(route.isEmpty());
    }

    @Test
    void shouldFindRoute_BetweenStartAndDest_GeneratedMap_5000()
    {
        //Arrange
        routeMapInterface.generateRandomIntersections(300);
        routeMapInterface.generateRandomConnections(600);

        //Act
        var result = false;
        long startTime = System.nanoTime();
        System.out.println(startTime);

        for (int i = 0; i < 200; i++) {
            var from = routeMapInterface.getIntersection(0);
            var to = routeMapInterface.getIntersection(routeMapInterface.getRandomIntersection(0));
            var routeFinder = new RouteFinder(routeMapInterface, from, to);

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
