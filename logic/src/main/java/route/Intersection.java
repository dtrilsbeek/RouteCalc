package route;

import java.util.HashSet;
import java.util.Set;

public class Intersection {

    private int id;
    private boolean start;
    private boolean dest;
    private Set<Line> lines;
    private int x;
    private int y;

    public Intersection(int id, int x, int y) {
        lines = new HashSet<>();
        this.id = id;
        this.x = x;
        this.y = y;
        this.start = false;
        this.dest = false;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public boolean isDest() {
        return dest;
    }

    public void setDest(boolean dest) {
        this.dest = dest;
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
