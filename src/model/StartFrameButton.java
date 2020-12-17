package model;

public class StartFrameButton {
    private final ButtonType type;
    private final int coordinateX;
    private final int coordinateY;

    public StartFrameButton(ButtonType type, int coordinateX, int coordinateY) {
        this.type = type;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public ButtonType getType() {
        return type;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

}
