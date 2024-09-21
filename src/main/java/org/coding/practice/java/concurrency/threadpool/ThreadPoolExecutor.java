package org.coding.practice.java.concurrency.threadpool;

import org.coding.practice.java.concurrency.PriorityBlockingQueue;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class ThreadPoolExecutor {

    private final int corePoolSize;
    private final int maxPoolSize;
    private final int queueSize;
    private PriorityBlockingQueue<RunnableTask> queue;
    Set<Thread> threadSet;
    AtomicInteger threadCounter;
    AtomicInteger queueSizeCounter;

    static final Logger LOGGER = Logger.getLogger(ThreadPoolExecutor.class.getName());
    public ThreadPoolExecutor(int corePoolSize, int maxPoolSize, int queueSize)
    {
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.queueSize = queueSize;
        this.queue = new PriorityBlockingQueue<>(queueSize);
        this.threadCounter=new AtomicInteger(0);
        this.queueSizeCounter=new AtomicInteger(0);
        this.threadSet = new HashSet<>();
    }

    public void execute(RunnableTask task) throws InterruptedException {
        boolean executed = true;
        if(this.threadCounter.get() < this.corePoolSize)
        {
            this.threadCounter.incrementAndGet();
            executed = addWorker(task, true); //starting workers
        }
        else if(this.queueSize - getQueueCount() > 0)
        {
            LOGGER.info("queueing the task - " + task.threadName);
            this.queueSizeCounter.incrementAndGet();
            this.queue.enqueue(task);
        }
        else if(this.threadCounter.get() < maxPoolSize)
        {
            this.queue.enqueue(task);
            this.threadCounter.incrementAndGet();
            this.queueSizeCounter.incrementAndGet();
            executed = addWorker(task, false); //last workers
        }
        else
        {
            executed = false;
            LOGGER.info("rejecting the task");
            //maybe just log for now?
            //add a Rejection handler
        }
        LOGGER.info("submission of the task successful for thread - " + task.threadName + executed);
    }

    private synchronized int getQueueCount()
    {
        //TODO :doubt - if i didn't make this synch, 2 threads were getting the same count 0
        // and the task tried to queue changing the expected behaviour? any better way?
        // aware that we should lock all access of a var if need be sync by same lock ,
        // maybe should take normal int instead of atomic?
        return queueSizeCounter.get();
    }

    private boolean addWorker(RunnableTask task, Boolean flag)
    {
        try {
            Worker worker = new Worker(task, flag);
            Thread t = new Thread(worker);
            threadSet.add(t);
            //TODO: doubt : actual executor runs the thread in a container, should we also
            t.start();
            return true;
        }catch (Exception e)
        {
            LOGGER.info("Caught exception while creating the worker");
            return false;
        }
    }

    private final class Worker implements Runnable{ // TODO : understand if we need this - AbstractQueuedSynchronizer

        RunnableTask firstTask;
        boolean doStartFirstTask;
        int taskCompleted;
        Worker(RunnableTask r, Boolean flag)
        {
            this.firstTask = r;
            this.taskCompleted =0;
            this.doStartFirstTask = flag;
        }

        @Override
        public void run() {

            try{
                RunnableTask task = firstTask;
                this.firstTask = null;
                while ((task != null && doStartFirstTask) || (task = getTask()) !=null)// todo : think how worker will query from the queue
                {
                    try {
                        Thread.currentThread().setName(task.threadName);
                        task.task.run();
                    }catch (Exception e)
                    {
                        throw new RuntimeException("Thread pool executor failed");
                    }finally {
                        task = null;
                        this.taskCompleted++;
                    }
                }
            }
            catch(Exception e)
            {
                throw new RuntimeException("Thread pool executor failed");
            }

        }
    }

    public RunnableTask getTask() throws InterruptedException {
        this.queueSizeCounter.decrementAndGet();
        return this.queue.dequeue();
    }

}



