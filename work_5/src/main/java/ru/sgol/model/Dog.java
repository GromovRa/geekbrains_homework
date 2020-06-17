package ru.sgol.model;

import java.util.Random;

public class Dog extends Animal{
    static private Random random = new Random();
    static private int numbersOfDogs = 0;


    {
        this.runCap = 400  + random.nextInt(200);
        this.jumpCap = 0.4;
        this.swimCap = 10;
        typeName = "Собака";
        numbersOfDogs++;
    }

    public static int getNumbersOfDogs() {
        return numbersOfDogs;
    }
}
