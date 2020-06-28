package ru.sgol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFieldPanel extends JPanel {

    private int fieldSize;
    private int fieldWidth;
    private int fieldHeight;
    private int cellWight;
    private int cellHeight;
    private GameController gameController;

    private static final int IN_FIELD_DOT_PADDING = 5;

    private static final String MSG_DRAW = "Ничья!";
    private static final String MSG_MIN_AI = "Победил компьютер!";
    private static final String MSG_WIN_HUM = "Победил игрок!";


    public GameFieldPanel() {
        gameController = new GameController(this);
        setBackground(Color.LIGHT_GRAY);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                System.out.printf("Mouse button %s was clicked at X=%d Y=%d%n", e.getButton(), e.getX(), e.getY());
                update(e);
            }
        });
    }

    public void startGame(int gameMode, int fieldSize, int winLength) {
        this.fieldWidth = getWidth();
        this.fieldHeight = getHeight();
        this.cellWight = this.fieldWidth / fieldSize;
        this.cellHeight = this.fieldHeight / fieldSize;
        this.fieldSize = fieldSize;
        gameController.initialize(gameMode, fieldSize, winLength);
        repaint();

    }

    private void update(MouseEvent e) {
        if (!gameController.isGameActive()) return;
        if (gameController.isGameOver()) return;
        int cellX = e.getX() / cellWight;
        int cellY = e.getY() / cellHeight;
        gameController.processMove(cellX, cellY);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g) {
        if (!gameController.isGameActive()) return;
        g.setColor(Color.BLACK);
        for (int i = 0; i < fieldSize; i++) {
            int x = i * cellWight;
            g.drawLine(x, 0, x, this.fieldHeight);

            int y = i * cellHeight;
            g.drawLine(0, y, this.fieldWidth, y);
        }

        int[][] field = gameController.getField();

        for (int y = 0; y < fieldSize; y++) {
            for (int x = 0; x < fieldSize; x++) {

                if (gameController.isEmptyCell(x, y)) continue;

                if (field[y][x] == GameConstant.DOT_O) {
                    g.setColor(new Color(1, 1, 255));
                    g.drawOval(x * cellWight + IN_FIELD_DOT_PADDING,
                            y * cellHeight + IN_FIELD_DOT_PADDING,
                            cellWight - IN_FIELD_DOT_PADDING * 2,
                            cellHeight - IN_FIELD_DOT_PADDING * 2);
                } else if (field[y][x] == GameConstant.DOT_X) {
                    g.setColor(Color.RED);

                    g.drawLine(x * cellWight + IN_FIELD_DOT_PADDING,
                            y * cellHeight + IN_FIELD_DOT_PADDING,
                            (x + 1) * cellWight - IN_FIELD_DOT_PADDING,
                            (y + 1) * cellHeight - IN_FIELD_DOT_PADDING);

                    g.drawLine((x + 1) * cellWight - IN_FIELD_DOT_PADDING,
                            y * cellHeight + IN_FIELD_DOT_PADDING,
                            x * cellWight + IN_FIELD_DOT_PADDING,
                            (y + 1) * cellHeight - IN_FIELD_DOT_PADDING);
                } else {
                    throw new RuntimeException();
                }
            }
        }

        if (gameController.isGameOver()) {
            showMessageGameOver (g);
        }

    }

    private void showMessageGameOver (Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,150,getWidth(),70);
        g.setColor(Color.ORANGE);
        g.setFont(new Font("Times new roman", Font.BOLD, 32));
        switch (gameController.getStateGameOver()) {
            case GameConstant.STATE_DRAW :
                g.drawString(MSG_DRAW, 180, 200 );
                break;
            case GameConstant.STATE_WIN_AI :
                g.drawString(MSG_MIN_AI, 20, 200 );
                break;
            case GameConstant.STATE_WIN_HUMAN :
                g.drawString(MSG_WIN_HUM, 70, 200 );
                break;
            default:
                throw new RuntimeException();
        }
    }

}
