package org.coding.practice.java.concurrency.webcrawler;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueueWithTimeout<T> {

    Queue<T> queue;
    int size;
    int currEls;
    long timeout;

    BlockingQueueWithTimeout(long timeout)
    {
        this.queue = new LinkedList<>();
        this.currEls = 0;
        this.timeout = timeout;
    }

    public synchronized void add(T el) throws InterruptedException {
        queue.add(el);
        currEls++;
        this.notifyAll();
    }

    public synchronized T get() throws InterruptedException{
        if(currEls == 0)
            this.wait(timeout);
        if(queue.isEmpty()) return null;
        currEls--;
        return queue.poll();
    }

    public synchronized boolean isEmpty() throws InterruptedException
    {
        return currEls == 0;
    }
}
