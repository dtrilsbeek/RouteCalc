package logic.round;

import logic.room.Room;

import java.util.TimerTask;

public class RoundTimer extends TimerTask {
    protected Room timerRoom;

    public RoundTimer(Room room){
        timerRoom = room;
    }
    public void run() {
        //Required because of extending TimerTask, no implementation needed.
    }
}
