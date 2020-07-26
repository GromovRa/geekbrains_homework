package ru.sgol.core;

import ru.sgol.chat.common.MessageLibrary;
import ru.sgol.net.MessageSocketThread;
import ru.sgol.net.MessageSocketThreadListener;

import java.net.Socket;

public class ClientSessionThread extends MessageSocketThread {

    private boolean isAuthorized = false;
    private String nickname;
    private boolean reconnected = false;

    public long getStartTimeMillis() {
        return startTimeMillis;
    }

    private long startTimeMillis;

    public ClientSessionThread(MessageSocketThreadListener listener, String name, Socket socket) {
        super(listener, name, socket);
        startTimeMillis = System.currentTimeMillis();
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public String getNickname() {
        return nickname;
    }

    public void authAccept(String nickname) {
        this.nickname = nickname;
        this.isAuthorized = true;
        sendMessage(MessageLibrary.getAuthAcceptMessage(nickname));
    }

    public void authDeny() {
        sendMessage(MessageLibrary.getAuthDeniedMessage());
    }

    public void authError(String msg) {
        sendMessage(MessageLibrary.getMsgFormatErrorMessage(msg));
        close();
    }

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isReconnected() {
        return reconnected;
    }

    public void setReconnected(boolean reconnected) {
        this.reconnected = reconnected;
    }
}
