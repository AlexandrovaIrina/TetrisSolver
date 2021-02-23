package model;

import static model.CellStatus.EMPTY;
import static view.MainFrame.thisField;

public class Field {
    public static Cell[][] field;
    public static int score;

    public Field() {
        score = 0;
        field = new Cell[10][16];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 16; j++) {
                field[i][j] = new Cell(i, j);
            }
        }
    }

    public static int deleteLines(boolean toDelete) {
        int answer = 0;
        for (int j = 0; j < 16; j++) {
            boolean flag = true;
            for (int i = 0; i < 10; i++) {
                flag = field[i][j].getStatus() != EMPTY && flag;
            }
            if (flag) {
                if (toDelete) {
                    for (int x = 0; x < 10; x++) {
                        for (int y = j; y > 0; y--) {
                            field[x][y].setStatus(field[x][y - 1].getStatus());
                        }
                        field[x][0].setStatus(EMPTY);
                    }
                }
                answer++;
            }
        }
        return answer;
    }

    public static void deleteFigure(Figure figure) {
        field[figure.getPosition().getFirstCell().getX()][figure.getPosition().getFirstCell().getY()].setStatus(EMPTY);
        field[figure.getPosition().getSecondCell().getX()][figure.getPosition().getSecondCell().getY()].setStatus(EMPTY);
        field[figure.getPosition().getThirdCell().getX()][figure.getPosition().getThirdCell().getY()].setStatus(EMPTY);
        field[figure.getPosition().getForthCell().getX()][figure.getPosition().getForthCell().getY()].setStatus(EMPTY);
    }

    public static void addFigure(Figure figure) {
        field[figure.getPosition().getFirstCell().getX()][figure.getPosition().getFirstCell().getY()].setStatus(figure.getColor());
        field[figure.getPosition().getSecondCell().getX()][figure.getPosition().getSecondCell().getY()].setStatus(figure.getColor());
        field[figure.getPosition().getThirdCell().getX()][figure.getPosition().getThirdCell().getY()].setStatus(figure.getColor());
        field[figure.getPosition().getForthCell().getX()][figure.getPosition().getForthCell().getY()].setStatus(figure.getColor());
    }

    public static void update() {
        int x = deleteLines(true);
        switch (x) {
            case 1: {
                thisField.score += 100;
                break;
            }
            case 2: {
                thisField.score += 300;
                break;
            }
            case 3: {
                thisField.score += 700;
                break;
            }
            case 4: {
                thisField.score += 1500;
                break;
            }
            default: {
                break;
            }
        }
    }
}
