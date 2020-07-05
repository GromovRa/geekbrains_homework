package ru.sgol.model;

public class Wall implements Barrier{

    private double high;

    public Wall(double high) {
        this.high = high;
    }

    public boolean passBarrier(Object participant) {
        if (participant instanceof CanJump) {
            return ((CanJump) participant).jump(high);
        } else {
            return false;
        }
    }
}
