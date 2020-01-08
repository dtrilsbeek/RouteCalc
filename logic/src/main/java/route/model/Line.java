package route.model;

import org.jetbrains.annotations.NotNull;

import static route.Scorer.calcScore;

public class Line implements Comparable<Line> {
    private int id;
    private static int idCounter;
    private int from; // using the id's to prevent circular references. (json converter)
    private int to;
    private int length;

    public Line(Intersection from, Intersection to) {
        this.id = idCounter;
        idCounter++;
        this.length = calcScore(from, to);
        this.from = from.getId();
        this.to = to.getId();
    }

    public int getLength() {
        return length;
    }

    @Override
    public int compareTo(@NotNull Line o) {
        return Integer.compare(length, o.getLength());
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
