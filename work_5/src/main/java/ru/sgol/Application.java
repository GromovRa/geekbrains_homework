package ru.sgol;

import ru.sgol.model.*;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Bird());
        animals.add(new Dog());
        animals.add(new Dog());
        animals.add(new Dog());
        animals.add(new Horse());
        animals.add(new Cat());
        animals.add(new Cat());

        animals.forEach(animal -> {
            System.out.println(animal.canJump(1));
            System.out.println(animal.canRun(20));
            System.out.println(animal.canSwim(2));
        });

        System.out.println(Dog.getNumbersOfDogs());
        System.out.println(Animal.getNumberOfAnimals());
        System.out.println(Cat.getNumbersOfCats());
    }

}
