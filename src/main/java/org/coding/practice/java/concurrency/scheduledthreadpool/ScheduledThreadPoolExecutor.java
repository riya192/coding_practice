package org.coding.practice.java.concurrency.scheduledthreadpool;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class ScheduledThreadPoolExecutor {

    DelayedPriorityQueue queue;
    //TODO: doubt what if asked to implement a task timeout
    //TODO: did not understand so many stages and use of ctl variable - do we think the might deep dive more
    AtomicInteger threadCount;
    int core;
    Set<Thread> threadSet;
    public static final Logger LOGGER = Logger.getLogger(ScheduledThreadPoolExecutor.class.getName());
    ScheduledThreadPoolExecutor(int coreSize, int size) //TODO : not implementing corepool size and maxPoolSize, fixed thread pool executor
    {
        queue = new DelayedPriorityQueue(size);
        threadCount = new AtomicInteger(0);
        threadSet = new HashSet<>();
        core = coreSize;
    }

    public boolean submitDelayedTask(Runnable task, long delay, String name)  {
        try {
            queue.addToQueue(new DelayedTask(task, delay, name));
            synchronized (this)
            {
                if(threadCount.get() < core) {
                    threadCount.incrementAndGet();
                    addWorker();
                }

            }
            return true;
        } catch (InterruptedException e) {
            LOGGER.info("Exception submitting task - "+ name);
            return false;
        }
    }

    private void addWorker()
    {
        Worker worker = new Worker();
        Thread t = new Thread(worker);
        t.start();
    }

    private final class Worker implements Runnable
    {

        @Override
        public void run() {
            while (true)
            {
                DelayedTask task = null;
                try
                {
                    while((task = queue.getNextTask()) != null)
                    {
                        task.task.run();
                        LOGGER.info("Task completed - " + task.taskId);
                    }
                }
                catch (InterruptedException ex)
                {
                    LOGGER.info("Caught excpetion running task - " + (task == null ? "" : task.taskId));
                }
            }
        }
    }

}
