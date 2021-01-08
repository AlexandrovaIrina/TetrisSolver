package controller;

import model.Figure;
import static model.FigureType.*;
import static model.Move.*;
import static view.MainFrame.redrawFigure;

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

    private boolean fieldOpened = false;
    final int buttonX = 140;
    final int buttonY = 250;
    final int cellWidth = 38;
    Figure currentFigure = new Figure(LINE);
    public ButtonMouseMotionEvent getMouseEvent() {
        return mouseEvent;
    }

    public class ButtonMouseMotionEvent extends MouseMotionAdapter implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            if (! fieldOpened && x >= buttonX && x <= buttonX + 300) {
                if (y >= buttonY && y <= buttonY + 60 ||
                y >= buttonY + 90 && y <= buttonY + 150) {
                    fieldOpened = true;
                    super.mouseDragged(e);
                    mainFrame.drawGameField();
                }
            }
            if (fieldOpened) {
                if (x >= 488 && x <= 488 + cellWidth &&
                        y >= 641 && y <= 641 + cellWidth) {
                    System.out.println("rotation");
                }
                if (y >= 679 && y <= 679 + cellWidth) {
                    FigurePosition lastPosition = currentFigure.getPosition();
                    if (x >= 450 && x <= 450 + cellWidth) {
                        System.out.println("move to the left");
                        redrawFigure(currentFigure, lastPosition, currentFigure.getPosition().movePosition(LEFT));
                    }
                    if (x >= 526 && x <= 526 + cellWidth) {
                        System.out.println("move to the right");
                        redrawFigure(currentFigure, lastPosition, currentFigure.getPosition().movePosition(RIGHT));
                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
        }
    }

}
