
package model;

import controller.Controller;
import view.MainFrame;

import java.util.TimerTask;

import static controller.Controller.*;
import static view.MainFrame.currentFigure;
import static view.MainFrame.redrawField;

public class TimerOfMove extends TimerTask {

    private static int step = 0;

    @Override
    public void run() {
        if (fieldOpened) {
            completeTask();
        }
    }

    private void completeTask() {
        if (ableToControl) {
            currentFigure.falling();
            if (fieldOpened) MainFrame.redrawField();
        } else if (!endOfGame) {
            Controller.solver.solve(step);
            redrawField();
            step = (step + 1) % 3;
        }
    }

}
