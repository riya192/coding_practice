package org.coding.practice.java.concurrency;

import java.util.function.IntConsumer;
import java.util.concurrent.Semaphore;

class ZeroEvenOdd {
    private final int n;
    Semaphore zeroSem;
    Semaphore evenSem;
    Semaphore oddSem;

    int curr;


    public ZeroEvenOdd(int n) {
        this.n = n;
        this.zeroSem = new Semaphore(1);
        this.evenSem = new Semaphore(0);
        this.oddSem = new Semaphore(0);
        this.curr = 1;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i= 0;i<n;i++)
        {
            zeroSem.acquire();
            printNumber.accept(0);
            if(i%2 == 0) oddSem.release();
            else evenSem.release();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while (curr <= this.n)
        {
            evenSem.acquire();
            printNumber.accept(curr);
            zeroSem.release();
            curr++;
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while (curr <= this.n)
        {
            oddSem.acquire();
            printNumber.accept(curr);
            zeroSem.release();
            curr++;
        }
    }
}