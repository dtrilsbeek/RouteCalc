package presentation.models.messages;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import presentation.models.LineModel;

public class DrawMessageModel extends EmptyMessageModel {

    private String color;
    private int brushSize;

    @JsonUnwrapped
    private LineModel line;

    int canvasWidth;
    int canvasHeight;

    public DrawMessageModel() {
        setType("draw");
    }

    public DrawMessageModel(int brushSize, String color, LineModel line, int canvasWidth, int canvasHeight) {
        this.brushSize = brushSize;
        this.color = color;
        this.line = line;
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        setType("draw");
    }

    public int getBrushSize() {
        return brushSize;
    }

    public void setBrushSize(int brushSize) {
        this.brushSize = brushSize;
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


    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    public void setCanvasWidth(int canvasWidth) {
        this.canvasWidth = canvasWidth;
    }

    public void setCanvasHeight(int canvasHeight) {
        this.canvasHeight = canvasHeight;
    }
}
