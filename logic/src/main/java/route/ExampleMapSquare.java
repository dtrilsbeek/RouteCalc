package route;

import route.interfaces.IRouteMap;
import route.interfaces.IRouteMapExample;

public class ExampleMapSquare implements IRouteMapExample {
    private final IRouteMap routeMap;

    public ExampleMapSquare() {
        this.routeMap = new RouteMap(800, 800 ,20);
        this.routeMap.generateSquareMap(10);
    }

    public ExampleMapSquare(IRouteMap routeMap, int size) {
        this.routeMap = routeMap;
        this.routeMap.generateSquareMap(size);
    }


    @Override
    public IRouteMap getRouteMap() {
        return this.routeMap;
    }
}
