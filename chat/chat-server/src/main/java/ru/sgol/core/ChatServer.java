package ru.sgol.core;

import ru.sgol.net.MessageSocketThread;
import ru.sgol.net.MessageSocketThreadListener;
import ru.sgol.net.ServerSocketThread;
import ru.sgol.net.ServerSocketThreadListener;

import java.net.Socket;

public class ChatServer implements ServerSocketThreadListener, MessageSocketThreadListener {

    private ServerSocketThread serverSocketThread;
    private MessageSocketThread messageSocketThread;

    public void start(int port) {
        if (serverSocketThread != null && serverSocketThread.isAlive()) {
            return;
        }
        serverSocketThread = new ServerSocketThread(this, "Chat-Server-Socket-Thread", port, 2000);
        serverSocketThread.start();
    }

    public void stop() {
        if (serverSocketThread == null || !serverSocketThread.isAlive()) {
            return;
        }
        serverSocketThread.interrupt();
    }

    @Override
    public void onClientConnected() {
        System.out.println("Client connected");
    }

    @Override
    public void onSocketAccepted(Socket socket) {
        this.messageSocketThread = new MessageSocketThread(this, "ServerSocket", socket);
    }

    @Override
    public void onException(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onClientTimeout(Throwable throwable) {

    }

    @Override
    public void onMessageReceived(String msg) {
        System.out.println(msg);
        messageSocketThread.sendMessage("echo: " + msg);
    }
}
