package view;

import controller.Controller;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;


public class MainFrame extends JFrame {
    final int buttonX = 140;
    final int buttonY = 250;
    private static JPanel panel;
    private final Controller controller = new Controller(this);
    public static Field thisField;

    MainFrame (String s) {
        super (s);
        thisField = new Field();
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
        g.drawImage((new ImageIcon("resources/ChangeButton.png").getImage()), 488, 679, panel);

        add(panel);
        setSize (600, 800);
    }
    public static FigureCreator creator = new FigureCreator();
    public static Figure currentFigure = creator.currentFigure;
    public static Figure nextFigure = creator.nextFigure;

    private static ImageIcon chooseCellIcon(CellStatus status) {
        ImageIcon cellIcon;
        switch (status) {
            case YELLOW:{
                cellIcon = new ImageIcon("resources/YellowCell.png");
                break;
            }
            case GREEN:{
                cellIcon = new ImageIcon("resources/GreenCell.png");
                break;
            }
            case BLUE:{
                cellIcon = new ImageIcon("resources/BlueCell.png");
                break;
            }
            case ORANGE:{
                cellIcon = new ImageIcon("resources/OrangeCell.png");
                break;
            }
            case RED:{
                cellIcon = new ImageIcon("resources/RedCell.png");
                break;
            }
            case CYAN:{
                cellIcon = new ImageIcon("resources/CyanCell.png");
                break;
            }
            case PURPLE: {
                cellIcon = new ImageIcon("resources/PurpleCell.png");
                break;
            }
            default: {
                cellIcon = new ImageIcon("resources/EmptyCell.png");
                break;
            }
        }
        return cellIcon;
    }

    private static ImageIcon chooseFigureIcon(Figure figure) {
        ImageIcon figureIcon;
        switch (figure.getColor()) {
            case YELLOW:{
                figureIcon = new ImageIcon("resources/Jfigure.png");
                break;
            }
            case GREEN:{
                figureIcon = new ImageIcon("resources/Lfigure.png");
                break;
            }
            case BLUE:{
                figureIcon = new ImageIcon("resources/Sfigure.png");
                break;
            }
            case ORANGE:{
                figureIcon = new ImageIcon("resources/Tfigure.png");
                break;
            }
            case RED:{
                figureIcon = new ImageIcon("resources/Zfigure.png");
                break;
            }
            case CYAN:{
                figureIcon = new ImageIcon("resources/LINEfigure.png");
                break;
            }
            default: {
                figureIcon = new ImageIcon("resources/SQUAREfigure.png");
                break;
            }
        }
        return figureIcon;
    }
    
    public static void redrawField() {
        Graphics g = panel.getGraphics();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 16; j++) {
                g.drawImage(chooseCellIcon(thisField.field[i][j].getStatus()).getImage(),
                        x + i * cellWidth, y + j * cellWidth, panel);
            }
        }
        g.drawImage(chooseFigureIcon(nextFigure).getImage(),471, 53, panel);
        g.setColor(Color.WHITE);
        g.fillRect(473, 150, 94, 36);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Helvetica", Font.PLAIN, 20));
        g.drawString(Integer.toString(thisField.score), 500, 170);

    }

    public static void drawEndingScreen() {
        Graphics g = panel.getGraphics();
        g.drawImage((new ImageIcon("resources/EndingBackground.png").getImage()), 0, 0, panel);
        g.setColor(new Color(255, 128, 0));
        g.setFont(new Font("Helvetica", Font.PLAIN, 50));
        g.drawString(Integer.toString(thisField.score), 290, 415);

    }    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                () -> new MainFrame("Тетрис")
        );

        TimerTask timerTask = new TimerOfMove();
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 400);
    }
}
