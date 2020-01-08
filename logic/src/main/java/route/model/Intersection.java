package route.model;

import route.model.Line;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static route.Scorer.calcScore;

public class Intersection {

    private int id;
    private boolean start;
    private boolean dest;
    private List<Line> lines;
    private int x;
    private int y;
    private int score;

    public Intersection(int id, int x, int y) {
        lines = new ArrayList<>();
        this.id = id;
        this.x = x;
        this.y = y;
        this.start = false;
        this.dest = false;
    }

    public void setScore(Intersection to) {
        this.score = calcScore(this, to);
    }

    public int getScore() {
        return score;
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
}
