package presentation.models.messages;

public class EmptyMessageModel {
    private String type;
    private boolean isSystem = false;

    public  EmptyMessageModel() {

    }

    public EmptyMessageModel(String type) {
        this.type = type;
    }

    public  void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return  type;
    }

    public boolean getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(boolean system) {
        isSystem = system;
    }
}
