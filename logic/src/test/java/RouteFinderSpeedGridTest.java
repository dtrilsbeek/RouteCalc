import org.junit.jupiter.api.Test;
import route.ExampleMapReal;
import route.ExampleMapSquare;
import route.RouteFinder;
import route.RouteMap;
import route.interfaces.IRouteMap;
import route.interfaces.IRouteMapExample;
import route.util.TimeStamp;

import java.security.Timestamp;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RouteFinderSpeedGridTest {

    private IRouteMap routeMap = new RouteMap(800, 800, 20);
    private IRouteMapExample routeMapExample;
    private TimeStamp timeStamp = new TimeStamp();

    @Test
    void shouldFindRoute_BetweenStartAndDest_ExampleMapSquare_10()
    {
        //Arrange
        int columnWidth = 10;
        routeMapExample = new ExampleMapSquare(routeMap, columnWidth);

        var from = routeMap.getIntersection(0);
        var to = routeMap.getIntersection(columnWidth * columnWidth -1);

        System.out.println("start");
        timeStamp.setBegin("Start Finding Route, GridWidth: "+columnWidth);
        //Act
        var routeFinder = new RouteFinder(routeMap, from, to);

        //Assert
        var route = routeFinder.getFinalRoute();
        timeStamp.setEnd("Route Found! Route Length: "+route.size());
        System.out.println(timeStamp);

        assertFalse(route.isEmpty());
    }

    @Test
    void shouldFindRoute_BetweenStartAndDest_ExampleMapSquare_100()
    {
        //Arrange
        int columnWidth = 100;
        routeMapExample = new ExampleMapSquare(routeMap, columnWidth);

        var from = routeMap.getIntersection(0);
        var to = routeMap.getIntersection(columnWidth * columnWidth -1);

        System.out.println("Start");
        timeStamp.setBegin("Finding Route, GridWidth: "+columnWidth);
        //Act
        var routeFinder = new RouteFinder(routeMap, from, to);

        //Assert
        var route = routeFinder.getFinalRoute();
        timeStamp.setEnd("Route Found! Route Length: "+route.size());
        System.out.println(timeStamp);

        assertFalse(route.isEmpty());
    }

    @Test
    void shouldFindRoute_BetweenStartAndDest_ExampleMapSquare_1000()
    {
        //Arrange
        int columnWidth = 1000;
        routeMapExample = new ExampleMapSquare(routeMap, columnWidth);

        var from = routeMap.getIntersection(0);
        var to = routeMap.getIntersection(columnWidth * columnWidth -1);

        System.out.println("start");
        timeStamp.setBegin("Start Finding Route, GridWidth: "+columnWidth);
        //Act
        var routeFinder = new RouteFinder(routeMap, from, to);

        //Assert
        var route = routeFinder.getFinalRoute();
        timeStamp.setEnd("Route Found! Route Length: "+route.size());
        System.out.println(timeStamp);

        assertFalse(route.isEmpty());
    }

    @Test
    void shouldFindRoute_BetweenStartAndDest_ExampleMapSquare_10_000()
    {
        //Arrange
        int columnWidth = 1500;
        routeMapExample = new ExampleMapSquare(routeMap, columnWidth);

        var from = routeMap.getIntersection(0);
        var to = routeMap.getIntersection(columnWidth * columnWidth -1);

        System.out.println("start");
        timeStamp.setBegin("Start Finding Route, GridWidth: "+columnWidth);
        //Act
        var routeFinder = new RouteFinder(routeMap, from, to);

        //Assert
        var route = routeFinder.getFinalRoute();
        timeStamp.setEnd("Route Found! Route Length: "+route.size());
        System.out.println(timeStamp);

        assertFalse(route.isEmpty());
    }

    @Test
    void shouldFindRoute_BetweenStartAndDest_ExampleMapSquare_100_000()
    {
        //Arrange
        int columnWidth = 3000;
        routeMapExample = new ExampleMapSquare(routeMap, columnWidth);

        var from = routeMap.getIntersection(0);
        var to = routeMap.getIntersection(columnWidth * columnWidth -1);

        System.out.println("Start. IntersectionAmount: "+ columnWidth * columnWidth);
        timeStamp.setBegin("Start Finding Route, GridWidth: "+columnWidth);
        //Act
        var routeFinder = new RouteFinder(routeMap, from, to);

        //Assert
        var route = routeFinder.getFinalRoute();
        timeStamp.setEnd("Route Found! Route Length: "+route.size());
        System.out.println(timeStamp);

        assertFalse(route.isEmpty());
    }
}
