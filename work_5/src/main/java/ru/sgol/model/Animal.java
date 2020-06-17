package ru.sgol.model;

public abstract class Animal {
    protected double runCap;
    protected double swimCap;
    protected double jumpCap;
    protected String typeName;
    private static int numberOfAnimals=0;

    public String canRun(double distance) {
        return distance <= this.runCap ? typeName + " может пробежать" : typeName + " не может пробежать";
    }

    public String canSwim(double distance) {
        return distance <= this.swimCap ? typeName + " может проплыть" : typeName + " не может проплыть";
    }

    public String canJump(double distance) {
        return distance <= this.jumpCap ? typeName + " может прыгнуть" : typeName + " не может прыгнуть";
    }

    public static int getNumberOfAnimals() {
        return numberOfAnimals;
    }

    public Animal() {
        numberOfAnimals++;
    }
}
