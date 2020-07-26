package ru.sgol.gui;

import ru.sgol.chat.common.MessageLibrary;
import ru.sgol.net.MessageSocketThread;
import ru.sgol.net.MessageSocketThreadListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class ClientGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler, MessageSocketThreadListener {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JTextArea chatArea = new JTextArea();
    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField ipAddressField = new JTextField("127.0.0.1");
    private final JTextField portField = new JTextField("8181");
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Always on top", true);
    private final JTextField loginField = new JTextField("login");
    private final JPasswordField passwordField = new JPasswordField("123");
    private final JButton buttonLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JButton buttonDisconnect = new JButton("<html><b>Disconnect</b></html>");
    private final JTextField messageField = new JTextField();
    private final JButton buttonSend = new JButton("Send");

    private final JList<String> listUsers = new JList<>();

    private final String WINDOW_TITLE = "Chat Client";
    private MessageSocketThread messageSocketThread;
    private String nickname;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientGUI();
            }
        });
    }


    ClientGUI() {
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat");
        setSize(WIDTH, HEIGHT);
        setAlwaysOnTop(true);
        JScrollPane scrollPaneUsers = new JScrollPane(listUsers);
        JScrollPane scrollPaneChatArea = new JScrollPane(chatArea);
        scrollPaneUsers.setPreferredSize(new Dimension(100, 0));

        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setEditable(false);
        panelTop.add(ipAddressField);
        panelTop.add(portField);
        panelTop.add(cbAlwaysOnTop);
        panelTop.add(loginField);
        panelTop.add(passwordField);
        panelTop.add(buttonLogin);
        panelBottom.add(buttonDisconnect, BorderLayout.WEST);
        panelBottom.add(messageField, BorderLayout.CENTER);
        panelBottom.add(buttonSend, BorderLayout.EAST);
        panelBottom.setVisible(false);

        add(scrollPaneChatArea, BorderLayout.CENTER);
        add(scrollPaneUsers, BorderLayout.EAST);
        add(panelTop, BorderLayout.NORTH);
        add(panelBottom, BorderLayout.SOUTH);
        cbAlwaysOnTop.addActionListener(this);

        buttonSend.addActionListener(this);
        buttonLogin.addActionListener(this);
        buttonDisconnect.addActionListener(this);
        this.getRootPane().setDefaultButton(buttonSend);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == buttonSend) {
            sendMessage(messageField.getText());
        } else if (src == cbAlwaysOnTop) {
            setAlwaysOnTop(cbAlwaysOnTop.isSelected());
        } else if (src == buttonLogin) {
            Socket socket = null;
            switchVisibleModeOfPanels();
            try {
                socket = new Socket(ipAddressField.getText(), Integer.parseInt(portField.getText()));
                messageSocketThread = new MessageSocketThread(this, "Client" + loginField.getText(), socket);
            } catch (IOException ioException) {
                showError(ioException.getMessage());
            }

        } else if (src == buttonDisconnect) {

            messageSocketThread.close();
        } else {
            throw new RuntimeException("Unsupported action: " + src);
        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        StackTraceElement[] ste = e.getStackTrace();
        String msg = String.format("Exception in \"%s\": %s %s%n\t %s",
                t.getName(), e.getClass().getCanonicalName(), e.getMessage(), ste[0]);
        showError(msg);
    }

    /*
     * Отправка сообщений в сторону сервера
     */
    public void sendMessage(String msg) {
        if (msg.isEmpty()) {
            return;
        }

        putMessageInChat(nickname, msg);
        messageField.setText("");
        messageField.grabFocus();
        messageSocketThread.sendMessage(MessageLibrary.getTypeBroadcastClient(nickname, msg));
    }

    private void putMessageInChat(String writerName, String message) {
        String formatMessage = String.format("%s %s: %s%n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy:hh:mm")),
                writerName,
                message);
        chatArea.append(formatMessage);
        logMessageINtoFile(formatMessage);
    }

    private void logMessageINtoFile(String message) {
        File file = new File(LocalDateTime.now().format((DateTimeFormatter.ofPattern("dd.MM.yyyy"))) + "_log.txt");
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(file, true))) {
            pw.print(message);
        } catch (FileNotFoundException ignored) {
            showError(message);
        }
    }

    private void showError(String errorMsg) {
        JOptionPane.showMessageDialog(this, errorMsg, "Exception!", JOptionPane.ERROR_MESSAGE);
    }

    private void switchVisibleModeOfPanels() {
        panelBottom.setVisible(!panelBottom.isVisible());
        panelTop.setVisible(!panelTop.isVisible());
    }

    @Override
    public void onSocketReady(MessageSocketThread thread) {
        panelTop.setVisible(false);
        panelBottom.setVisible(true);
        messageSocketThread.sendMessage(MessageLibrary.getAuthRequestMessage(loginField.getText(), new String(passwordField.getPassword())));
    }

    @Override
    public void onSocketClosed(MessageSocketThread thread) {
        panelTop.setVisible(true);
        panelBottom.setVisible(false);
        setTitle(WINDOW_TITLE);
        listUsers.setListData(new String[0]);
    }

    @Override
    public void onMessageReceived(MessageSocketThread thread, String msg) {
        handleMessage(msg);
    }

    @Override
    public void onException(MessageSocketThread thread, Throwable throwable) {
        throwable.printStackTrace();
        showError(throwable.getMessage());
    }

    private void handleMessage(String msg) {
        String[] values = msg.split(MessageLibrary.DELIMITER);
        switch (MessageLibrary.getMessageType(msg)) {
            case AUTH_ACCEPT:
                this.nickname = values[2];
                setTitle(WINDOW_TITLE + " authorized with nickname: " + this.nickname);
                break;
            case AUTH_DENIED:
                putMessageInChat("server", msg);
                messageSocketThread.close();
                break;
            case TYPE_BROADCAST:
                putMessageInChat(values[2], values[3]);
                break;
            case MSG_FORMAT_ERROR:
                putMessageInChat("server", msg);
                break;
            case USER_LIST:
                // /user_list##user1##user2##user3
                String users = msg.substring(MessageLibrary.USER_LIST.length() +
                        MessageLibrary.DELIMITER.length());
                // user1##user2##user3
                String[] userArray = users.split(MessageLibrary.DELIMITER);
                Arrays.sort(userArray);
                listUsers.setListData(userArray);
                break;
            case TYPE_BROADCAST_CLIENT:
                String srcNickname = values[1];
                if (srcNickname.equals(nickname)) {
                    return;
                }
                putMessageInChat(srcNickname, values[2]);
                break;
            default:
                throw new RuntimeException("Unknown message: " + msg);

        }
    }
}
