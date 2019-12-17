package route;

public interface Scorer {
    double computeCost(Intersection from, Intersection to);
}