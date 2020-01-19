package route.model;

import org.jetbrains.annotations.NotNull;
import route.Scorer;

import java.util.ArrayList;
import java.util.List;

public class Intersection implements Comparable<Intersection>  {

    private int id;
    private boolean start;
    private boolean dest;
    private List<Integer> connections;
    private int x;
    private int y;
    private double totalScore;
    private double lengthToDest;
    private int score;
    private Integer nth;
    private int parent;

    public Intersection(int id, int x, int y) {
        connections = new ArrayList<>();
        this.id = id;
        this.x = x;
        this.y = y;
        this.start = false;
        this.dest = false;
        nth = 999999;
        totalScore = 0;
        parent = -1;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getNth() {
        return nth;
    }

    public void setNth(int nth) {
        this.nth = nth;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public void setLengthToDest(Intersection dest) {
        this.lengthToDest = Scorer.computeCost(this, dest);
    }

    public double getScore(Intersection o) {
        var length = Scorer.computeCost(this, o);
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

    public double getLength(Intersection o) {
        return Scorer.computeCost(this, o);
    }

    public double getLengthToDest() {
        return lengthToDest;
    }

    @Override
    public int compareTo(@NotNull Intersection o) {
        return this.nth.compareTo(o.getNth());
    }
}
