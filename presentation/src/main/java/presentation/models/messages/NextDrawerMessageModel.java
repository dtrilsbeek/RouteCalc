package presentation.models.messages;

public class NextDrawerMessageModel extends MessageModel {
    private int time;

    public NextDrawerMessageModel() {
        setIsSystem(true);
    }

    public NextDrawerMessageModel(String message, int time) {
        super(message, "next_drawer");
        this.time = time;
        setIsSystem(true);
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
