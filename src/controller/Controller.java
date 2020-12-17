package controller;

import view.*;

import java.awt.event.*;


public class Controller {

    private final MainFrame mainFrame;
    private final ButtonMouseMotionEvent mouseEvent;
    public Controller (MainFrame mainFrame) {
        this.mouseEvent = new ButtonMouseMotionEvent();
        this.mainFrame = mainFrame;
    }

    final int buttonX = 140;
    final int buttonY = 250;

    public ButtonMouseMotionEvent getMouseEvent() {
        return mouseEvent;
    }

    public class ButtonMouseMotionEvent extends MouseMotionAdapter implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            if (x >= buttonX && x <= buttonX + 300) {
                if (y >= buttonY && y <= buttonY + 60 ||
                y >= buttonY + 90 && y <= buttonY + 150) {
                    super.mouseDragged(e);
                    mainFrame.drawGameField();
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

    public class KeyboardListener implements FocusListener {

        public void focusGained(FocusEvent e) {
        }

        public void focusLost(FocusEvent e) {
        }
    }
}
