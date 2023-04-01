package aso_lab2;

import java.util.concurrent.CyclicBarrier;

public class Main {

    public static void main(String[] args) {
        //Создаем объект Магазина
        Store store = new Store();
        // 2 - произваодителя и 4 потребителя
        CyclicBarrier cyclicBarrier = new CyclicBarrier(6);
        //По заданию два производителя и их наименования
        Producer producer1 = new Producer(store, "Producer-1", cyclicBarrier);
        Producer producer2 = new Producer(store, "Producer-2", cyclicBarrier);

        //По заданию 4 потребителя
        Consumer consumer1 = new Consumer(store, "Consumer-1", cyclicBarrier);
        Consumer consumer2 = new Consumer(store, "Consumer-2", cyclicBarrier);
        Consumer consumer3 = new Consumer(store, "Consumer-3", cyclicBarrier);
        Consumer consumer4 = new Consumer(store, "Consumer-4", cyclicBarrier);

        //Thread class имеет метод start, который запускает в отдельном потоке код написанный в методе run
        producer1.start();
        producer2.start();

        consumer1.start();
        consumer2.start();
        consumer3.start();
        consumer4.start();
    }
    
}
