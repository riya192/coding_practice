package org.coding.practice.java.concurrency;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class UberRiderProblem {

    int democrat;
    int republican;
    private static final String DEMOCRAT_ID = "D";
    private static final String REPUBLICAN_ID = "R";

    final ReentrantLock lock;
    Semaphore demsWaiting;
    Semaphore repubWaiting;
    Barrier barrier;

    UberRiderProblem()
    {
        this.democrat = 0;
        this.republican = 0;
        this.lock = new ReentrantLock();
        this.demsWaiting = new Semaphore(0);
        this.repubWaiting = new Semaphore(0);
        this.barrier = new Barrier(4);
    }

    public void seatDemocrat() throws InterruptedException, BrokenBarrierException {

        lock.lock();
        democrat++;
        boolean rideLeader = false;
        String comb=DEMOCRAT_ID;

        if(democrat ==2 && republican>=2)
        {
            demsWaiting.release(1);
            democrat-=1;
            republican-=2;
            repubWaiting.release(2);
            comb = comb.concat(REPUBLICAN_ID);
            rideLeader = true;
        }
        else if(democrat ==4)
        {
            demsWaiting.release(3);
            democrat-=4;
            rideLeader = true;
        }else {
            lock.unlock();
            demsWaiting.acquire();
        }

        seated(DEMOCRAT_ID);
        barrier.await();

        if(rideLeader)
        {
            drive(comb);
            lock.unlock();
        }
    }

    public void seatRepublican() throws InterruptedException, BrokenBarrierException {
        lock.lock();
        republican++;
        boolean rideLeader = false;
        String comb=REPUBLICAN_ID;

        if(democrat >=2 && republican>=2)
        {
            repubWaiting.release(1);
            republican-=1;
            democrat-=2;
            demsWaiting.release(2);
            comb = comb.concat(DEMOCRAT_ID);
            rideLeader = true;
        }
        else if(republican >=4)
        {
            repubWaiting.release(3);
            republican-=4;
            rideLeader = true;
        }else {
            lock.unlock();
            repubWaiting.acquire();
        }

        seated(REPUBLICAN_ID);
        barrier.await();

        if(rideLeader)
        {
            drive(comb);
            lock.unlock();
        }
    }

    private void seated(String id){
        System.out.println("Seated - "+ id);
        System.out.flush();
    }

    private void drive(String comb)
    {
        System.out.println("Car left - " + comb);
        System.out.flush();
    }

}
