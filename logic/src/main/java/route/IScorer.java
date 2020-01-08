package route;

import route.model.Intersection;

public interface IScorer {
    double computeCost(Intersection from, Intersection to);
}