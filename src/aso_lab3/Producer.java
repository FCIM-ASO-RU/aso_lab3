package aso_lab3;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;


// Наследуюясь от класса Thread мы получаем возможность переписать код в методе run и запустить его в новом потоке через start
class Producer extends Thread {
    private final CyclicBarrier barrier;
    private final String name;
    private final Store store;

    private final Random random = new Random();

    public Producer(Store store, String name, CyclicBarrier barrier) {
        this.store = store;
        this.name = name;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        int producedIntegers = 0;

        while (true) {
            try {
                Thread.sleep(random.nextInt(500) + 1000);
                int oddNumber = (random.nextInt(50) * 2 + 1);
                if (store.getMessageCount() <= Main.MAX_MESSAGES) {
                    if (store.isQueueEmpty()) {
                        System.out.println(name + " produced " + oddNumber);
                        store.push(oddNumber);
                        barrier.await();
                    } else {
                        System.out.println(name + " produced " + oddNumber);
                        producedIntegers++;
                    }
                } else {
                    System.out.println(name + " produced " + producedIntegers + " elements");
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

