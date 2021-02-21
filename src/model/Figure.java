package model;

import static model.CellStatus.*;

import static model.FigureCreator.recreateFigure;
import static model.Move.LEFT;
import static view.MainFrame.currentFigure;
import static view.MainFrame.field;

public class Figure {
    private FigureType type;
    private CellStatus color;
    private FigurePosition position;
    public Figure(FigureType type) {
        Figure temp = new Figure(type, new FigurePosition(type));
        this.position = temp.position;
        this.type = temp.type;
        this.color = temp.color;
    }

    public Figure(FigureType type, FigurePosition pos) {
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
        int dx = pos.getSecondCell().getX() - position.getSecondCell().getX();
        int dy = pos.getSecondCell().getY() - position.getSecondCell().getY();
        position.changePosition(dx, dy);
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
        field[position.getFirstCell().getX()][position.getFirstCell().getY()].setStatus(EMPTY);
        field[position.getSecondCell().getX()][position.getSecondCell().getY()].setStatus(EMPTY);
        field[position.getThirdCell().getX()][position.getThirdCell().getY()].setStatus(EMPTY);
        field[position.getForthCell().getX()][position.getForthCell().getY()].setStatus(EMPTY);
        
        if (position.ableToMove(move)) {
            position.setFirstCell(new Cell(position.getFirstCell().getX() + i, position.getFirstCell().getY(), color));
            position.setSecondCell(new Cell(position.getSecondCell().getX() + i, position.getSecondCell().getY(), color));
            position.setThirdCell(new Cell(position.getThirdCell().getX() + i, position.getThirdCell().getY(), color));
            position.setForthCell(new Cell(position.getForthCell().getX() + i, position.getForthCell().getY(), color));
        }

        field[position.getFirstCell().getX()][position.getFirstCell().getY()].setStatus(position.getFirstCell().getStatus());
        field[position.getSecondCell().getX()][position.getSecondCell().getY()].setStatus(position.getSecondCell().getStatus());
        field[position.getThirdCell().getX()][position.getThirdCell().getY()].setStatus(position.getThirdCell().getStatus());
        field[position.getForthCell().getX()][position.getForthCell().getY()].setStatus(position.getForthCell().getStatus());

    }

    public void falling() {
        final CellStatus color = currentFigure.getColor();
        field[position.getFirstCell().getX()][position.getFirstCell().getY()].setStatus(EMPTY);
        field[position.getSecondCell().getX()][position.getSecondCell().getY()].setStatus(EMPTY);
        field[position.getThirdCell().getX()][position.getThirdCell().getY()].setStatus(EMPTY);
        field[position.getForthCell().getX()][position.getForthCell().getY()].setStatus(EMPTY);
        final boolean able = position.ableToFall();

        if (able) {
            position.setFirstCell(new Cell(position.getFirstCell().getX(), position.getFirstCell().getY() + 1, color));
            position.setSecondCell(new Cell(position.getSecondCell().getX(), position.getSecondCell().getY() + 1, color));
            position.setThirdCell(new Cell(position.getThirdCell().getX(), position.getThirdCell().getY() + 1, color));
            position.setForthCell(new Cell(position.getForthCell().getX(), position.getForthCell().getY() + 1, color));

        }

        field[position.getFirstCell().getX()][position.getFirstCell().getY()].setStatus(position.getFirstCell().getStatus());
        field[position.getSecondCell().getX()][position.getSecondCell().getY()].setStatus(position.getSecondCell().getStatus());
        field[position.getThirdCell().getX()][position.getThirdCell().getY()].setStatus(position.getThirdCell().getStatus());
        field[position.getForthCell().getX()][position.getForthCell().getY()].setStatus(position.getForthCell().getStatus());

        if (!able) recreateFigure();
    }

    public void assign(Figure other) {
        this.position = other.position;
        this.type = other.type;
        this.color = other.color;
    }
}
