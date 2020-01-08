package route;

import route.model.Intersection;

public interface Scorer {
    double computeCost(Intersection from, Intersection to);
}