package route;

import java.util.Set;

public class Intersection {

    private Set<Line> lines;
    private int x;
    private int y;

    public Intersection(int x, int y) {
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

}
