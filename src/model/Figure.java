package model;

import static model.CellStatus.*;

public class Figure {
    private final FigureType type;
    private final CellStatus color;
    private FigurePosition position;
    public Figure(FigureType type) {
        this.type = type;
        position = new FigurePosition(type);
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
    }
    public FigureType getType() {
        return type;
    }

    public CellStatus getColor() {
        return color;
    }

}
