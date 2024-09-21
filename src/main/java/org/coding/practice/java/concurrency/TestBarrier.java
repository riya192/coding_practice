package org.coding.practice.java.concurrency;

public class TestBarrier {

    public static void main(String[] args) throws InterruptedException {
        Barrier barrier = new Barrier(3);

        Thread t1 = new Thread(() -> {
            try {
                barrier.await();
                System.out.println("Thread 1 allowed");
                barrier.await();
                System.out.println("Thread 1 allowed");
                barrier.await();
                System.out.println("Thread 1 allowed");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep( 500);
                barrier.await();
                System.out.println("Thread 2 allowed");
                Thread.sleep( 500);
                barrier.await();
                System.out.println("Thread 2 allowed");
                Thread.sleep( 500);
                barrier.await();
                System.out.println("Thread 2 allowed");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                Thread.sleep( 1000);
                barrier.await();
                System.out.println("Thread 3 allowed");
                Thread.sleep( 1000);
                barrier.await();
                System.out.println("Thread 3 allowed");
                Thread.sleep( 1000);
                barrier.await();
                System.out.println("Thread 3 allowed");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }
}
