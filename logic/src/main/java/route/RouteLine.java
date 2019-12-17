package route;

class RouteLine implements Comparable<RouteLine> {
    private final Line current;
    private Line previous;
    private double routeScore;
    private double estimatedScore;

    RouteLine(Line current) {
        this(current, null, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    RouteLine(Line current, Line previous, double routeScore, double estimatedScore) {
        this.current = current;
        this.previous = previous;
        this.routeScore = routeScore;
        this.estimatedScore = estimatedScore;
    }

    @Override
    public int compareTo(RouteLine other) {
        return Double.compare(this.estimatedScore, other.estimatedScore);
    }
}