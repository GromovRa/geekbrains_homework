package ru.sgol.model;

public class Cat extends Animal{
    static private int numbersOfCats = 0;

    {
        this.runCap = 200;
        this.jumpCap = 2;
        numbersOfCats++;
        typeName = "кот";
    }

    @Override
    public String canSwim(double distance) {
        return typeName + " не может плавать";
    }

    public static int getNumbersOfCats() {
        return numbersOfCats;
    }
}
