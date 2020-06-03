package ru.sgol;

import java.util.Random;
import java.util.Scanner;

public class TicTacGame {
    private static char[][] gameField;
    private static final char PLAYER_CHAR;
    private static final char COMP_CHAR;
    private static final char EMPTY_CHAR = '*';
    private static int SIZE;
    private static final Random RANDOM = new Random();
    private static final Scanner SCANNER = new Scanner(System.in);


    static {
        if (RANDOM.nextBoolean()) {
            PLAYER_CHAR = 'X';
            COMP_CHAR = '0';
        } else {
            PLAYER_CHAR = '0';
            COMP_CHAR = 'X';
        }
    }

    public static void main(String[] args) {
        initField(3);
        printField();
        System.out.println("Игрок ходит маркером " + PLAYER_CHAR);
        while (true) {
            getPlayerMove();
            printField();
            if (isWin()) {
                System.out.println("Игрок победил");
                break;
            }
            if (isDraw()) {
                System.out.println("Ничья");
                break;
            }
            getCompMove();
            printField();
            if (isWin()) {
                System.out.println("Компьютер победил");
                break;
            }
            if (isDraw()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончилась");

    }


    private static void initField(int size) {
        gameField = new char[size][size];
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField.length; j++) {
                gameField[i][j] = EMPTY_CHAR;
            }
        }
        SIZE = size;
    }

    private static boolean isPlayerInputValid(int x, int y) {
        return x <= SIZE && y <= SIZE && x >= 0 && y >= 0;
    }

    private static boolean isFieldEmpty(int x, int y) {
        return gameField[x][y] == EMPTY_CHAR;
    }

    /*
     * Победа, если:
     * В одной вертикали все символы пренадлежат одному игроку
     * В одной горизонтали все символы пренадлежат одному игроку
     * В одной вертикали все символы пренадлежат одному игроку*/
    private static boolean isWin() {
        char prevChar;

        for (char[] chArr : gameField) {
            prevChar = chArr[0];
            if (prevChar == EMPTY_CHAR) {
                continue;
            }
            for (int i = 1; i < chArr.length; i++) {
                if (chArr[i] != EMPTY_CHAR && chArr[i] == prevChar) {
                    if (i == chArr.length - 1) {
                        return true;
                    }
                } else {
                    break;
                }
            }

        }


        for (int i = 0; i < gameField.length; i++) {
            prevChar = gameField[0][i];
            if (prevChar == EMPTY_CHAR) {
                continue;
            }

            for (int j = 1; j < gameField[i].length; j++) {
                if (gameField[j][i] != EMPTY_CHAR && gameField[j][i] == prevChar) {
                    if (j == gameField[i].length - 1) {
                        return true;
                    }
                } else {
                    break;
                }

            }

        }


        int leftDiagonalPre = gameField[0][0];
        int rightDiagonalPre = gameField[gameField.length - 1][0];
        boolean isLeftCheckHasReason = true;
        boolean isRightCheckHasReason = true;

        for (int i = 1; i < gameField.length; i++) {
            if (isLeftCheckHasReason) {
                if (gameField[i][i] != EMPTY_CHAR && gameField[i][i] == leftDiagonalPre) {
                    if (i == gameField.length - 1) {
                        return true;
                    }
                } else {
                    isLeftCheckHasReason = false;
                }
            }

            if (isRightCheckHasReason) {
                if (gameField[gameField.length - i - 1][i] != EMPTY_CHAR
                        && gameField[gameField.length - i - 1][i] == rightDiagonalPre) {
                    if (i == gameField.length - 1) {
                        return true;
                    }
                } else {
                    isRightCheckHasReason = false;
                }
            }

            if (!isRightCheckHasReason && !isLeftCheckHasReason) {
                break;
            }
        }
        return false;
    }

    /*
     * Если все поля не пустые
     * */
    private static boolean isDraw() {
        for (char[] chArr : gameField) {
            if (chArr[0] == EMPTY_CHAR) {
                return false;
            }
            for (int i = 1; i < chArr.length; i++) {
                if (chArr[i] != EMPTY_CHAR) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void getPlayerMove() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isPlayerInputValid(x, y) || !isFieldEmpty(x, y));
        gameField[x][y] = PLAYER_CHAR;

    }

    private static void getCompMove() {
        int x, y;
        do {
            x = RANDOM.nextInt(SIZE);
            y = RANDOM.nextInt(SIZE);
        } while (!isFieldEmpty(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        gameField[x][y] = COMP_CHAR;
    }

    private static void printField() {

        System.out.println("================");

        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField.length; j++) {
                System.out.print(" " + gameField[j][i] + " |");

            }
            System.out.println();
        }

        System.out.println("================");
    }
}
