package model;

import java.util.Random;

import static controller.Controller.*;
import static model.FigureType.*;
import static view.MainFrame.drawEndingScreen;
import static view.MainFrame.thisField;

public class FigureCreator {
    public static Figure currentFigure;
    public static Figure nextFigure;

    public FigureCreator() {
        currentFigure = getRandomFigure();
        nextFigure = getRandomFigure();
    }

    private static Figure getRandomFigure(){
        final Random random = new Random();
        switch (random.nextInt(7)) {
            case 0: {
                return new Figure(J);
            }
            case 1: {
                return new Figure(T);
            }
            case 2: {
                return new Figure(S);
            }
            case 3: {
                return new Figure(Z);
            }
            case 4: {
                return new Figure(LINE);
            }
            case 5: {
                return new Figure(SQUARE);
            }
            default: {
                return new Figure(L);
            }
        }
    }

    public static void recreateFigure() {
        currentFigure.assign(nextFigure);
        if (!currentFigure.getPosition().ableToFall()) {
            fieldOpened = false;
            endOfGame = true;
            if (ableToControl) drawEndingScreen();
            return;
        }
        nextFigure.assign(getRandomFigure());
    }
    public static void swapFigures(Figure currentFigure, Figure nextFigure) {
        thisField.deleteFigure(currentFigure);

        if (currentFigure.getPosition().ableToFall() && currentFigure.getPosition().getFirstCell().getY() > 0) {
            final Figure next = new Figure(nextFigure.getType(), currentFigure.getPosition());
            nextFigure.assign(new Figure(currentFigure.getType()));
            currentFigure.assign(next);
        }
        thisField.addFigure(currentFigure);

    }
}
