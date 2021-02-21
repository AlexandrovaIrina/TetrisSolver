package model;

import java.util.Random;

import static model.CellStatus.EMPTY;

import static model.FigureType.*;
import static view.MainFrame.field;

public class FigureCreator {
    public static Figure currentFigure;
    public static Figure nextFigure;

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

    public static void recreateFigure() {
        currentFigure.assign(nextFigure);
        final Random random = new Random();
        
        switch (random.nextInt(7)) {
            case 0: {
                nextFigure.assign(new Figure(J));
                break;
            }
            case 1: {
                nextFigure.assign(new Figure(T));
                break;
            }
            case 2: {
                nextFigure.assign(new Figure(S));
                break;
            }
            case 3: {
                nextFigure.assign(new Figure(Z));
                break;
            }
            case 4: {
                nextFigure.assign(new Figure(LINE));
                break;
            }
            case 5: {
                nextFigure.assign(new Figure(SQUARE));
                break;
            }
            case 6: {
                nextFigure.assign(new Figure(L));
                break;
            }
        }
    }
    public static void swapFigures(Figure currentFigure, Figure nextFigure) {

        field[currentFigure.getPosition().getFirstCell().getX()]
                [currentFigure.getPosition().getFirstCell().getY()].setStatus(EMPTY);

        field[currentFigure.getPosition().getSecondCell().getX()]
                [currentFigure.getPosition().getSecondCell().getY()].setStatus(EMPTY);

        field[currentFigure.getPosition().getThirdCell().getX()]
                [currentFigure.getPosition().getThirdCell().getY()].setStatus(EMPTY);

        field[currentFigure.getPosition().getForthCell().getX()]
                [currentFigure.getPosition().getForthCell().getY()].setStatus(EMPTY);

        if (currentFigure.getPosition().ableToFall() && currentFigure.getPosition().getFirstCell().getY() > 0) {
            final Figure next = new Figure(nextFigure.getType(), currentFigure.getPosition());
            nextFigure.assign(new Figure(currentFigure.getType()));
            currentFigure.assign(next);
        }
        field[currentFigure.getPosition().getFirstCell().getX()]
                [currentFigure.getPosition().getFirstCell().getY()].setStatus(currentFigure.getColor());

        field[currentFigure.getPosition().getSecondCell().getX()]
                [currentFigure.getPosition().getSecondCell().getY()].setStatus(currentFigure.getColor());

        field[currentFigure.getPosition().getThirdCell().getX()]
                [currentFigure.getPosition().getThirdCell().getY()].setStatus(currentFigure.getColor());

        field[currentFigure.getPosition().getForthCell().getX()]
                [currentFigure.getPosition().getForthCell().getY()].setStatus(currentFigure.getColor());
    }
}
