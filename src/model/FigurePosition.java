package model;



import view.MainFrame;

import static model.CellStatus.*;
import static model.CellStatus.EMPTY;
import static model.Move.LEFT;
import static model.Move.RIGHT;
import static view.MainFrame.currentFigure;


public class FigurePosition {
    private Cell firstCell;
    private Cell secondCell;
    private Cell thirdCell;
    private Cell forthCell;
    private Cell rotationPoint;
    private CellStatus color;

    public CellStatus cellColor(FigureType type) {
        switch (type) {
            case SQUARE: return PURPLE;
            case LINE: return CYAN;
            case Z: return RED;
            case T: return ORANGE;
            case S: return BLUE;
            case L: return GREEN;
            case J: return YELLOW;
            default: return EMPTY;
        }
    }
    public FigurePosition(FigureType type) {
        color = cellColor(type);
        switch (type) {
            case J: {
                firstCell = new Cell(5, 1, color);
                secondCell = new Cell(5, 2, color);
                thirdCell = new Cell(5, 3, color);
                forthCell = new Cell(4, 3, color);
                rotationPoint = secondCell;
                break;
            }
            case L: {
                firstCell = new Cell(5, 1, color);
                secondCell = new Cell(5, 2, color);
                thirdCell = new Cell(5, 3, color);
                forthCell = new Cell(6, 3, color);
                rotationPoint = secondCell;
                break;
            }
            case S: {
                firstCell = new Cell(6, 1, color);
                secondCell = new Cell(5, 1, color);
                thirdCell = new Cell(5, 2, color);
                forthCell = new Cell(4, 2, color);
                rotationPoint = thirdCell;
                break;
            }
            case T: {
                firstCell = new Cell(4, 1, color);
                secondCell = new Cell(5, 1, color);
                thirdCell = new Cell(6, 1, color);
                forthCell = new Cell(5, 2, color);
                rotationPoint = secondCell;
                break;
            }
            case Z: {
                firstCell = new Cell(4, 1, color);
                secondCell = new Cell(5, 1, color);
                thirdCell = new Cell(5, 2, color);
                forthCell = new Cell(6, 2, color);
                rotationPoint = thirdCell;
                break;
            }
            case LINE: {
                firstCell = new Cell(4, 1, color);
                secondCell = new Cell(5, 1, color);
                thirdCell = new Cell(6, 1, color);
                forthCell = new Cell(7, 1, color);
                rotationPoint = secondCell;
                break;
            }
            case SQUARE: {
                firstCell = new Cell(5, 1, color);
                secondCell = new Cell(6, 1, color);
                thirdCell = new Cell(5, 2, color);
                forthCell = new Cell(6, 2, color);
                rotationPoint = secondCell;
                break;
            }
        }
    }

    public FigurePosition(Cell firstCell, Cell secondCell, Cell thirdCell, Cell forthCell) {
        this.firstCell = firstCell;
        this.secondCell = secondCell;
        this.thirdCell = thirdCell;
        this.forthCell = forthCell;
    }

    public Cell getFirstCell() { return firstCell; }

    public Cell getSecondCell() { return secondCell; }

    public Cell getThirdCell() { return thirdCell; }

    public Cell getForthCell() { return forthCell; }

    public void setFirstCell(Cell cell) { firstCell = cell; }
    public void setSecondCell(Cell cell) { secondCell = cell; }
    public void setThirdCell(Cell cell) { thirdCell = cell; }
    public void setForthCell(Cell cell) { forthCell = cell; }

    public void rotatePosition(){

    }

    public FigurePosition (FigurePosition position) {
        this.firstCell = position.getFirstCell();
        this.secondCell = position.getSecondCell();
        this.thirdCell = position.getThirdCell();
        this.forthCell = position.getForthCell();
    }
    public boolean ableToMove(Move move) {
        if (move == LEFT &&
                firstCell.getX() > 1 && forthCell.getX() > 1) return true;
        if (move == RIGHT &&
                firstCell.getX() < 10 && secondCell.getX() < 10 &&
                thirdCell.getX() < 10 && forthCell.getX() < 10) return true;
        return false;
    }

    public void falling() {
        final CellStatus color = currentFigure.getColor();
        final Cell newFirstCell = new Cell(firstCell.getX(), firstCell.getY() + 1, color);
        final Cell newSecondCell = new Cell(secondCell.getX(), secondCell.getY() + 1, color);
        final Cell newThirdCell = new Cell(thirdCell.getX(), thirdCell.getY() + 1, color);
        final Cell newForthCell = new Cell(forthCell.getX(), forthCell.getY() + 1, color);

        MainFrame.redrawFigure(currentFigure, this, new FigurePosition(newFirstCell, newSecondCell, newThirdCell, newForthCell));

        firstCell = newFirstCell;
        secondCell = newSecondCell;
        thirdCell = newThirdCell;
        forthCell = newForthCell;
    }


}
