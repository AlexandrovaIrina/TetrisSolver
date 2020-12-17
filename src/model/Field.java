package model;

public class Field {
    private Cell[][] field;

    public Field(){
        field = new Cell[10][16];
    }

    public Cell[][] getField() {
        return field;
    }

    public void changeCellStatus(int x, int y, CellStatus status) {
        field[x][y].setStatus(status);
    }

}
