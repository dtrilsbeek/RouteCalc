package route;

import route.interfaces.IRouteMap;

public class RouteMapExample {
    private final IRouteMap routeMap;

    public RouteMapExample() {
        this.routeMap = new RouteMap(800, 800 ,20);
        this.addIntersections();
        this.addConnections();
    }

    public RouteMapExample(IRouteMap routeMap) {
        this.routeMap = routeMap;
        this.addIntersections();
        this.addConnections();
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

    private void addConnections() {
        routeMap.addConnection(38, 40);
        routeMap.addConnection(40 ,41);
        routeMap.addConnection(41 ,43);
        routeMap.addConnection(43 ,44);
        routeMap.addConnection(44 ,28);
        routeMap.addConnection(28 ,22);
        routeMap.addConnection(22 ,48);
        routeMap.addConnection(48 ,50);
        routeMap.addConnection(50 ,49);
        routeMap.addConnection(49 ,65);
        routeMap.addConnection(65 ,17);
        routeMap.addConnection(17 ,64);
        routeMap.addConnection(64 ,26);
        routeMap.addConnection(26 ,4);
        routeMap.addConnection(39, 42);
        routeMap.addConnection(42, 41);
        routeMap.addConnection(41, 13);
        routeMap.addConnection(13, 52);
        routeMap.addConnection(52, 51);
        routeMap.addConnection(51, 11);
        routeMap.addConnection(11, 58);
        routeMap.addConnection(58, 10);
        routeMap.addConnection(10, 18);
        routeMap.addConnection(18, 23);
        routeMap.addConnection(23, 19);
        routeMap.addConnection(35, 5);
        routeMap.addConnection(5, 55 );
        routeMap.addConnection(55, 8);
        routeMap.addConnection(8, 57 );
        routeMap.addConnection(57, 10 );
        routeMap.addConnection(10, 20);
        routeMap.addConnection(20, 0);
        routeMap.addConnection(0, 63 );
        routeMap.addConnection(63, 62);
        routeMap.addConnection(62, 61 );
        routeMap.addConnection(31, 6);
        routeMap.addConnection(6, 54);
        routeMap.addConnection(54, 56);
        routeMap.addConnection(56, 11 );
        routeMap.addConnection(11, 58);
        routeMap.addConnection(58, 10 );
        routeMap.addConnection(10, 59 );
        routeMap.addConnection(59, 36 );
        routeMap.addConnection(36, 37);
        routeMap.addConnection(45, 46);
        routeMap.addConnection(46, 27);
        routeMap.addConnection(27, 47);
        routeMap.addConnection(47, 21);
        routeMap.addConnection(21, 16);
        routeMap.addConnection(16, 48);
        routeMap.addConnection(48, 29);
        routeMap.addConnection(29, 52);
        routeMap.addConnection(52, 30);
        routeMap.addConnection(30, 53);
        routeMap.addConnection(53, 12);
        routeMap.addConnection(12, 52);
        routeMap.addConnection(52, 29);
        routeMap.addConnection(29, 15);
        routeMap.addConnection(15, 50);
        routeMap.addConnection(50, 17);
        routeMap.addConnection(17, 20);
        routeMap.addConnection(20, 2);
        routeMap.addConnection(2, 3);
        routeMap.addConnection(3, 25);
        routeMap.addConnection(25, 24);
        routeMap.addConnection(24, 25);
        routeMap.addConnection(25, 18);
        routeMap.addConnection(18, 9);
        routeMap.addConnection(9, 37);
        routeMap.addConnection(37, 7);
        routeMap.addConnection(7, 33);
        routeMap.addConnection(33, 5);
        routeMap.addConnection(5, 32);
        routeMap.addConnection(32, 5);
        routeMap.addConnection(5, 33);
        routeMap.addConnection(33, 34);
        routeMap.addConnection(34, 7);
        routeMap.addConnection(7, 60);
        routeMap.addConnection(60, 57);
        routeMap.addConnection(57, 10);
        routeMap.addConnection(14, 43);
        routeMap.addConnection(63, 1);
    }


    public IRouteMap getRouteMap() {
        return this.routeMap;
    }
}
