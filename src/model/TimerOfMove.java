
package model;

import view.MainFrame;

import java.util.TimerTask;

import static view.MainFrame.currentFigure;
import static controller.Controller.fieldOpened;

public class TimerOfMove extends TimerTask {
    @Override
    public void run() {
        completeTask();
    }
    private void completeTask() {
        if (fieldOpened) {
            try {
                Thread.sleep(400);
                currentFigure.falling();
                MainFrame.redrawField();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
