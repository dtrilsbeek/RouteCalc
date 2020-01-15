import org.junit.jupiter.api.Test;
import route.RouteFinder;
import route.RouteMapExample;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class RouteFinderTest {
    @Test
    void testFindRoute()
    {
        var routeMap = new RouteMapExample().getRouteMap();
        var from = routeMap.getIntersection(29);
        var to = routeMap.getIntersection(48);
        var routeFinder = new RouteFinder(routeMap, from, to);
        var result = routeFinder.getFinalRoute();

        assertFalse(result.isEmpty());
    }
}
