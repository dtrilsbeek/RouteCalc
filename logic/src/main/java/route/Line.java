package route;

public class Line {
    private int id;
    private int from;
    private int to;

    public Line(int id, Intersection from, Intersection to) {
        this.id = id;
        this.from = from.getId();
        this.to = to.getId();
    }

    public int getId() {
        return id;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }
}
