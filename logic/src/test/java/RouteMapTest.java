import org.junit.jupiter.api.Test;
import route.RouteMap;

import static org.junit.jupiter.api.Assertions.*;

public class RouteMapTest {

    @Test
    void testGenerateRandomLines() {
        //Arrange
        var routeMap = new RouteMap();

        //Act
        routeMap.generateRandomIntersections(50);
        routeMap.generateRandomLines(50);

        var result = routeMap.getLines().size() > 0;

        //Assert
        assertTrue(result);
    }
}
