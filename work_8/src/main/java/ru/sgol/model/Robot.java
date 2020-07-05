package ru.sgol.model;

public class Robot implements CanJump, CanRun {
    private double metersCanRun;
    private double metersCanJump;

    public Robot(double metersCanRun, double metersCanJump) {
        this.metersCanRun = metersCanRun;
        this.metersCanJump = metersCanJump;
    }


    public Boolean jump(double high) {
        if (high > metersCanJump) {
            System.out.println("Робот не смог прыгнуть");
            return false;
        } else {
            metersCanJump -= high;
            System.out.println("Робот прыгнул");
            return true;
        }
    }

    public Boolean run(double distance) {
        if (distance > metersCanRun) {
            System.out.println("Робот не смог пробежать");
            return false;
        } else {
            System.out.println("Робот пробежал");
            metersCanRun -= distance;
            return true;
        }

    }

}
