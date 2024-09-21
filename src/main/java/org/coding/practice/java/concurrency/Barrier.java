package org.coding.practice.java.concurrency;

import java.util.concurrent.Semaphore;
public class Barrier {

    int count;
    int total_thread;
    Barrier(int total)
    {
        this.count = 0;
        this.total_thread = total;
    }
    public synchronized void await() throws InterruptedException {
        count++;
        if(count == this.total_thread)
        {
            notifyAll();
            count = 0;
        }
        else
        {
            this.wait();
            //TODO: doubt - here there is not a chance of spurious wakeups?
            // this.wait can be outside a while?
            //Is it not similar to async to sync implementation in educative chapter
        }
    }
}
