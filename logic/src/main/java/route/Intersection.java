package route;

import java.util.HashSet;
import java.util.Set;

public class Intersection {

    private int id;
    private Set<Line> lines;
    private int x;
    private int y;

    public Intersection(int id, int x, int y) {
        lines = new HashSet<>();
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public void addLine(Line line) {
        this.lines.add(line);
    }

    public Set<Line> getLines() {
        return this.lines;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }
}
