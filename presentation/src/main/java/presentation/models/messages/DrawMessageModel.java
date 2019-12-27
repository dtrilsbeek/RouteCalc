package presentation.models.messages;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import presentation.models.LineModel;

public class DrawMessageModel extends EmptyMessageModel {

    private String color;
    private int brushSize;

    @JsonUnwrapped
    private LineModel line;

    public DrawMessageModel() {
        setType("draw");
    }

    public DrawMessageModel(String color, LineModel line) {
        this.color = color;
        this.line = line;

        setType("draw");
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setLine(LineModel line) {
        this.line = line;
    }

    public LineModel getLine() {
        return line;
    }
}
