package org.coding.practice.java.concurrency;

public class TestDinerPhilosopherProblem {

    public static void main(String[] args) throws InterruptedException {
        DinersPhilosopherProblem diner = new DinersPhilosopherProblem(5);
        Thread t1 = new Thread(() -> {
            try {
                diner.philospherLifeCycle(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                diner.philospherLifeCycle(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                diner.philospherLifeCycle(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t4 = new Thread(() -> {
            try {
                diner.philospherLifeCycle(4);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t5 = new Thread(() -> {
            try {
                diner.philospherLifeCycle(0);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
    }
}
