package com.telran.org.homework_28;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HWExchangeApp {
    public static void main(String[] args) {

        Exchanger<String> objectExchanger = new Exchanger<>();

        int arraysCont = 10;
        int arraysLength = 3;

        String[][] trakes = new String[arraysCont][arraysLength];
        for (int i = 0; i < arraysCont; i++) {
            for (int j = 0; j < arraysLength; j++) {
                trakes[i][j] = "A" + (i * arraysLength + j + 1);
            }
        }

        for (int i = 0; i < arraysCont; i++) {
            for (int j = 0; j < arraysLength; j++) {
                System.out.print(trakes[i][j] + " ");
            }
            System.out.println();
        }

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < arraysCont; i++) {
            executorService.execute(new TruckEx(trakes[i], objectExchanger));
        }
        executorService.shutdown();
    }
}