package aso_lab3;

import java.util.concurrent.CyclicBarrier;

public class Main {

    public static void main(String[] args) {
        Store store = new Store();
        CyclicBarrier barrier = new CyclicBarrier(6); // вместо нуля указываете общее кол-во производителей и потребителей

        Producer p1 = new Producer(store, barrier, "Producer 1");
        Producer p2 = new Producer(store, barrier, "Producer 2");

        Consumer c1 = new Consumer(store, barrier, "Consumer 1");
        Consumer c2 = new Consumer(store, barrier, "Consumer 2");
        Consumer c3 = new Consumer(store, barrier, "Consumer 3");
        Consumer c4 = new Consumer(store, barrier, "Consumer 4");

        p1.start();
        p2.start();

        c1.start();
        c2.start();
        c3.start();
        c4.start();

    }
    
}
