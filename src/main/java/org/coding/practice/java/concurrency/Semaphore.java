package org.coding.practice.java.concurrency;

public class Semaphore {

    int usedPermit;
    int MAX_PERMIT;

    //TODO: doubt do we even have to create semaphore of your own
    Semaphore(int size)
    {
        this.usedPermit = 0;
        this.MAX_PERMIT = size;
    }

    public synchronized void acquire() throws InterruptedException {
        while(usedPermit == MAX_PERMIT)
            this.wait();
        usedPermit ++;
        this.notifyAll();
    }

    public synchronized void release() throws InterruptedException {
        while (usedPermit == 0)
            this.wait();
        usedPermit --;
        this.notifyAll();
        this.wait();
    }
}
