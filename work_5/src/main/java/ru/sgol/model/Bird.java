package ru.sgol.model;

public class Bird extends Animal {
    {
        this.runCap = 5;
        this.jumpCap = 0.1;
        typeName = "птица";
    }

    @Override
    public String canSwim(double distance) {
        return typeName + " не может плавать";
    }
}
