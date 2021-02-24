package model;

public class Cell {
    private final int x;
    private final int y;
    private CellStatus status;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.status = CellStatus.EMPTY;
    }
    public Cell(int x, int y, CellStatus status) {
        this.x = x;
        this.y = y;
        this.status = status;
    }
    public Cell(Cell other) {
        this.x = other.x;
        this.y = other.y;
        this.status = other.status;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public CellStatus getStatus() {
        return status;
    }

    public void setStatus(CellStatus status) {
        this.status = status;
    }

}
