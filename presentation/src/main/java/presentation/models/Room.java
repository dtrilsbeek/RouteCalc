package presentation.models;

import java.util.HashMap;
import java.util.Map;

public class Room {

    private int id;
    private Map<Integer, User> userMap;

    public Room(int id) {
        this.id = id;
        this.userMap = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public Map<Integer, User> getUserMap() {
        return userMap;
    }

}
