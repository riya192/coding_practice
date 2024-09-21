package org.coding.practice.java.concurrency;

public class TestBarberShop {

    public static void main(String[] args) throws InterruptedException {
        BarberShop barberShop = new BarberShop(5);
        barberShop.startShop();
        Thread t1 = new Thread(() -> {
            try {
                barberShop.enterCustomer("riya 1");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                barberShop.enterCustomer("riya 2");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t5 = new Thread(() -> {
            try {
                barberShop.enterCustomer("riya 5");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                barberShop.enterCustomer("riya 3");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t4 = new Thread(() -> {
            try {
                barberShop.enterCustomer("riya 4");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t6 = new Thread(() -> {
            try {
                barberShop.enterCustomer("riya 6");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();
    }
}
