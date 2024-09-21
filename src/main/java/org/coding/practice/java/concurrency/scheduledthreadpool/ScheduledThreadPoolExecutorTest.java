package org.coding.practice.java.concurrency.scheduledthreadpool;

import org.coding.practice.java.concurrency.scheduledthreadpool.ScheduledThreadPoolExecutor;

public class ScheduledThreadPoolExecutorTest {

    public static void main(String[] args)
    {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(3, 5);
        for (int i =1;i<4;i++)
        {
            scheduledThreadPoolExecutor.submitDelayedTask(() -> {
                ScheduledThreadPoolExecutor.LOGGER.info("Executing the thread");
            }, 1000*(4-i), "Thread - "+ i);
        }
        ScheduledThreadPoolExecutor.LOGGER.info("Submitted All tasks");
    }
}
