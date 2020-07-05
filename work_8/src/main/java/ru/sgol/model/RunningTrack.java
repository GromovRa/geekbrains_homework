package ru.sgol.model;

public class RunningTrack implements Barrier{

    private double distance;

    public RunningTrack(double distance) {
        this.distance = distance;
    }

    public boolean passBarrier(Object participant) {
        if (participant instanceof CanRun) {
            return ((CanRun) participant).run(distance);
        } else {
            return false;
        }
    }
}
