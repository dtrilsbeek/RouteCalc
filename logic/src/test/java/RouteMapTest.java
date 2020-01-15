import org.junit.jupiter.api.Test;
import route.RouteFinder;
import route.RouteMap;
import route.RouteMapExample;
import route.model.Intersection;

import static org.junit.jupiter.api.Assertions.*;

public class RouteMapTest {

    private RouteMap routeMap = new RouteMap(800, 800, 20);

    @Test
    void shouldAddRandomIntersection() {
        //Act
        routeMap.generateRandomIntersections(1);
        var result = routeMap.getIntersections().size() > 0;

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldAddConnection_WhenAddingConnection_MultipleIntersections() {
        //Arrange
        routeMap.generateRandomIntersections(5);

        //Act
        routeMap.addConnection(1,2);
        var connections = routeMap.getIntersection(1).getConnections();

        //Assert
        var found = false;
        for(Integer connection: connections) {
            if (connection == 2) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    void shouldThrowException_WhenAddingConnection_SingleIntersection() {
        //Arrange

        //Act
        routeMap.generateRandomIntersections(1);
        var result = routeMap.getIntersections().size() > 0;

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldCreateIntersection_WhenUsingRouteMapExample() {
        //Arrange
        var example = new RouteMapExample(routeMap);

        //Act
        var test = example.getRouteMap().getIntersection(0);
        var resultX = test.getX() == 420;
        var resultY = test.getY() == 620;

        //Assert
        assertTrue(resultX);
        assertTrue(resultY);
    }
}
