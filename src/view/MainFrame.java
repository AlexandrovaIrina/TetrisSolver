package view;

import controller.Controller;
import model.Cell;
import model.Figure;
import model.FigureCreator;
import model.FigurePosition;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {
    final int buttonX = 140;
    final int buttonY = 250;
    private static JPanel panel;
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

    private static final int x = 54;
    private static final int y = 112;
    private static final int cellWidth = 38;

    public void drawGameField() {
        Graphics g = panel.getGraphics();
        g.drawImage((new ImageIcon("resources/GameField.png").getImage()), 0, 0, panel);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 16; j++) {
                g.drawImage((new ImageIcon("resources/EmptyCell.png").getImage()),
                        x + i * cellWidth, y + j * cellWidth, panel);
            }
        }
        g.drawImage((new ImageIcon("resources/RotationButton.png").getImage()), 488, 641, panel);
        g.drawImage((new ImageIcon("resources/LeftMovingButton.png").getImage()), 450, 679, panel);
        g.drawImage((new ImageIcon("resources/RightMoveButton.png").getImage()), 526, 679, panel);

        add(panel);
        setSize (600, 800);
    }
    public static FigureCreator creator = new FigureCreator();
    public static Figure currentFigure = creator.currentFigure;
    public static Figure nextFigure = creator.nextFigure;
    public static void redrawFigure(Figure figure, FigurePosition lastPosition, FigurePosition newPosition) {
        Graphics g = panel.getGraphics();
        ImageIcon cellIcon;
        switch (figure.getType()) {
            case J:{
                cellIcon = new ImageIcon("resources/YellowCell.png");
                break;
            }
            case L:{
                cellIcon = new ImageIcon("resources/GreenCell.png");
                break;
            }
            case S:{
                cellIcon = new ImageIcon("resources/BlueCell.png");
                break;
            }
            case T:{
                cellIcon = new ImageIcon("resources/OrangeCell.png");
                break;
            }
            case Z:{
                cellIcon = new ImageIcon("resources/RedCell.png");
                break;
            }
            case LINE:{
                cellIcon = new ImageIcon("resources/CyanCell.png");
                break;
            }
            default:
                cellIcon = new ImageIcon("resources/PurpleCell.png");
        }

        final Cell firstCell = lastPosition.getFirstCell();
        final Cell secondCell = lastPosition.getSecondCell();
        final Cell thirdCell = lastPosition.getThirdCell();
        final Cell forthCell = lastPosition.getForthCell();

        final Cell newFirstCell = newPosition.getFirstCell();
        final Cell newSecondCell = newPosition.getSecondCell();
        final Cell newThirdCell = newPosition.getThirdCell();
        final Cell newForthCell = newPosition.getForthCell();

        g.drawImage((new ImageIcon("resources/EmptyCell.png").getImage()),
                x + (firstCell.getX() - 1) * cellWidth, y + (firstCell.getY() - 1) * cellWidth, panel);
        g.drawImage((new ImageIcon("resources/EmptyCell.png").getImage()),
                x + (secondCell.getX() - 1) * cellWidth, y + (secondCell.getY() - 1) * cellWidth, panel);
        g.drawImage((new ImageIcon("resources/EmptyCell.png").getImage()),
                x + (thirdCell.getX() - 1) * cellWidth, y + (thirdCell.getY() - 1) * cellWidth, panel);
        g.drawImage((new ImageIcon("resources/EmptyCell.png").getImage()),
                x + (forthCell.getX() - 1) * cellWidth, y + (forthCell.getY() - 1) * cellWidth, panel);

        g.drawImage(cellIcon.getImage(),x + (newFirstCell.getX() - 1) * cellWidth,
                y + (newFirstCell.getY() - 1) * cellWidth, panel);
        g.drawImage(cellIcon.getImage(),x + (newSecondCell.getX() - 1) * cellWidth,
                y + (newSecondCell.getY() - 1) * cellWidth, panel);
        g.drawImage(cellIcon.getImage(),x + (newThirdCell.getX() - 1) * cellWidth,
                y + (newThirdCell.getY() - 1) * cellWidth, panel);
        g.drawImage(cellIcon.getImage(),x + (newForthCell.getX() - 1) * cellWidth,
                y + (newForthCell.getY() - 1) * cellWidth, panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                () -> new MainFrame("Тетрис")
        );

    }
}
