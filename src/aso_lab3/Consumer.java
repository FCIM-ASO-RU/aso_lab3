package aso_lab3;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Consumer extends Thread {
    private final Store store;

    private int number;
    private final CyclicBarrier barrier;
    
    public Consumer(Store _store, CyclicBarrier _barrier, String name) {
        store = _store;
        barrier = _barrier;
        setName(name);
    }

    public void run() {
        char value = 0;
        for (int i = 0; i < 2; i++) {
            value = store.get();
            if(value == 0){
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }

            }
            else {
                System.out.println(this.getName() + " got: " + value);
            }
        }
    }
    
}