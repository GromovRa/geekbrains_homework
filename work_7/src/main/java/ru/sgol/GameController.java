package ru.sgol;

import java.util.Random;


public class GameController {


    private int[][] field;
    private int fieldSize;
    private int winLength;
    private static final Random RANDOM = new Random();
    private int gameMode;



    private int stateGameOver;

    private boolean isGameOver;
    private boolean gameActive = true;

    private int playerOneDot;
    private int playerTwoDot;

    private GameFieldPanel gameFieldPanel;

    public GameController(GameFieldPanel gameFieldPanel) {
        this.gameFieldPanel = gameFieldPanel;
    }
    public int getStateGameOver() {
        return stateGameOver;
    }
    public boolean isGameOver() {
        return isGameOver;
    }

    public boolean isGameActive() {
        return gameActive;
    }

    public void initialize(int gameMode, int fieldSize, int winLength) {
        this.gameMode = gameMode;
        this.fieldSize = fieldSize;
        this.winLength = winLength;
        this.gameActive = true;
        this.isGameOver = false;

        this.playerOneDot = GameConstant.DOT_O;
        this.playerTwoDot = GameConstant.DOT_X;

        this.field = new int[fieldSize][fieldSize];
    }

    public int[][] getField() {
        return field;
    }

    private void setGameOver(int gameOverState) {
        isGameOver = true;
        stateGameOver = gameOverState;
    }

    public void processMove(int cellX, int cellY) {
        System.out.printf("Координаты ячейки: %d  %d", cellX, cellY);
        field[cellY][cellX] = playerOneDot;
        gameFieldPanel.repaint();
        if (iSWinnerSequenceExistFor(playerOneDot)) {
            setGameOver(GameConstant.STATE_WIN_HUMAN);
            return;
        }
        if (isDraw()) {
            setGameOver(GameConstant.STATE_DRAW);
            return;
        }

        aiTurn(playerTwoDot, playerOneDot);
        gameFieldPanel.repaint();
        if (iSWinnerSequenceExistFor(playerTwoDot)) {
            setGameOver(GameConstant.STATE_WIN_AI);
            return;
        }
        if (isDraw()) {
            setGameOver(GameConstant.STATE_DRAW);

        }

    }

    private void aiTurn(int dotAi, int dotHuman) {
        if (turnAIWinCell(dotAi)) {
            return;
        }

        if (turnHumanWinCell(dotAi, dotHuman)) {
            return;
        }

        int x;
        int y;
        do {
            x = RANDOM.nextInt(fieldSize);
            y = RANDOM.nextInt(fieldSize);
        } while (!isEmptyCell(x, y));
        field[y][x] = dotAi;
    }

    private boolean turnAIWinCell(int dotAi) {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (isEmptyCell(j, i)) {
                    field[i][j] = dotAi;

                    if (iSWinnerSequenceExistFor(dotAi)) {
                        return true;
                    }
                    field[i][j] = GameConstant.DOT_EMPTY;
                }
            }
        }
        return false;
    }

    private boolean turnHumanWinCell(int dotAi, int dotHuman) {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (isEmptyCell(j, i)) {
                    field[i][j] = dotHuman;

                    if (iSWinnerSequenceExistFor(dotHuman)) {
                        field[i][j] = dotAi;
                        return true;
                    }

                    field[i][j] = GameConstant.DOT_EMPTY;
                }

            }

        }
        return false;
    }

    private boolean iSWinnerSequenceExistFor(int dot) {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {

                if (checkLine(i, j, 1, 0, winLength, dot)) {
                    return true;
                }
                if (checkLine(i, j, 1, 1, winLength, dot)) {
                    return true;
                }
                if (checkLine(i, j, 0, 1, winLength, dot)) {
                    return true;
                }
                if (checkLine(i, j, 1, -1, winLength, dot)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkLine(int x, int y, int offsetX, int offsetY, int winLength, int dot) {
        final int xEnd = x + (winLength - 1) * offsetX;
        final int yEnd = y + (winLength - 1) * offsetY;

        if (!isValidCell(xEnd, yEnd)) {
            return false;
        }

        for (int i = 0; i < winLength; i++) {
            if (field[y + i * offsetY][x + i * offsetX] != dot) {
                return false;
            }
        }
        return true;
    }

    private boolean isDraw() {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (field[i][j] == GameConstant.DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSize && y >= 0 && y < fieldSize;
    }

    public boolean isEmptyCell(int x, int y) {
        return field[y][x] == GameConstant.DOT_EMPTY;
    }

}
