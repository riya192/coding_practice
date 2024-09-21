package org.coding.practice.java.concurrency.scheduledthreadpool;

public class DelayedTask implements Comparable{

    Runnable task;
    long startTime;
    String taskId;

    DelayedTask(Runnable task, long delay, String id)
    {
        this.task = task;
        this.startTime = System.currentTimeMillis() + delay;
        this.taskId = id;
    }

    @Override
    public int compareTo(Object o) {
        DelayedTask task = (DelayedTask) o;
        return Long.compare(this.startTime, task.startTime);
    }
}
