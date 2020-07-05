package ru.sgol;

import ru.sgol.model.*;

public class Application {
    public static void main(String[] args) {
        Object[] participants = new Object[6];
        Barrier[] barriers = new Barrier[4];

        participants[0] = new Cat(50, 100);
        participants[1] = new Cat(50, 100);
        participants[2] = new Robot(1000, 110000);
        participants[3] = new Robot(1000, 100000);
        participants[4] = new Human(500, 20);
        participants[5] = new Human(10000, 10000);

        barriers[0] = new Wall(100);
        barriers[1] = new RunningTrack(5);
        barriers[2] = new Wall(5);
        barriers[3] = new RunningTrack(100);

        PAR:
        for (Object participant : participants) {
            for (Barrier barrier : barriers) {
                if (!barrier.passBarrier(participant)) {
                    continue PAR;
                }
             }
        }
    }
}
