package presentation.models.messages;

public class SystemMessageModel extends MessageModel {
    public SystemMessageModel() {
        setIsSystem(true);
        setType("system");
    }

    public SystemMessageModel(String message) {
        setMessage(message);
        setIsSystem(true);
        setType("system");
    }
}
