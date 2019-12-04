package presentation.models;

public class LineModel {

    int currX;
    int currY;
    int prevX;
    int prevY;

    public LineModel() {

    }

    public LineModel(int currX, int currY, int prevX, int prevY) {
        this.currX = currX;
        this.currY = currY;
        this.prevX = prevX;
        this.prevY = prevY;
    }

    public int getCurrX() {
        return currX;
    }

    public int getCurrY() {
        return currY;
    }

    public int getPrevX() {
        return prevX;
    }

    public int getPrevY() {
        return prevY;
    }

    public void setCurrX(int currX) {
        this.currX = currX;
    }

    public void setCurrY(int currY) {
        this.currY = currY;
    }

    public void setPrevX(int prevX) {
        this.prevX = prevX;
    }

    public void setPrevY(int prevY) {
        this.prevY = prevY;
    }
}
