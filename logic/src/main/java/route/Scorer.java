package route;

import route.model.Intersection;

public class Scorer {
    public static int calcScore(Intersection intersection1, Intersection intersection2) {
        var distance1 = Math.abs(intersection1.getX() - intersection2.getX());
        var distance2 = Math.abs(intersection1.getY() - intersection2.getY());
        var number1 = Math.pow(distance1, 2);
        var number2 = Math.pow(distance2, 2);
        var result = number1 + number2;

        return (int) Math.sqrt(result);
    }
}
