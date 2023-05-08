package aso_lab3;

import java.util.concurrent.CyclicBarrier;

public class Main {
    public static final int MAX_MESSAGES = 50;

    public static void main(String[] args) {

        Store store = new Store();
        CyclicBarrier producerBarrier = new CyclicBarrier(2);
        CyclicBarrier consumerBarrier = new CyclicBarrier(2);

        // Вариант 2
        Producer producer1 = new Producer(store, "Producer-1", producerBarrier);
        Producer producer2 = new Producer(store, "Producer-2", producerBarrier);

        Consumer consumer1 = new Consumer(store, "Consumer-1", consumerBarrier);
        Consumer consumer2 = new Consumer(store, "Consumer-2", consumerBarrier);
        Consumer consumer3 = new Consumer(store, "Consumer-3", consumerBarrier);
        Consumer consumer4 = new Consumer(store, "Consumer-4", consumerBarrier);

        // Thread class имеет метод start, который запускает в отдельном потоке код написанный в методе run
        producer1.start();
        producer2.start();

        consumer1.start();
        consumer2.start();
        consumer3.start();
        consumer4.start();
    }
}
