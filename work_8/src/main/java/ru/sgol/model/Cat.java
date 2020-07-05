package ru.sgol.model;

public class Cat implements CanJump, CanRun {
    private double metersCanRun;
    private double metersCanJump;

    public Cat(double metersCanRun, double metersCanJump) {
        this.metersCanRun = metersCanRun;
        this.metersCanJump = metersCanJump;
    }

    public Boolean jump(double high) {
        if (high > metersCanJump) {
            System.out.println("Кот не смог прыгнуть");
            return false;
        } else {
            metersCanJump -= high;
            System.out.println("Кот прыгнул");
            return true;
        }
    }

    public Boolean run(double distance) {
        if (distance > metersCanRun) {
            System.out.println("Кот не смог пробежать");
            return false;
        } else {
            System.out.println("Кот пробежал");
            metersCanRun -= distance;
            return true;
        }

    }
}
