package route.model;

import org.jetbrains.annotations.NotNull;
import route.model.Line;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static route.Scorer.calcScore;

public class Intersection implements Comparable<Intersection>  {

    private int id;
    private boolean start;
    private boolean dest;
    private List<Line> lines;
    private List<Intersection> connections;
    private int x;
    private int y;
    private int lengthToDest;

    public Intersection(int id, int x, int y) {
        lines = new ArrayList<>();
        connections = new ArrayList<>();
        this.id = id;
        this.x = x;
        this.y = y;
        this.start = false;
        this.dest = false;
    }

    public void setLengthToDest(Intersection dest) {
        this.lengthToDest = calcScore(this, dest);
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

    public void addConnection(Intersection intersection) {
        this.connections.add(intersection);
    }

    public List<Intersection> getConnections() {
        return connections;
    }

    public void addLine(Line line) {
        this.lines.add(line);
    }

    public List<Line> getLines() {
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

    public int getLength(Intersection o) {
        return calcScore(this, o);
    }

    public int getLengthToDest() {
        return lengthToDest;
    }

    @Override
    public int compareTo(@NotNull Intersection o) {
        var length = calcScore(this, o);
        return Integer.compare(length + lengthToDest, o.getLength(this) + o.getLengthToDest());
    }
}
