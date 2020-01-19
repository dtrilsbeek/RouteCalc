package route;

import route.interfaces.IRouteMap;

public class RouteMapExample {
    private final IRouteMap routeMapInterface;

    public RouteMapExample() {
        this.routeMapInterface = new RouteMap(800, 800 ,20);
        this.addIntersections();
        this.addConnections();
    }

    public RouteMapExample(IRouteMap routeMapInterface) {
        this.routeMapInterface = routeMapInterface;
        this.addIntersections();
        this.addConnections();
    }

    private void addIntersections() {
        routeMapInterface.addIntersection(420, 620);
        routeMapInterface.addIntersection(330, 620);
        routeMapInterface.addIntersection(550, 620);
        routeMapInterface.addIntersection(550, 620);
        routeMapInterface.addIntersection(220, 620);
        routeMapInterface.addIntersection(780, 74);
        routeMapInterface.addIntersection(562, 59);
        routeMapInterface.addIntersection(802, 188);
        routeMapInterface.addIntersection(639, 171);
        routeMapInterface.addIntersection(768, 368);
        routeMapInterface.addIntersection(607, 302);
        routeMapInterface.addIntersection(487, 248);
        routeMapInterface.addIntersection(434, 108);
        routeMapInterface.addIntersection( 265, 125);
        routeMapInterface.addIntersection( 172, 284);
        routeMapInterface.addIntersection( 370, 291);
        routeMapInterface.addIntersection( 174, 403);
        routeMapInterface.addIntersection( 395, 402);
        routeMapInterface.addIntersection( 681, 430);
        routeMapInterface.addIntersection( 868, 448);
        routeMapInterface.addIntersection( 507, 462);
        routeMapInterface.addIntersection( 213, 448);
        routeMapInterface.addIntersection( 123, 363);
        routeMapInterface.addIntersection( 795, 484);
        routeMapInterface.addIntersection( 759, 592);
        routeMapInterface.addIntersection( 652, 569);
        routeMapInterface.addIntersection( 289, 560);
        routeMapInterface.addIntersection( 93, 520);
        routeMapInterface.addIntersection( 85, 285);
        routeMapInterface.addIntersection( 317, 217);
        routeMapInterface.addIntersection( 345, 68);
        routeMapInterface.addIntersection( 528, 27);
        routeMapInterface.addIntersection( 721, 27);
        routeMapInterface.addIntersection(760, 137);
        routeMapInterface.addIntersection(866, 139);
        routeMapInterface.addIntersection(819, 41);
        routeMapInterface.addIntersection(734, 290);
        routeMapInterface.addIntersection(829, 288);
        routeMapInterface.addIntersection(100, 56);
        routeMapInterface.addIntersection(67,112);
        routeMapInterface.addIntersection(208, 88);
        routeMapInterface.addIntersection(155, 155);
        routeMapInterface.addIntersection(74,185);
        routeMapInterface.addIntersection(228, 229);
        routeMapInterface.addIntersection(121, 246);
        routeMapInterface.addIntersection(56, 356);
        routeMapInterface.addIntersection(65,453);
        routeMapInterface.addIntersection(135, 489);
        routeMapInterface.addIntersection(230, 350);
        routeMapInterface.addIntersection(282,417);
        routeMapInterface.addIntersection(320,334);
        routeMapInterface.addIntersection(397,206);
        routeMapInterface.addIntersection(342,154);
        routeMapInterface.addIntersection(438,47);
        routeMapInterface.addIntersection(497,104);
        routeMapInterface.addIntersection(650,108);
        routeMapInterface.addIntersection(542,186);
        routeMapInterface.addIntersection(629,222);
        routeMapInterface.addIntersection(552,278);
        routeMapInterface.addIntersection(675,277);
        routeMapInterface.addIntersection(700,219);
        routeMapInterface.addIntersection(107, 663);
        routeMapInterface.addIntersection(263,666);
        routeMapInterface.addIntersection(362,662);
        routeMapInterface.addIntersection(345,505);
        routeMapInterface.addIntersection(292,453);
    }

    private void addConnections() {
        routeMapInterface.addConnection(38, 40);
        routeMapInterface.addConnection(40 ,41);
        routeMapInterface.addConnection(41 ,43);
        routeMapInterface.addConnection(43 ,44);
        routeMapInterface.addConnection(44 ,28);
        routeMapInterface.addConnection(28 ,22);
        routeMapInterface.addConnection(22 ,48);
        routeMapInterface.addConnection(48 ,50);
        routeMapInterface.addConnection(50 ,49);
        routeMapInterface.addConnection(49 ,65);
        routeMapInterface.addConnection(65 ,17);
        routeMapInterface.addConnection(17 ,64);
        routeMapInterface.addConnection(64 ,26);
        routeMapInterface.addConnection(26 ,4);
        routeMapInterface.addConnection(39, 42);
        routeMapInterface.addConnection(42, 41);
        routeMapInterface.addConnection(41, 13);
        routeMapInterface.addConnection(13, 52);
        routeMapInterface.addConnection(52, 51);
        routeMapInterface.addConnection(51, 11);
        routeMapInterface.addConnection(11, 58);
        routeMapInterface.addConnection(58, 10);
        routeMapInterface.addConnection(10, 18);
        routeMapInterface.addConnection(18, 23);
        routeMapInterface.addConnection(23, 19);
        routeMapInterface.addConnection(35, 5);
        routeMapInterface.addConnection(5, 55 );
        routeMapInterface.addConnection(55, 8);
        routeMapInterface.addConnection(8, 57 );
        routeMapInterface.addConnection(57, 10 );
        routeMapInterface.addConnection(10, 20);
        routeMapInterface.addConnection(20, 0);
        routeMapInterface.addConnection(0, 63 );
        routeMapInterface.addConnection(63, 62);
        routeMapInterface.addConnection(62, 61 );
        routeMapInterface.addConnection(31, 6);
        routeMapInterface.addConnection(6, 54);
        routeMapInterface.addConnection(54, 56);
        routeMapInterface.addConnection(56, 11 );
        routeMapInterface.addConnection(11, 58);
        routeMapInterface.addConnection(58, 10 );
        routeMapInterface.addConnection(10, 59 );
        routeMapInterface.addConnection(59, 36 );
        routeMapInterface.addConnection(36, 37);
        routeMapInterface.addConnection(45, 46);
        routeMapInterface.addConnection(46, 27);
        routeMapInterface.addConnection(27, 47);
        routeMapInterface.addConnection(47, 21);
        routeMapInterface.addConnection(21, 16);
        routeMapInterface.addConnection(16, 48);
        routeMapInterface.addConnection(48, 29);
        routeMapInterface.addConnection(29, 52);
        routeMapInterface.addConnection(52, 30);
        routeMapInterface.addConnection(30, 53);
        routeMapInterface.addConnection(53, 12);
        routeMapInterface.addConnection(12, 52);
        routeMapInterface.addConnection(52, 29);
        routeMapInterface.addConnection(29, 15);
        routeMapInterface.addConnection(15, 50);
        routeMapInterface.addConnection(50, 17);
        routeMapInterface.addConnection(17, 20);
        routeMapInterface.addConnection(20, 2);
        routeMapInterface.addConnection(2, 3);
        routeMapInterface.addConnection(3, 25);
        routeMapInterface.addConnection(25, 24);
        routeMapInterface.addConnection(24, 25);
        routeMapInterface.addConnection(25, 18);
        routeMapInterface.addConnection(18, 9);
        routeMapInterface.addConnection(9, 37);
        routeMapInterface.addConnection(37, 7);
        routeMapInterface.addConnection(7, 33);
        routeMapInterface.addConnection(33, 5);
        routeMapInterface.addConnection(5, 32);
        routeMapInterface.addConnection(32, 5);
        routeMapInterface.addConnection(5, 33);
        routeMapInterface.addConnection(33, 34);
        routeMapInterface.addConnection(34, 7);
        routeMapInterface.addConnection(7, 60);
        routeMapInterface.addConnection(60, 57);
        routeMapInterface.addConnection(57, 10);
        routeMapInterface.addConnection(14, 43);
        routeMapInterface.addConnection(63, 1);
    }


    public IRouteMap getRouteMap() {
        return this.routeMapInterface;
    }
}
