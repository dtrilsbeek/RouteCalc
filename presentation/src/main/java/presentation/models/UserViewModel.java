package presentation.models;

import route.model.Intersection;

public class UserViewModel {
    private int id;
    private String name;
    private Intersection startPoint;

    public UserViewModel(int id, String name) {
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
