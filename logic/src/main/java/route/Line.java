package route;

public class Line {
    private int id;
    private Intersection from;
    private Intersection to;

    public Line(int id, Intersection from, Intersection to) {
        this.id = id;
        this.from = from;
        this.to = to;
    }

    public int getId() {
        return id;
    }

    public Intersection getFrom() {
        return from;
    }

    public Intersection getTo() {
        return to;
    }
}
