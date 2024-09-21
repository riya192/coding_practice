package org.coding.practice.java.concurrency.threadpool;

import static org.coding.practice.java.concurrency.threadpool.ThreadPoolExecutor.LOGGER;

public class ThreadPoolExecutorTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 3, 3);

        for(int i = 0; i<10 ;i ++)
        {
//            if(i==4) {
//                Thread.sleep(3000);
//                LOGGER.info("submitting the 5th task");
//            }
            Runnable task = () -> {
                try {
                    Thread.sleep(1000);
                    LOGGER.info("Task completed for thread" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            };
            threadPoolExecutor.execute(new RunnableTask(task, "Thread-" + i));
        }
        LOGGER.info("All task submitted");
    }
}

/**
 *
 * t1 - 0
 * t2 - 1
 * queue - 2, 3, 4
 * t3 - 2 queue - 3,4, 5
 *
 */
