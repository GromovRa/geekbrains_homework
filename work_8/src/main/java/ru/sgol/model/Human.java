package ru.sgol.model;

public class Human implements CanJump, CanRun {
    public Human(double metersCanRun, double metersCanJump) {
        this.metersCanRun = metersCanRun;
        this.metersCanJump = metersCanJump;
    }

    private double metersCanRun;
    private double metersCanJump;

    public Boolean jump(double high) {
        if (high > metersCanJump) {
            System.out.println("Человек не смог прыгнуть");
            return false;
        } else {
            metersCanJump -= high;
            System.out.println("Человек прыгнул");
            return true;
        }
    }

    public Boolean run(double distance) {
        if (distance > metersCanRun) {
            System.out.println("Человек не смог пробежать");
            return false;
        } else {
            System.out.println("Человек пробежал");
            metersCanRun -= distance;
            return true;
        }

    }

}
