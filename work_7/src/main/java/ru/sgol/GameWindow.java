package ru.sgol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    public static final int Y = 300;
    public static final int X = 300;
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;
    GameSettingWindow gameSettingWindow;
    GameFieldPanel gameFieldPanel;

    public GameWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(X, Y, WIDTH, HEIGHT);
        setVisible(true);
        setTitle("Игра крестики-нолики");

        JButton buttonStart = createStartButton();
        JButton buttonExit = createExitButton();

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonExit);
        add(buttonPanel, BorderLayout.SOUTH);
        gameFieldPanel = new GameFieldPanel();
        add(gameFieldPanel,BorderLayout.CENTER);

        gameSettingWindow = new GameSettingWindow(this);

        setResizable(false);

    }

    public void startGame (int gameMode, int fieldSize, int winLength) {
     gameFieldPanel.startGame(gameMode,fieldSize,winLength);
    }

    private JButton createExitButton() {
        JButton buttonExit = new JButton("Выход");
        buttonExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });
        return buttonExit;
    }

    private JButton createStartButton() {
        JButton buttonStart = new JButton("Начать игру");
        buttonStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameSettingWindow.setVisible(true);
            }
        });
        return buttonStart;
    }
}

