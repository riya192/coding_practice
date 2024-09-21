package org.coding.practice.java.concurrency;

public class TestPriorityQueue {

    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Integer> pq = new PriorityBlockingQueue<>(5);
        PriorityBlockingQueueWithMutex<Integer> pqm = new PriorityBlockingQueueWithMutex<>(5);
        PriorityBlockingQueueWithSemaphore<Integer> pqs = new PriorityBlockingQueueWithSemaphore<>(5);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++)
                {
                    try {
//                        pq.enqueue(i);
//                        pqm.enqueue(i);
                        pqs.enqueue(i);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++)
                {
                    try {
//                        int j = pq.dequeue();
//                        System.out.println("Dequeued the element - " + j);
//                        int k = pqm.dequeue();
                        int k = pqs.dequeue();
                        System.out.println("semaphore Dequeued the element - " + k);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }


}
