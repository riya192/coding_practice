package org.coding.practice.java.concurrency;

import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

public class GenderBathroom {

    //TODO : make this
    String inUseBy;
    Semaphore maxEmp;
    int currPeopleInBathRoom;

    Semaphore bathroomSem;

    private static final Logger LOGGER = Logger.getLogger(GenderBathroom.class.getName());

    GenderBathroom(int size)
    {
        inUseBy = "NONE";
        maxEmp = new Semaphore(size, true);
        this.currPeopleInBathRoom = 0;
        this.bathroomSem = new Semaphore(1, true);
    }

    private void useBathRoom(String name) throws InterruptedException {
        LOGGER.info("current poeple in bathroom - " + this.currPeopleInBathRoom);
        Thread.sleep(100);
//        System.out.println("Leaving the bathroom - " + name);
    }

    public void maleAquireBathroom(String name) throws InterruptedException {
        //TODO: Doubt - how to make this non starving for one gender without having to maintain a queue
        synchronized (this)
        {
            while(maxEmp.availablePermits() == 0 || inUseBy.equals("FEMALE"))
                this.wait();
            maxEmp.acquire();
            inUseBy = "MALE";
            currPeopleInBathRoom++;
        }
        useBathRoom(name);
        synchronized (this)
        {
            currPeopleInBathRoom --;
            if(currPeopleInBathRoom == 0)
            {
                inUseBy = "NONE";
                LOGGER.info("changing the gender");
            }
            maxEmp.release();
            this.notifyAll();
            LOGGER.info("leaving the bathroom - " + name);
        }
    }

    public void femaleAquireBathroom(String name) throws InterruptedException {
        synchronized (this)
        {
            while(maxEmp.availablePermits() == 0 || inUseBy.equals("MALE"))
                this.wait();
            maxEmp.acquire();
            inUseBy = "FEMALE";
            currPeopleInBathRoom++;
        }
        useBathRoom(name);
        synchronized (this)
        {
            currPeopleInBathRoom --;
            if(currPeopleInBathRoom == 0)
            {
                inUseBy = "NONE";
                LOGGER.info("changing the gender");
            }
            maxEmp.release();
            this.notifyAll();
            LOGGER.info("leaving the bathroom - " + name);
        }
    }

}
