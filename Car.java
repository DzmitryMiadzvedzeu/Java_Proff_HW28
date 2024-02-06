package com.telran.org.homework_28.GasStation;

import java.util.concurrent.Semaphore;

public class Car implements Runnable {

    private String name;
    public Semaphore gusStation;
    private boolean[] pumps;

    public Car(String name, Semaphore gusStation, boolean[] pumps) {
        this.name = name;
        this.gusStation = gusStation;
        this.pumps = pumps;
    }

    @Override
    public void run() {
        System.out.println("{!} " + name + " подъехал к АЗС");
        try {
            gusStation.acquire();
            int occupiedPump = -1;
            synchronized (pumps) {
                for (int i = 0; i < pumps.length; i++) {
                    if (pumps[i] == false) {
                        pumps[i] = true;
                        occupiedPump = i;
                        System.out.println("{--} " + name + " остановился у колонки " + (i + 1));
                        break;
                    }
                }
            }
            Thread.sleep(15000); // время заправки 15 сек
            synchronized (pumps){
                pumps[occupiedPump] = false;
            }
            gusStation.release();

            synchronized (pumps){
                System.out.println("-> " + name + " закончил заправку и покинул АЗС");
                System.out.println("[!!] колонка под номером " + (occupiedPump + 1) + " освободилась!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
