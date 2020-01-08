package route;

public class RouteMapExample {
    private final RouteMap routeMap;

    public RouteMapExample() {
        this.routeMap = new RouteMap(800, 800 ,20);
        this.addIntersections();
        this.addLines();
    }

    private void addIntersections() {
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
        routeMap.addIntersection(100, 56);
        routeMap.addIntersection(67,112);
        routeMap.addIntersection(208, 88);
        routeMap.addIntersection(155, 155);
        routeMap.addIntersection(74,185);
        routeMap.addIntersection(228, 229);
        routeMap.addIntersection(121, 246);
        routeMap.addIntersection(56, 356);
        routeMap.addIntersection(65,453);
        routeMap.addIntersection(135, 489);
        routeMap.addIntersection(230, 350);
        routeMap.addIntersection(282,417);
        routeMap.addIntersection(320,334);
        routeMap.addIntersection(397,206);
        routeMap.addIntersection(342,154);
        routeMap.addIntersection(438,47);
        routeMap.addIntersection(497,104);
        routeMap.addIntersection(650,108);
        routeMap.addIntersection(542,186);
        routeMap.addIntersection(629,222);
        routeMap.addIntersection(552,278);
        routeMap.addIntersection(675,277);
        routeMap.addIntersection(700,219);
        routeMap.addIntersection(107, 663);
        routeMap.addIntersection(263,666);
        routeMap.addIntersection(362,662);
        routeMap.addIntersection(345,505);
        routeMap.addIntersection(292,453);
    }

    private void addLines() {
        routeMap.addLine(38, 40);
        routeMap.addLine(40 ,41);
        routeMap.addLine(41 ,43);
        routeMap.addLine(43 ,44);
        routeMap.addLine(44 ,28);
        routeMap.addLine(28 ,22);
        routeMap.addLine(22 ,48);
        routeMap.addLine(48 ,50);
        routeMap.addLine(50 ,49);
        routeMap.addLine(49 ,65);
        routeMap.addLine(65 ,17);
        routeMap.addLine(17 ,64);
        routeMap.addLine(64 ,26);
        routeMap.addLine(26 ,4);
    }

    public RouteMap getRouteMap() {
        return this.routeMap;
    }
}
