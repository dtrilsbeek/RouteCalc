package presentation.models;

import java.util.Collections;
import java.util.List;

public class RoomModel {
    private String name;
    private int id;
    private List<String> players;

    public RoomModel() {

    }

    public RoomModel(String name, int id, List<String> players) {
        this.name = name;
        this.id = id;
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<String> getPlayers() {
        return Collections.unmodifiableList(players);
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

}
