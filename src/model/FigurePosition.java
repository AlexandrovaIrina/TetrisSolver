package model;


import static model.CellStatus.*;
import static model.CellStatus.EMPTY;

import static model.Figure.rotatedCell;
import static model.Move.*;

import static view.MainFrame.thisField;

public class FigurePosition {
    private Cell firstCell;
    private Cell secondCell;
    private Cell thirdCell;
    private Cell forthCell;
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
                firstCell = new Cell(4, 0, color);
                secondCell = new Cell(4, 1, color);
                thirdCell = new Cell(4, 2, color);
                forthCell = new Cell(3, 2, color);
                break;
            }
            case L: {
                firstCell = new Cell(4, 0, color);
                secondCell = new Cell(4, 1, color);
                thirdCell = new Cell(4, 2, color);
                forthCell = new Cell(5, 2, color);
                break;
            }
            case S: {
                firstCell = new Cell(5, 0, color);
                secondCell = new Cell(4, 1, color);
                thirdCell = new Cell(4, 0, color);
                forthCell = new Cell(3, 1, color);
                break;
            }
            case T: {
                firstCell = new Cell(3, 0, color);
                secondCell = new Cell(4, 0, color);
                thirdCell = new Cell(5, 0, color);
                forthCell = new Cell(4, 1, color);
                break;
            }
            case Z: {
                firstCell = new Cell(3, 0, color);
                secondCell = new Cell(4, 1, color);
                thirdCell = new Cell(4, 0, color);
                forthCell = new Cell(5, 1, color);
                break;
            }
            case LINE: {
                firstCell = new Cell(3, 0, color);
                secondCell = new Cell(4, 0, color);
                thirdCell = new Cell(5, 0, color);
                forthCell = new Cell(6, 0, color);
                break;
            }
            case SQUARE: {
                firstCell = new Cell(4, 0, color);
                secondCell = new Cell(5, 0, color);
                thirdCell = new Cell(4, 1, color);
                forthCell = new Cell(5, 1, color);
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


    public void changePosition(int dx, int dy) {
        setFirstCell(new Cell(firstCell.getX() + dx, firstCell.getY() + dy));
        setSecondCell(new Cell(secondCell.getX() + dx, secondCell.getY() + dy));
        setThirdCell(new Cell(thirdCell.getX() + dx, thirdCell.getY() + dy));
        setForthCell(new Cell(forthCell.getX() + dx, forthCell.getY() + dy));

    }

    public FigurePosition (FigurePosition position) {
        this.firstCell = position.getFirstCell();
        this.secondCell = position.getSecondCell();
        this.thirdCell = position.getThirdCell();
        this.forthCell = position.getForthCell();
    }
    public boolean ableToMove(Move move) {
        if (move == LEFT && secondCell.getX() > 0 && thirdCell.getX() > 0 &&
                firstCell.getX() > 0 && forthCell.getX() > 0 && 
                thisField.field[firstCell.getX() - 1][firstCell.getY()].getStatus() == EMPTY &&
                thisField.field[secondCell.getX() - 1][secondCell.getY()].getStatus() == EMPTY &&
                thisField.field[thirdCell.getX() - 1][thirdCell.getY()].getStatus() == EMPTY &&
                thisField.field[forthCell.getX() - 1][forthCell.getY()].getStatus() == EMPTY) return true;

        return move == RIGHT && firstCell.getX() < 9 && secondCell.getX() < 9 &&
                thirdCell.getX() < 9 && forthCell.getX() < 9 &&
                thisField.field[firstCell.getX() + 1][firstCell.getY()].getStatus() == EMPTY &&
                thisField.field[secondCell.getX() + 1][secondCell.getY()].getStatus() == EMPTY &&
                thisField.field[thirdCell.getX() + 1][thirdCell.getY()].getStatus() == EMPTY &&
                thisField.field[forthCell.getX() + 1][forthCell.getY()].getStatus() == EMPTY;
    }

    public boolean ableToFall() {
        return firstCell.getY() < 15 && secondCell.getY() < 15 &&
                thirdCell.getY() < 15 && forthCell.getY() < 15 &&
                thisField.field[firstCell.getX()][firstCell.getY() + 1].getStatus() == EMPTY &&
                thisField.field[secondCell.getX()][secondCell.getY() + 1].getStatus() == EMPTY &&
                thisField.field[thirdCell.getX()][thirdCell.getY() + 1].getStatus() == EMPTY &&
                thisField.field[forthCell.getX()][forthCell.getY() + 1].getStatus() == EMPTY;
    }

    public boolean ableToRotate() {
        final Cell rotatedFirst = rotatedCell(secondCell,firstCell);
        final Cell rotatedThird = rotatedCell(secondCell,thirdCell);
        final Cell rotatedForth = rotatedCell(secondCell,forthCell);
        if (rotatedFirst.getX() < 0 || rotatedFirst.getX() > 9 || 
                rotatedFirst.getY() < 0 || rotatedFirst.getY() > 15) return false;
        if (thisField.field[rotatedFirst.getX()][rotatedFirst.getY()].getStatus() != EMPTY) return false;
        if (rotatedThird.getX() < 0 || rotatedThird.getX() > 9 ||
                rotatedThird.getY() < 0 || rotatedThird.getY() > 15) return false;
        if (thisField.field[rotatedThird.getX()][rotatedThird.getY()].getStatus() != EMPTY) return false;
        if (rotatedForth.getX() < 0 || rotatedForth.getX() > 9 ||
                rotatedForth.getY() < 0 || rotatedForth.getY() > 15) return false;
        if (thisField.field[rotatedForth.getX()][rotatedForth.getY()].getStatus() != EMPTY) return false;
        return true;
    }

}
