import org.junit.jupiter.api.Test;
import route.Intersection;
import route.RouteMap;

import static org.junit.jupiter.api.Assertions.*;

public class RouteMapTest {

    @Test
    void testGenerateRandomLines() {
        //Arrange
        var routeMap = new RouteMap(800, 800, 20);

        //Act
        //routeMap.generateRandomIntersections(50);
        //routeMap.generateRandomLines(50);

        var result = routeMap.getLines().size() > 0;

        var intersections = routeMap.getIntersections();

//        intersections.forEach((key, value) -> {
//                if (value.getLines().size() > 0) {
//                    var i = value;
//                }
//            }
//        );

        //Assert
        assertFalse(result);
    }
}
