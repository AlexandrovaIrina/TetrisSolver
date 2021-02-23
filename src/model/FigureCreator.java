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
        currentFigure = new Figure(LINE);
        nextFigure = new Figure(SQUARE);
    }

    public static void recreateFigure() {
        currentFigure.assign(nextFigure);
        final Random random = new Random();
        if (!currentFigure.getPosition().ableToFall()) {
            fieldOpened = false;
            endOfGame = true;
            if (ableToControl) drawEndingScreen();
            return;
        }
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
        thisField.deleteFigure(currentFigure);

        if (currentFigure.getPosition().ableToFall() && currentFigure.getPosition().getFirstCell().getY() > 0) {
            final Figure next = new Figure(nextFigure.getType(), currentFigure.getPosition());
            nextFigure.assign(new Figure(currentFigure.getType()));
            currentFigure.assign(next);
        }
        thisField.addFigure(currentFigure);

    }
}
