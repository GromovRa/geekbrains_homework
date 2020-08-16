package ru.sgol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;

public class Race {
    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages() { return stages; }
    public CyclicBarrier preparationBarrier;
    public CyclicBarrier startBarrier;
    public CountDownLatch finishLatch;
    public Car winnerCar;
    public AtomicBoolean hasWinner = new AtomicBoolean();
    public Race(int carCount, Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
        this.preparationBarrier = new CyclicBarrier(carCount);
        this.startBarrier = new CyclicBarrier(carCount);
        this.finishLatch = new CountDownLatch(carCount-1);
    }
}
