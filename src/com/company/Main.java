package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        Philosopher[] philosophers = new Philosopher[Constants.NUMBER_OF_PHILOSOPHERS];
        Chopsticks[] chopsticks = new Chopsticks[Constants.NUMBER_OF_CHOPSTICKS];
        for (int i = 0; i < Constants.NUMBER_OF_CHOPSTICKS; i++) {
            chopsticks[i] = new Chopsticks(i);
        }
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < Constants.NUMBER_OF_PHILOSOPHERS; ++i) {
            philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % Constants.NUMBER_OF_PHILOSOPHERS]);
            executorService.execute(philosophers[i]);
        }

        try {
            Thread.sleep(Constants.SIMUKTAION_TIME_INMILISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Philosopher philosopher : philosophers) {
            philosopher.setFull(true);
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(5000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Philosopher philosopher : philosophers) {
               System.out.println(philosopher + " has eaten " + philosopher.getCounter() + " times.");
           }
       
    }
}
