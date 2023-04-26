package aso_lab3;

import aso_lab3.TeamE.Consumer;
import aso_lab3.TeamE.Producer;
import aso_lab3.TeamE.Store;

import java.util.concurrent.CyclicBarrier;

public class Main {

    public static void main(String[] args) {
        Store store = new Store();
        CyclicBarrier barrier = new CyclicBarrier(6); // вместо нуля указываете общее кол-во производителей и потребителей

        // Create and start the producer threads
        for (int i = 0; i < 3; i++) {
            Producer producer = new Producer(store, barrier, "Prutean & Neer. Producer: " + i);
            producer.start();
        }

        // Create and start the consumer threads
        for (int i = 0; i < 3; i++) {
            Consumer consumer = new Consumer(store, barrier, "Prutean & Neer. Consumer: " + i);
            consumer.start();
        }
    }
}
