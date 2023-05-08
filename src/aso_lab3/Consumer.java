package aso_lab3;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

class Consumer extends Thread {
    private final CyclicBarrier barrier;
    private final Store store;
    private final String name;

    private final Random random = new Random();

    public Consumer(Store store, String name, CyclicBarrier barrier) {
        this.store = store;
        this.name = name;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        Optional<Integer> consumedInteger;
        int consumedIntegers = 0;

        while (true) {
            try {
                Thread.sleep(random.nextInt(500) + 1000);
                if (store.getMessageCount() <= Main.MAX_MESSAGES) {
                    consumedInteger = store.consume();
                    if (consumedInteger.isEmpty()) {
                        barrier.await();
                    } else {
                        System.out.println(name + " consumed " + consumedInteger.get());
                        consumedIntegers++;
                    }
                } else {
                    System.out.println(name + " consumed " + consumedIntegers + " elements");
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

