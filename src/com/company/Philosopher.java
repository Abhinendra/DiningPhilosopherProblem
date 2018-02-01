package com.company;

import java.util.Random;

public class Philosopher implements Runnable {
    Chopsticks leftChopstick;
    Chopsticks rightChopstick;
    private int id;
    private boolean isFull;
    private Random random;
    private int counter=0;

    public Philosopher(int id, Chopsticks leftChopstick,Chopsticks rightChopstick) {
        this.id = id;
        random = new Random();
        this.leftChopstick=leftChopstick;
        this.rightChopstick=rightChopstick;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while(!isFull) {
            doThink();
            try {
                if (leftChopstick.lockAcquire(this, State.LEFT)) {
                    if (rightChopstick.lockAcquire(this, State.RIGHT)) {
                        doEat();
                        counter++;
                        rightChopstick.putDown(this, State.RIGHT);
                    }
                    leftChopstick.putDown(this, State.LEFT);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void doThink() {
        System.out.println("Philosopher " + id + " is thinking..");
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doEat() {
        System.out.println("Philosopher " + id + " is eating..");
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String toString(){
        return "Philosopher "+id;
    }
}
