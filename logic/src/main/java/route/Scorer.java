package route;

import route.model.Intersection;

public class Scorer {

    public static double computeCost(Intersection from, Intersection to) {
        var distance1 = Math.abs(from.getX() - to.getX());
        var distance2 = Math.abs(from.getY() - to.getY());
        var number1 = Math.pow(distance1, 2);
        var number2 = Math.pow(distance2, 2);
        var result = number1 + number2;

        return Math.sqrt(result);
    }
}
