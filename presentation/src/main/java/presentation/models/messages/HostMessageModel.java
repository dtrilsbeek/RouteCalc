package presentation.models.messages;

public class HostMessageModel extends MessageModel
{
    public  HostMessageModel() {
        setType("host");
    }

    public HostMessageModel(String message) {
        super(message, "host");
    }
}
