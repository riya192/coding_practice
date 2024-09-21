package org.coding.practice.java.concurrency;

import java.util.Comparator;
import java.util.concurrent.BrokenBarrierException;

public class TestUberRiderProblem {

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        UberRiderProblem uber = new UberRiderProblem();
        Thread t1 = new Thread(() -> {
            try{
                uber.seatDemocrat();
            }catch (InterruptedException | BrokenBarrierException e)
            {
                throw new RuntimeException();
            }
        });
        Thread t2 = new Thread(() -> {
            try{
                uber.seatDemocrat();
            }catch (InterruptedException | BrokenBarrierException e)
            {
                throw new RuntimeException();
            }
        });
        Thread t3 = new Thread(() -> {
            try{
                uber.seatDemocrat();
            }catch (InterruptedException | BrokenBarrierException e)
            {
                throw new RuntimeException();
            }
        });
        Thread t4 = new Thread(() -> {
            try{
                uber.seatRepublican();
            }catch (InterruptedException | BrokenBarrierException e)
            {
                throw new RuntimeException();
            }
        });
        Thread t5 = new Thread(() -> {
            try{
                uber.seatDemocrat();
            }catch (InterruptedException | BrokenBarrierException e)
            {
                throw new RuntimeException();
            }
        });
        Thread t6 = new Thread(() -> {
            try{
                Thread.sleep(1000);
                uber.seatRepublican();
            }catch (InterruptedException | BrokenBarrierException e)
            {
                throw new RuntimeException();
            }
        });
        Thread t7 = new Thread(() -> {
            try{
                uber.seatDemocrat();
            }catch (InterruptedException | BrokenBarrierException e)
            {
                throw new RuntimeException();
            }
        });
        Thread t8 = new Thread(() -> {
            try{
                uber.seatDemocrat();
            }catch (InterruptedException | BrokenBarrierException e)
            {
                throw new RuntimeException();
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
    }
}
