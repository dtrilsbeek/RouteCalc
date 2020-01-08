package route.model;

public class Line {
    private int id;
    private static int idCounter;
    private int from;
    private int to;

    public Line(Intersection from, Intersection to) {
        this.id = idCounter;
        idCounter++;
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
