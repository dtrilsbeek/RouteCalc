import org.junit.jupiter.api.Test;
import route.RouteMap;
import route.ExampleMapReal;
import route.interfaces.IRouteMap;

import static org.junit.jupiter.api.Assertions.*;

public class RouteMapTest {

    private IRouteMap routeMapInterface = new RouteMap(800, 800, 20);

    @Test
    void shouldAddRandomIntersection() {
        //Act
        routeMapInterface.generateRandomIntersections(1);
        var result = routeMapInterface.getIntersections().size() > 0;

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldAddConnection_WhenAddingConnection_MultipleIntersections() {
        //Arrange
        routeMapInterface.generateRandomIntersections(5);

        //Act
        routeMapInterface.addConnection(1,2);
        var connections = routeMapInterface.getIntersection(1).getConnections();

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
        routeMapInterface.generateRandomIntersections(1);
        var result = routeMapInterface.getIntersections().size() > 0;

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldCreateIntersection_WhenUsingRouteMapExample() {
        //Arrange
        var example = new ExampleMapReal(routeMapInterface);

        //Act
        var test = example.getRouteMap().getIntersection(0);
        var resultX = test.getX() == 420;
        var resultY = test.getY() == 620;

        //Assert
        assertTrue(resultX);
        assertTrue(resultY);
    }
}
