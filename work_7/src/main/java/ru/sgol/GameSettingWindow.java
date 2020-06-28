package ru.sgol;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameSettingWindow extends JFrame {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final int MIN_WIN_LENGTH = 3;
    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 10;
    private static final String FIELD_SIZE_PREFIX = "Размер поля: ";
    private static final String WIN_SIZE_PREFIX = "Длинна победы: ";

    private GameWindow gameWindow;
    private JRadioButton humVsAi;
    private JRadioButton humVsHum;
    private JSlider sliderFieldSize;
    private JSlider sliderWinLen;


    public GameSettingWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setSize(WIDTH, HEIGHT);
        Rectangle gameWindowBounds = gameWindow.getBounds();
        setLocation((int) gameWindowBounds.getX() - WIDTH / 2,
                (int) gameWindowBounds.getY() - HEIGHT / 2);
        setResizable(false);
        setTitle("Настройки игры");

        setLayout(new GridLayout(8, 1));

        JButton btnStart = new JButton("Создать новую игру");
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        add(btnStart);

        addGameModeRadioButtons();
        addGameSliders();

    }

    private void addGameModeRadioButtons() {
        JLabel jLabel = new JLabel("Выбрать режипм");
        add(jLabel);
        humVsAi = new JRadioButton("человек против компьютера");
        humVsHum = new JRadioButton("человек против человека");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(humVsAi);
        buttonGroup.add(humVsHum);

        humVsAi.setSelected(true);

        add(humVsAi);
        add(humVsHum);

    }

    private void addGameSliders() {
        final JLabel lbFieldSize = new JLabel(FIELD_SIZE_PREFIX + MIN_FIELD_SIZE);
        final JLabel lbWinLen = new JLabel(WIN_SIZE_PREFIX + MIN_WIN_LENGTH);

        sliderFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
        sliderWinLen = new JSlider(MIN_WIN_LENGTH, MIN_FIELD_SIZE, MIN_FIELD_SIZE);

        sliderFieldSize.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int curVal = sliderFieldSize.getValue();
                lbFieldSize.setText(FIELD_SIZE_PREFIX + curVal);
                sliderWinLen.setMaximum(curVal);
            }
        });

        sliderWinLen.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                lbWinLen.setText(WIN_SIZE_PREFIX + sliderWinLen.getValue());
            }
        });

        add(lbFieldSize);
        add(sliderFieldSize);
        add(lbWinLen);
        add(sliderWinLen);
    }

    private void startGame() {
        int gameMode;
        if (humVsHum.isSelected()) {
            gameMode = GameConstant.HUM_VS_HUM_MODE_CODE;
        } else if (humVsAi.isSelected()) {
            gameMode = GameConstant.HUM_VS_COMP_MODE_CODE;
        } else {
            throw new RuntimeException("Некорректный режим игры");
        }

        gameWindow.startGame(gameMode, sliderFieldSize.getValue(), sliderWinLen.getValue());
        setVisible(false);
    }
}
