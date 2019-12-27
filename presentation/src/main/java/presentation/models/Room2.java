package presentation.models;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import io.javalin.websocket.WsContext;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import logic.Player;
import logic.round.RoundTimer;
import logic.round.WordPicker;

public class Room2 {
    private int id;
    private List<Player> players = new CopyOnWriteArrayList();
    private Player drawer;
    private String currentWord;
    private int maxPlayers = 8;
    private boolean gameStarted = false;
    private WordPicker wordPicker;
    private Timer timer;
    private RoundTimer task;
    private String name;
    private int awardPoints = 10;
    public Map<WsContext, Player> userUsernameMap = new ConcurrentHashMap();

    public Room2(int id) {
        this.id = id;
        this.wordPicker = new WordPicker();
    }

    public String getCurrentWord() {
        return this.currentWord;
    }

    public boolean join(Player player) {
        if (this.players.size() < this.maxPlayers) {
            this.players.add(player);
            return true;
        } else {
            return false;
        }
    }

    public void leave(String username) {
        Iterator var2 = this.players.iterator();

        while(var2.hasNext()) {
            Player player = (Player)var2.next();
            if (player.getName().equals(username)) {
                this.players.remove(player);
            }
        }

    }

    public void nextRound() {
        this.drawer = this.selectNextDrawer();
        this.currentWord = this.selectNextWord();
        this.gameStarted = true;
    }

    private String selectNextWord() {
        return this.currentWord == null ? this.wordPicker.getNewWord() : this.wordPicker.selectNextWord(this.currentWord);
    }

    private Player selectNextDrawer() {
        Player nextDrawer;
        int index;
        if (this.drawer == null) {
            index = ThreadLocalRandom.current().nextInt(this.players.size());
            nextDrawer = (Player)this.players.get(index);
        } else {
            for(nextDrawer = this.drawer; this.drawer.equals(nextDrawer); nextDrawer = (Player)this.players.get(index)) {
                index = ThreadLocalRandom.current().nextInt(this.players.size());
            }
        }

        return nextDrawer;
    }

    public Player getCurrentDrawer() {
        return this.drawer;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public int getId() {
        return this.id;
    }

    public void purgeTimer() {
        this.timer.purge();
    }

    public Timer getTimer() {
        return this.timer;
    }

    public void setTimer(RoundTimer task, long time) {
        this.timer = new Timer();
        this.timer.schedule(task, time);
    }

    public RoundTimer getTask() {
        return this.task;
    }

    public void setTask(RoundTimer task) {
        this.task = task;
    }

    public void clearTask() {
        if (this.task != null) {
            this.task.cancel();
            this.task = null;
            this.purgeTimer();
        }

    }

    public boolean checkWord(Player player, String guess) {
        boolean result = this.currentWord.equalsIgnoreCase(guess);
        if (result) {
            player.addScore(this.awardPoints);
            this.drawer.addScore(this.awardPoints);
            Iterator var4 = this.players.iterator();

            while(var4.hasNext()) {
                Player p = (Player)var4.next();
                p.addGamesPlayed();
            }
        }

        return result;
    }

    public boolean hasGameStarted() {
        return this.gameStarted;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
