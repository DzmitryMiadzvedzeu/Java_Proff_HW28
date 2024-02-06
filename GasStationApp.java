package com.telran.org.homework_28.GasStation;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class GasStationApp {
    public static void main(String[] args) {

        Semaphore gusStation = new Semaphore(4, true); // true is occupied
        boolean[] freePumps = new boolean[4];
        List<Thread> carThreads = new ArrayList<>();

        LocalDateTime startWorking = LocalDateTime.now(); // стартовое время работы колонки = время запуска работы приложения
        Duration workTime = Duration.ofMinutes(1); // минута работы
        LocalDateTime endWorking = startWorking.plus(workTime);

        while (LocalDateTime.now().isBefore(endWorking)){
            Car car = new Car("Авто " + carThreads.size(),gusStation, freePumps );
            Thread carThread = new Thread(car);
            carThreads.add(carThread);
            carThread.start();
            try {
                Thread.sleep(3000); // каждые 3 сек спавнится новая бричка
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("АЗС закончила свою работу");
    }
}
// Результат:
// 1 - программа работает согласно ТЗ
// 2 - Было обнаруженно, что программа не корректно завершает работу при маленком цикле работы
// -- при окончании работы АЗС автомобили, которые приехали, заправляются
// и уезжают, но новые не приезжают. Это связанно с тем, что я завязал именно
// создание новых потоков и их запуском, с временем работы АЗС.
// Первый вариант: нужно как -то завершить одновременно все потоки, вместе с завершением работы АЗС
// Воторой вариант: из расчёта времени на заправку добавить условие которое перестанет создавать
// потоки за определённое время до окончание работы АЗС.

// решил ничего не предпринимать, потому как вспомнил, что если у нас закрывается магазин, конец рабочего дня,
// то это означает, что новых покупателей не впускают, а тех кто внутри, обслуживают и выпускают.



