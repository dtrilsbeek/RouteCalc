package presentation.models.messages;

public class FillMessageModel extends EmptyMessageModel {
    private String color;

    public FillMessageModel() {
        setType("fill");
    }

    public FillMessageModel(String color) {
        setType("fill");
        this.color = color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
