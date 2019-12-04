package presentation.models.messages;

public class UserMessageModel extends MessageModel {
    private String sender;

    public  UserMessageModel () {

    }

    public UserMessageModel(String message, String type, String sender) {
        super(message, type);
        this.sender = sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }
}
