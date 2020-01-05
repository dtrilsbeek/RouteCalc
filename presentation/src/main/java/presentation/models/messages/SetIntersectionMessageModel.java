package presentation.models.messages;
import route.Intersection;
import route.Line;

import java.util.Map;
import java.util.Set;

public class SetIntersectionMessageModel extends EmptyMessageModel {
    private int intersectionId;

    public SetIntersectionMessageModel() {
        setType("setIntersection");
    }

    public SetIntersectionMessageModel(String type, int intersectionId) {
        setType(type);
        this.intersectionId = intersectionId;
    }

    public int getIntersectionId() {
        return intersectionId;
    }
}
