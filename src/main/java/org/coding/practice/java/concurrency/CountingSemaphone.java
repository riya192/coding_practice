package org.coding.practice.java.concurrency;

import java.util.concurrent.Semaphore;

public class CountingSemaphone extends Semaphore {

    private int toAcquire;
    public CountingSemaphone(int permits, int availablePermit)  {
        super(permits, true);
        this.toAcquire = permits - availablePermit;
        try {
            acquirePermits();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private void acquirePermits() throws InterruptedException {
        System.out.println("aquiring permits" + this.toAcquire);
        for (int i = 0; i <this.toAcquire; i++) {
            super.acquire();
        }
        System.out.println("aquired");
    }


}
