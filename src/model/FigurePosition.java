package model;

import javafx.util.Pair;

import static model.Move.LEFT;


public class FigurePosition {
    private Cell firstCell;
    private Cell secondCell;
    private Cell thirdCell;
    private Cell forthCell;
    private Pair<Integer, Integer> rotationPoint;
    private Figure figure;
    public FigurePosition(FigureType type) {
        final CellStatus color = figure.getColor();
        figure = new Figure(type);
        firstCell = new Cell(5, 1, color);

        switch (figure.getType()) {
            case J: {
                secondCell = new Cell(5, 2, color);
                thirdCell = new Cell(5, 3, color);
                forthCell = new Cell(4, 3, color);
                rotationPoint = new Pair<Integer, Integer> (6, 2);
            }
            case L: {
                secondCell = new Cell(5, 2, color);
                thirdCell = new Cell(5, 3, color);
                forthCell = new Cell(6, 3, color);
                rotationPoint = new Pair<Integer, Integer> (6, 2);

            }
            case S: {
                secondCell = new Cell(6, 1, color);
                thirdCell = new Cell(5, 2, color);
                forthCell = new Cell(4, 2, color);
                rotationPoint = new Pair<Integer, Integer> (5, 1);

            }
            case T: {
                secondCell = new Cell(4, 1, color);
                thirdCell = new Cell(6, 1, color);
                forthCell = new Cell(5, 2, color);
                rotationPoint = new Pair<Integer, Integer> (5, 1);

            }
            case Z: {
                secondCell = new Cell(4, 1, color);
                thirdCell = new Cell(5, 2, color);
                forthCell = new Cell(6, 2, color);
                rotationPoint = new Pair<Integer, Integer> (5, 1);

            }
            case LINE: {
                secondCell = new Cell(4, 1, color);
                thirdCell = new Cell(6, 1, color);
                forthCell = new Cell(7, 1, color);
                rotationPoint = new Pair<Integer, Integer> (5, 1);

            }
            case SQUARE: {
                secondCell = new Cell(6, 1, color);
                thirdCell = new Cell(5, 2, color);
                forthCell = new Cell(6, 2, color);
                rotationPoint = new Pair<Integer, Integer> (5, 1);

            }
        }
    }

    public void rotatePosition(){
    }

    public void falling() {
        final CellStatus color = figure.getColor();
        firstCell = new Cell(firstCell.getX(), firstCell.getY() + 1, color);
        secondCell = new Cell(secondCell.getX(), secondCell.getY() + 1, color);
        thirdCell = new Cell(thirdCell.getX(), thirdCell.getY() + 1, color);
        forthCell = new Cell(forthCell.getX(), forthCell.getY() + 1, color);
    }

    public void movePosition(Move move) {
        final CellStatus color = figure.getColor();
        int i = 1;
        if (move == LEFT) i = -1;
        firstCell = new Cell(firstCell.getX() + i, firstCell.getY(), color);
        secondCell = new Cell(secondCell.getX() + i, secondCell.getY(), color);
        thirdCell = new Cell(thirdCell.getX() + i, thirdCell.getY(), color);
        forthCell = new Cell(forthCell.getX() + i, forthCell.getY(), color);
    }
}
