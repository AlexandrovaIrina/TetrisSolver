package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {
    final int buttonX = 140;  // скорректировать расположение кнопок
    final int buttonY = 250;
    private JPanel panel;
    private final Controller controller = new Controller(this);
    MainFrame (String s) {
        super (s);
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage((new ImageIcon("resources/Background.png").getImage()),
                        0, 0, this);
                g.drawImage((new ImageIcon("resources/Start.png")).getImage(),
                        buttonX, buttonY, panel);
                g.drawImage((new ImageIcon("resources/Solver.png").getImage()),
                        buttonX, buttonY + 92, panel);
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
        g.drawImage((new ImageIcon("resources/GameField.png").getImage()), 0, 0, panel);
        final int x = 54;
        final int y = 112;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 16; j++) {
                g.drawImage((new ImageIcon("resources/EmptyCell.png").getImage()),
                        x + i*38, y + j*38, panel);
            }
        }
        add(panel);
        setSize (600, 800);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                () -> new MainFrame("Тетрис")
        );
    }
}
