package aso_lab3;

import java.util.concurrent.CyclicBarrier;

public class Main {

    public static void main(String[] args) {
        final Store store = new Store();
        final CyclicBarrier barrier = new CyclicBarrier(6);
        final Producer p1 = new Producer(store, barrier, "Producer 1");
        final Producer p2 = new Producer(store, barrier, "Producer 2");
        final Producer p3 = new Producer(store, barrier, "Producer 3");
        final Consumer c1 = new Consumer(store, barrier, "Consumer 1");
        final Consumer c2 = new Consumer(store, barrier, "Consumer 2");
        final Consumer c3 = new Consumer(store, barrier, "Consumer 3");
        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
        c3.start();

    }

}
