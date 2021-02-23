package solver;

import model.Cell;
import model.Figure;
import model.FigurePosition;

import static model.CellStatus.EMPTY;
import static model.FigureCreator.recreateFigure;
import static model.Move.*;
import static view.MainFrame.*;

public class TetrisSolver {
    private int sumOfHeight;
    private int numberOfClears;
    private int numberOfHoles;
    private int numberOfBlockades;
    private int nearWall;
    private int nearBlock;
    private int nearFloor;

    public TetrisSolver() {
        sumOfHeight = 0;
        numberOfBlockades = 0;
        numberOfHoles = 0;
        numberOfClears = 0;
        nearWall = 0;
        nearBlock = 0;
        nearFloor = 0;
    }

    public double calculateTheScore(Figure figure) {
        setCurrentNumberOfBlockades();
        setCurrentNumberOfClears();
        setCurrentNumberOfHoles();
        setCurrentSumOfHeight();
        setCurrentNumberOfCells(figure.getPosition());
        return -4.7 * sumOfHeight + 8.0 * numberOfClears - 4.61 * numberOfHoles - 3.5 * numberOfBlockades
                + 5.0 * nearBlock + 3.5 * nearWall + 7.0 * nearFloor;
    }

    public void solve(int step) {
        switch(step) {
            case 0: {
                Figure bestFigure = new Figure(currentFigure.getType());
                double bestScore = -1e6;
                Figure usingFigure = new Figure(currentFigure.getType());
                thisField.deleteFigure(currentFigure);
                usingFigure.falling();
                thisField.deleteFigure(usingFigure);
                for (int i = 0; i < 4; i++) {
                    Figure rotationState = new Figure(usingFigure.getType());
                    rotationState.assign(usingFigure);
                    while (usingFigure.getPosition().ableToMove(LEFT)) {
                        usingFigure.movePosition(LEFT);
                        thisField.deleteFigure(usingFigure);
                    }
                    while (usingFigure.getPosition().ableToMove(RIGHT)) {
                        Figure upperState = new Figure(usingFigure.getType());
                        upperState.assign(usingFigure);
                        while (usingFigure.getPosition().ableToFall()) {
                            usingFigure.falling();
                            thisField.deleteFigure(usingFigure);
                        }
                        thisField.addFigure(usingFigure);
                        double currentScore = calculateTheScore(usingFigure);
                        if (currentScore - bestScore > 0.0001) {
                            bestScore = currentScore;
                            bestFigure.assign(usingFigure);
                        }
                        usingFigure.assign(upperState);
                        usingFigure.movePosition(RIGHT);
                        thisField.deleteFigure(usingFigure);
                    }
                    usingFigure.assign(rotationState);
                    if (!usingFigure.getPosition().ableToRotate()) {
                        if (usingFigure.getPosition().ableToFall()) {
                            usingFigure.falling();
                            thisField.deleteFigure(usingFigure);
                        }
                        if (!usingFigure.getPosition().ableToRotate()) {
                            break;
                        }
                    }
                    usingFigure.rotatePosition();
                    thisField.deleteFigure(usingFigure);
                }
                thisField.deleteFigure(usingFigure);
                currentFigure.assign(bestFigure);
                thisField.addFigure(currentFigure);
                break;
            }
            case 1:{
                thisField.update();
                break;
            }
            default:{
                recreateFigure();
                thisField.addFigure(currentFigure);
                break;
            }
        }
    }

    private void setCurrentSumOfHeight() {
        sumOfHeight = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 16; j++) {
                if (thisField.field[i][j].getStatus() != EMPTY)
                    sumOfHeight += 16 - j;
            }
        }
    }

    private void setCurrentNumberOfClears() {
        numberOfClears = thisField.deleteLines(false);
    }

    private void setCurrentNumberOfHoles() {
        numberOfHoles = 0;
        for (int i = 0; i < 10; i++) {
            boolean metABlock = false;
            for (int j = 0; j < 16; j++) {
                if (thisField.field[i][j].getStatus() == EMPTY && metABlock) numberOfHoles++;
                if (thisField.field[i][j].getStatus() != EMPTY) metABlock = true;
            }
        }
    }

    private void setCurrentNumberOfBlockades() {
        numberOfBlockades = 0;
        for (int i = 0; i < 10; i++) {
            boolean metAHole = false;
            for (int j = 15; j >= 0; j--) {
                if (thisField.field[i][j].getStatus() != EMPTY && metAHole) numberOfBlockades++;
                if (thisField.field[i][j].getStatus() == EMPTY) metAHole = true;
            }
        }
    }

    private void countNearCells(Cell cell) {
        Cell[] nearCells = new Cell[4];
        nearCells[0] = new Cell(cell.getX() - 1, cell.getY(), cell.getStatus());
        nearCells[1] = new Cell(cell.getX(), cell.getY() - 1, cell.getStatus());
        nearCells[2] = new Cell(cell.getX() + 1, cell.getY(), cell.getStatus());
        nearCells[3] = new Cell(cell.getX(), cell.getY() + 1, cell.getStatus());

        for (int i = 0; i < 4; i++) {
            if (nearCells[i].getX() < 0 || nearCells[i].getX() > 9) nearWall++;
            else if (nearCells[i].getY() > 15 || nearCells[i].getY() < 0) nearFloor++;
            else {
                if (thisField.field[nearCells[i].getX()][nearCells[i].getY()].getStatus() != EMPTY) nearBlock++;
            }
        }
    }

    private void setCurrentNumberOfCells(FigurePosition position) {
        nearWall = 0;
        nearBlock = 0;
        nearFloor = 0;
        countNearCells(position.getFirstCell());
        countNearCells(position.getSecondCell());
        countNearCells(position.getThirdCell());
        countNearCells(position.getForthCell());
    }
}
