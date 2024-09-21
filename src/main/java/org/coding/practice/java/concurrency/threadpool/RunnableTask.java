package org.coding.practice.java.concurrency.threadpool;

public class RunnableTask {

    Runnable task;
    String threadName;

    RunnableTask(Runnable r, String threadName)
    {
        this.task = r;
        this.threadName = threadName;
    }
}
