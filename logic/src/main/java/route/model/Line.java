package route.model;

import static route.Scorer.calcScore;

public class Line {
    private int id;
    private static int idCounter;
    private int from;
    private int to;
    private int score;

    public Line(Intersection from, Intersection to) {
        this.id = idCounter;
        idCounter++;
        this.score = calcScore(from, to);
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

    public int getScore() {
        return score;
    }
}
