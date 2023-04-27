package aso_lab3;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {

        Store store = new Store();

        CyclicBarrier barrier = new CyclicBarrier(6);
        Semaphore semaphore = new Semaphore(1, true);

        Producer p1 = new Producer(store, semaphore, barrier, "Producer 1");
        Producer p2 = new Producer(store, semaphore, barrier, "Producer 2");

        Consumer c1 = new Consumer(store, semaphore, barrier, "Consumer 1");
        Consumer c2 = new Consumer(store, semaphore, barrier, "Consumer 2");
        Consumer c3 = new Consumer(store, semaphore, barrier, "Consumer 3");
        Consumer c4 = new Consumer(store, semaphore, barrier, "Consumer 4");

        p1.start();
        p2.start();

        c1.start();
        c2.start();
        c3.start();
        c4.start();

    }
    
}
