package model;

import static model.CellStatus.*;
import static model.Move.LEFT;
import static view.MainFrame.currentFigure;

public class Figure {
    private final FigureType type;
    private final CellStatus color;
    private FigurePosition position;
    public Figure(FigureType type) {
        this.type = type;
        switch (type) {
            case SQUARE: {
                color = PURPLE;
                break;}
            case LINE: {
                color = CYAN;
                break;}
            case Z: {
                color = RED;
                break;
            }
            case T: {
                color = ORANGE;
                break;
            }
            case S: {
                color = BLUE;
                break;
            }
            case L: {
                color = GREEN;
                break;
            }
            case J: {
                color = YELLOW;
                break;
            }
            default: color = EMPTY;
        }
        position = new FigurePosition(type);
    }
    public FigureType getType() {
        return type;
    }

    public CellStatus getColor() {
        return color;
    }

    public FigurePosition getPosition() {return position; }

    public void movePosition(Move move) {
        final CellStatus color = currentFigure.getColor();
        int i = 1;
        if (move == LEFT) i = -1;
        if (position.ableToMove(move)) {

            position.setFirstCell(new Cell(position.getFirstCell().getX() + i, position.getFirstCell().getY(), color));
            position.setSecondCell(new Cell(position.getSecondCell().getX() + i, position.getSecondCell().getY(), color));
            position.setThirdCell(new Cell(position.getThirdCell().getX() + i, position.getThirdCell().getY(), color));
            position.setForthCell(new Cell(position.getForthCell().getX() + i, position.getForthCell().getY(), color));
        }

    }
}
