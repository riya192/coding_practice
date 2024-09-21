package org.coding.practice.java.concurrency.scheduledthreadpool;

import java.util.PriorityQueue;
import java.util.concurrent.Delayed;

public class DelayedPriorityQueue {

    PriorityQueue<DelayedTask> taskQueue;

    int size;

    DelayedPriorityQueue(int size)
    {
        taskQueue = new PriorityQueue<>(size);
        this.size = size;
    }

    public synchronized void addToQueue(DelayedTask task) throws InterruptedException {
        while (taskQueue.size() == size)
            this.wait();
        taskQueue.add(task);
        this.notifyAll();
    }

    public synchronized DelayedTask getNextTask() throws InterruptedException
    {
        long currentTime = System.currentTimeMillis();
        DelayedTask task;
        while ((task = taskQueue.peek()) == null) //TODO: doubt if i need to add timeout, should i not have a while loop
            this.wait();
        if(task.startTime <= currentTime) return taskQueue.poll();
        return null;
    }
}
