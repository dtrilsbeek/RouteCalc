package route;

public class RouteMapExample {
    private final RouteMap routeMap;

    public RouteMapExample() {
        this.routeMap = new RouteMap(800, 800 ,20);
        this.addIntersections();
    }

    private void addIntersections() {
        routeMap.addIntersection(25, 20);
        routeMap.addIntersection(50, 25);
        routeMap.addIntersection(75, 20);
        routeMap.addIntersection(120, 80);
        routeMap.addIntersection(180, 20);
        routeMap.addIntersection(220, 20);
        routeMap.addIntersection(20, 100);
        routeMap.addIntersection(20, 120);
        routeMap.addIntersection(20, 150);
        routeMap.addIntersection(20, 280);
        routeMap.addIntersection(20, 320);
        routeMap.addIntersection(20, 420);
        routeMap.addIntersection(20, 620);
        routeMap.addIntersection(20, 720);
        routeMap.addIntersection(120, 620);
        routeMap.addIntersection(320, 720);
        routeMap.addIntersection(20, 720);
        routeMap.addIntersection(420, 620);
        routeMap.addIntersection(330, 620);
        routeMap.addIntersection(550, 620);
        routeMap.addIntersection(550, 620);
        routeMap.addIntersection(220, 620);
        routeMap.addIntersection(780, 74);
        routeMap.addIntersection(562, 59);
        routeMap.addIntersection(802, 188);
        routeMap.addIntersection(639, 171);
        routeMap.addIntersection(768, 368);
        routeMap.addIntersection(607, 302);
        routeMap.addIntersection(487, 248);
        routeMap.addIntersection(434, 108);
        routeMap.addIntersection( 265, 125);
        routeMap.addIntersection( 172, 284);
        routeMap.addIntersection( 370, 291);
        routeMap.addIntersection( 174, 403);
        routeMap.addIntersection( 395, 402);
        routeMap.addIntersection( 681, 430);
        routeMap.addIntersection( 868, 448);
        routeMap.addIntersection( 507, 462);
        routeMap.addIntersection( 213, 448);
        routeMap.addIntersection( 123, 363);
        routeMap.addIntersection( 795, 484);
        routeMap.addIntersection( 759, 592);
        routeMap.addIntersection( 652, 569);
        routeMap.addIntersection( 289, 560);
        routeMap.addIntersection( 93, 520);
        routeMap.addIntersection( 85, 285);
        routeMap.addIntersection( 317, 217);
        routeMap.addIntersection( 345, 68);
        routeMap.addIntersection( 528, 27);
        routeMap.addIntersection( 721, 27);
        routeMap.addIntersection(760, 137);
        routeMap.addIntersection(866, 139);
        routeMap.addIntersection(819, 41);
        routeMap.addIntersection(734, 290);
        routeMap.addIntersection(829, 288);
    }

    private void addLines() {

    }

    public RouteMap getRouteMap() {
        return this.routeMap;
    }
}
