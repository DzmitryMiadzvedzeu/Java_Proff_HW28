package com.telran.org.homework_28;

import java.util.concurrent.Exchanger;

public class TruckEx implements Runnable{

    private String[] letterWithNum;
    private Exchanger<String> exchanger;

    public TruckEx(String[] letterWithNum, Exchanger<String> exchanger) {
        this.letterWithNum = letterWithNum;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            String changeThis = exchanger.exchange(letterWithNum[1]);
            letterWithNum[1] = changeThis;

            System.out.println("success " + letterWithNum[0] + " " + letterWithNum[1] + " " + letterWithNum[2]);

            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
