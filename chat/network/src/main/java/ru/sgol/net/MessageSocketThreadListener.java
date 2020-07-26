package ru.sgol.net;

public interface MessageSocketThreadListener {

    void onSocketReady();
    void onSocketClosed();
    void onMessageReceived(String msg);
    void onException(Throwable throwable);
}
