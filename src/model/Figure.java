package model;

import static controller.Controller.ableToControl;
import static model.CellStatus.*;

import static model.Field.deleteLines;
import static model.FigureCreator.recreateFigure;
import static model.Move.LEFT;
import static view.MainFrame.*;

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

        thisField.deleteFigure(this);
        if (position.ableToMove(move)) {
            position.setFirstCell(new Cell(position.getFirstCell().getX() + i, position.getFirstCell().getY(), color));
            position.setSecondCell(new Cell(position.getSecondCell().getX() + i, position.getSecondCell().getY(), color));
            position.setThirdCell(new Cell(position.getThirdCell().getX() + i, position.getThirdCell().getY(), color));
            position.setForthCell(new Cell(position.getForthCell().getX() + i, position.getForthCell().getY(), color));
        }
        thisField.addFigure(this);

    }
    public static Cell rotatedCell(Cell baseCell, Cell rotationCell) {
        int dx = rotationCell.getX() - baseCell.getX();
        int dy = rotationCell.getY() - baseCell.getY();
        return new Cell(baseCell.getX() - dy, baseCell.getY() + dx, rotationCell.getStatus());
    }

    public void rotatePosition(){
        thisField.deleteFigure(this);

        if (position.ableToRotate()) {
            position.setFirstCell(rotatedCell(position.getSecondCell(), position.getFirstCell()));
            position.setThirdCell(rotatedCell(position.getSecondCell(), position.getThirdCell()));
            position.setForthCell(rotatedCell(position.getSecondCell(), position.getForthCell()));
        }
        thisField.addFigure(this);

    }
    public void falling() {
        final CellStatus color = currentFigure.getColor();
        thisField.deleteFigure(this);
        final boolean able = position.ableToFall();

        if (able) {
            position.setFirstCell(new Cell(position.getFirstCell().getX(), position.getFirstCell().getY() + 1, color));
            position.setSecondCell(new Cell(position.getSecondCell().getX(), position.getSecondCell().getY() + 1, color));
            position.setThirdCell(new Cell(position.getThirdCell().getX(), position.getThirdCell().getY() + 1, color));
            position.setForthCell(new Cell(position.getForthCell().getX(), position.getForthCell().getY() + 1, color));

        }
        thisField.addFigure(this);

        if (!able) {
            thisField.update();
            if (ableToControl) {
                redrawField();
                recreateFigure();
            }
        }
    }

    public void assign(Figure other) {
        this.position = other.position;
        this.type = other.type;
        this.color = other.color;
    }
}
