package database;

public class PlayerDTO {
    private int id;
    private String name;
    private String password;
    private int score;
    private int gamesPlayed;

    public PlayerDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public PlayerDTO(int id, String name, int score, int gamesPlayed) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.gamesPlayed = gamesPlayed;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getScore() {
        return score;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    @Override
    public String toString() {
        return "Player: "+ this.name +"; Score: "+score+"; Games Played: "+gamesPlayed+";";
    }
}