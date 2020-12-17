package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel panel;
    private final Controller controller = new Controller(this);
    MainFrame (String s) {
        super (s);
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                final int buttonX = 140;  // скорректировать расположение кнопок
                final int buttonY = 250;
                g.drawImage((new ImageIcon("resources/Background.png").getImage()), 0, 0, this);
                g.drawImage((new ImageIcon("resources/Start.png")).getImage(), // заменить изображения
                        buttonX, buttonY, this);
                g.drawImage((new ImageIcon("resources/Solver.png").getImage()),
                        buttonX, buttonY + 90, this);  // скорректировать "+20"
                this.addMouseListener(controller.getMouseEvent());
            }
        };
        add(panel);
        setSize (600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible (true);
    }
    public void drawGameField() {
        Graphics g = panel.getGraphics();
        g.drawImage((new ImageIcon("resources/GameField.png").getImage()), 0, 0, this);
        add(panel);
        setSize (600, 800);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                () -> new MainFrame("Тетрис")
        );
    }
}
