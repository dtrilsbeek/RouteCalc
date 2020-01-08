package route;

import route.model.Intersection;
import route.model.Line;

public class Scorer {
    public static int calcScore(Intersection intersection1, Intersection intersection2) {
        var distance1 = Math.abs(intersection1.getX() - intersection2.getX());
        var distance2 = Math.abs(intersection1.getY() - intersection2.getY());

        return distance1 + distance2;
    }
}
