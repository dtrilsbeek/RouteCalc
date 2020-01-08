package presentation.models;

import route.model.Intersection;

public class User {
    private int id;
    private String name;
    private Intersection startPoint;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Intersection getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Intersection startPoint) {
        startPoint.setStart(true);
        this.startPoint = startPoint;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }
}
