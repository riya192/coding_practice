package org.coding.practice.java.concurrency;

public class TokenBucketAldoWithDiffThread {

    int MAX_TOKEN;
    int possibleToken;

    TokenBucketAldoWithDiffThread(int m) throws InterruptedException {
        this.MAX_TOKEN = m;

        Thread dt = new Thread(() -> {
            try {
                deamonThread();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        dt.setDaemon(true);
        dt.start();
    }

    private void deamonThread() throws InterruptedException {
        while (true)
        {
            synchronized (this)
            {
                if(possibleToken < MAX_TOKEN)
                {
                    possibleToken++;
                }
                this.notify();
            }
            Thread.sleep(1000);
        }
    }

    private void getToken() throws InterruptedException {

        // implement this using a semaphore of 1 size and fair value true to maintain FIFO
        //
        synchronized (this)
        {
            while (possibleToken == 0)
            {
                this.wait();
            }
            possibleToken --;
        }
        System.out.println("Granted token");
    }

}
