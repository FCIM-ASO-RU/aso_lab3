package aso_lab3.TeamE;

import java.util.concurrent.CyclicBarrier;

// 5 ВАРИАНТ

public class Consumer extends Thread {
    
    private static final int max = 33; // указываете в соответствии с вариантом
    
    private final Store store;
    private final CyclicBarrier barrier;
    
    public Consumer(Store _store, CyclicBarrier _barrier, String name) {
        store = _store;
        barrier = _barrier;
        setName(name);
    }
    
    @Override
    public void run() {
        try
        {

            for (int i = 0; i < max; i++) {
                store.get();
                barrier.await(); // synchronize threads after each iteration
            }

            System.out.println("Consumer " + getName() + " finished");

        }
        catch (Exception e)
        {
            Thread.currentThread().interrupt();
        }
    }
}