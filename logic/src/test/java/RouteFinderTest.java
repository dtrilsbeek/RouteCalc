import org.junit.jupiter.api.Test;
import route.RouteFinder;
import route.RouteMap;
import route.ExampleMapReal;
import route.interfaces.IRouteMap;
import route.interfaces.IRouteMapExample;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RouteFinderTest {

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
    void shouldReturnExplored_WhenRoute_isFound() {
        //Arrange
        routeMap = new ExampleMapReal(routeMap).getRouteMap();
        var from = routeMap.getIntersection(29);
        var to = routeMap.getIntersection(48);

        //Act
        var routeFinder = new RouteFinder(routeMap, from, to);

        //Assert
        var route = routeFinder.getExplored();
        assertFalse(route.isEmpty());
    }

    @Test
    void shouldReturnEmpty_When_StartIsSameAsDest() {
        //Arrange
        routeMap = new ExampleMapReal(routeMap).getRouteMap();
        var from = routeMap.getIntersection(29);
        var to = routeMap.getIntersection(29);

        //Act
        var routeFinder = new RouteFinder(routeMap, from, to);

        //Assert
        var route = routeFinder.getFinalRoute();
        var result = route.size() < 2;

        assertTrue(result);
    }
}
