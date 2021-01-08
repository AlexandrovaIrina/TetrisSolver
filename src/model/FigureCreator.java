package model;

import static model.FigureType.*;

public class FigureCreator {
    public Figure currentFigure;
    public Figure nextFigure;

    public FigureCreator() {
        currentFigure = new Figure(LINE);
        nextFigure = new Figure(SQUARE);
    }

    public void setCurrentFigure(Figure figure) {
        currentFigure = figure;
    }

    public void setNextFigure(Figure nextFigure) {
        this.nextFigure = nextFigure;
    }

    public void recreateFigure() {
        currentFigure = nextFigure;
        switch (nextFigure.getType()) {
            case L: {
                nextFigure = new Figure(J);
                break;
            }
            case J: {
                nextFigure = new Figure(T);
                break;
            }
            case T: {
                nextFigure = new Figure(S);
                break;
            }
            case S: {
                nextFigure = new Figure(Z);
                break;
            }
            case Z: {
                nextFigure = new Figure(LINE);
                break;
            }
            case LINE: {
                nextFigure = new Figure(SQUARE);
                break;
            }
            case SQUARE: {
                nextFigure = new Figure(L);
                break;
            }
        }
    }
}
