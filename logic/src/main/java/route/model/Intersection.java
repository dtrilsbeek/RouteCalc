package route.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static route.Scorer.calcScore;

public class Intersection implements Comparable<Intersection>  {

    private int id;
    private boolean start;
    private boolean dest;
    private List<Integer> connections;
    private int x;
    private int y;
    private int totalScore;
    private int lengthToDest;
    private int score;

    public Intersection(int id, int x, int y) {
        connections = new ArrayList<>();
        this.id = id;
        this.x = x;
        this.y = y;
        this.start = false;
        this.dest = false;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public void setLengthToDest(Intersection dest) {
        this.lengthToDest = calcScore(this, dest);
    }

    public int getScore(Intersection o) {
        var length = calcScore(this, o);
        return length + lengthToDest;
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
        this.connections.add(intersection.getId());
    }

    public List<Integer> getConnections() {
        return connections;
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
