package com.company;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopsticks {
    private int id;
    private Lock lock;
    public Chopsticks(int id){
        this.setId(id);
        lock=new ReentrantLock();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean lockAcquire(Philosopher philosopher,State state) throws              InterruptedException {
        if(lock.tryLock(10, TimeUnit.MILLISECONDS)){
            System.out.println(philosopher+" has acquired "+state+" lock");
            return true;
        }
        return false;
    }

    public void putDown(Philosopher philosopher,State state){
        System.out.println(philosopher+" has put down "+state+" lock");
        lock.unlock();
    }
}
