package model;

import static model.CellStatus.EMPTY;

public class Field {
    public static Cell[][] field;
    public static int score;
    public Field() {
        score = 0;
        field = new Cell[10][16];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 16; j++){
                field [i][j] = new Cell(i, j);
            }
        }
    }

    public static int deleteLines() {
        int answer = 0;
        for (int j = 0; j < 16; j++) {
            boolean flag = true;
            for (int i = 0; i < 10; i++) {
                flag = field[i][j].getStatus() != EMPTY && flag;
            }
            if (flag) {
                for (int x = 0; x < 10; x++) {
                    for (int y = j; y > 0; y--) {
                        field[x][y].setStatus(field[x][y - 1].getStatus());
                    }
                    field[x][0].setStatus(EMPTY);
                }
                answer++;
            }
        }
        return answer;
    }
}
