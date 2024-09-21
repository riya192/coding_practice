package org.coding.practice.java.concurrency;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.logging.Logger;

public class TestGenderBathroom {
    private static final Logger LOGGER = Logger.getLogger(TestGenderBathroom.class.getName());

    public static void main(String[] args) throws InterruptedException {
        GenderBathroom bathroom = new GenderBathroom(3);
        Thread tf = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bathroom.femaleAquireBathroom("Riya - 1");
//                    LOGGER.info("leaving the bathroom - " + "Riya -1");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread tf1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bathroom.femaleAquireBathroom("Riya - 2");
//                    LOGGER.info("leaving the bathroom - " + "Riya -2");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread tf2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bathroom.femaleAquireBathroom("Riya - 3");
//                    LOGGER.info("leaving the bathroom - " + "Riya -3");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread tf3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(120);
                    bathroom.femaleAquireBathroom("Riya - 4");
//                    LOGGER.info("leaving the bathroom - " + "Riya -4");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread tm = new Thread(() -> {
            try {
                bathroom.maleAquireBathroom("Awinash - 1");
//                LOGGER.info("leaving the bathroom - " + "Awinash -1");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread tm1 = new Thread(() -> {
            try {
                bathroom.maleAquireBathroom("Awinash - 2" );
//                LOGGER.info("leaving the bathroom - " + "Awinash -2");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread tm2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bathroom.maleAquireBathroom("Awinash - 3" );
//                    LOGGER.info("leaving the bathroom - " + "Awinash -3");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        tf.start();
        tf1.start();
        tf2.start();
        tf3.start();
        tm.start();
        tm1.start();
        tm2.start();
//        tf.join();
//        tf1.join();
//        tf2.join();
//        tm.join();
//        tf3.join();
//        tm1.join();
//        tm2.join();


    }
}
