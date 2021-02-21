package controller;

import static model.Move.*;
import static view.MainFrame.*;

import model.FigurePosition;
import view.*;

import java.awt.event.*;


public class Controller {

    private final MainFrame mainFrame;
    private final ButtonMouseMotionEvent mouseEvent;
    public Controller (MainFrame mainFrame) {
        this.mouseEvent = new ButtonMouseMotionEvent();
        this.mainFrame = mainFrame;
    }

    public static boolean fieldOpened = false;
    final int buttonX = 140;
    final int buttonY = 250;
    final int cellWidth = 38;
    public boolean ableToMake = true;
    public ButtonMouseMotionEvent getMouseEvent() {
        return mouseEvent;
    }

    public class ButtonMouseMotionEvent extends MouseMotionAdapter implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            if (! fieldOpened && x >= buttonX && x <= buttonX + 300) {
                if (y >= buttonY && y <= buttonY + 60 ||
                        y >= buttonY + 90 && y <= buttonY + 150) {
                    fieldOpened = true;
                    mainFrame.drawGameField();
                }
            }
            if (fieldOpened) {
                if (x >= 488 && x <= 488 + cellWidth &&
                        y >= 641 && y <= 641 + cellWidth) {
                    if (ableToMake) {
                        System.out.println("rotation");
                    }
                }
                if (y >= 679 && y <= 679 + cellWidth) {
                    FigurePosition lastPosition = new FigurePosition(currentFigure.getPosition());

                    if (x >= 488 && x <= 488 + cellWidth)
                        if (ableToMake) {
                            model.FigureCreator.swapFigures(currentFigure, nextFigure);
                        }

                    if (x >= 450 && x <= 450 + cellWidth) {
                        if (ableToMake) {
                            currentFigure.movePosition(LEFT);
                            FigurePosition newPosition = currentFigure.getPosition();
                        }
                    }
                    if (x >= 526 && x <= 526 + cellWidth) {
                        if (ableToMake) {
                            currentFigure.movePosition(RIGHT);
                            FigurePosition newPosition = currentFigure.getPosition();
                        }
                    }
                }
            }
            redrawField();
            ableToMake = false;
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            ableToMake = true;
        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
        }
    }

}
