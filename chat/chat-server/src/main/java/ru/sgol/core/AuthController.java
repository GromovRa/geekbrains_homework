package ru.sgol.core;

import ru.sgol.data.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class AuthController extends Thread{

    HashMap<String, User> users = new HashMap<>();

    private Vector<ClientSessionThread> clients;

    public AuthController(String name, Vector<ClientSessionThread> clients ) {
        super(name);
        this.clients = clients;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            for (ClientSessionThread clientSession : clients){
                if(System.currentTimeMillis() - clientSession.getStartTimeMillis() > 120_000
                && !clientSession.isAuthorized()){
                    clientSession.authDeny();
                    clientSession.close();
                }
            }
        }
    }

    public void init() {
        for (User user : receiveUsers()) {
            users.put(user.getLogin(), user);
        }
    }

    public String getNickname(String login, String password) {
        User user = users.get(login);
        if (user != null && user.isPasswordCorrect(password)) {
            return user.getNickname();
        }
        return null;
    }

    private ArrayList<User> receiveUsers() {
        ArrayList<User> usersArr = new ArrayList<>();
        usersArr.add(new User("admin", "admin", "sysroot"));
        usersArr.add(new User("alex", "123", "alex-st"));
        return usersArr;
    }

}
