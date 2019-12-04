package presentation.models.messages;

public class MessageModel extends EmptyMessageModel {
    private String message;


    public  MessageModel() {

    }

    public MessageModel(String message, String type) {
        super(type);
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return  message;
    }
}
