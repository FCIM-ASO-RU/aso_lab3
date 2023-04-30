package aso_lab3;

import java.util.Objects;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Producer extends Thread {
    private final Store store;

    private int number;

    private char[] array;
    private final CyclicBarrier barrier;
    
    public Producer(Store _store, CyclicBarrier _barrier, String name) {
        store = _store;
        barrier = _barrier;
        setName(name);
        array = new char[]{'a','e','i','u','o'};
    }
    
    @Override
    public void run() {
        if(Objects.isNull(array)){
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
        else
            for (int i = 0; i <2; i++) {
                store.put(array[i]);
                System.out.println(this.getName() + " put: " + i);
                try {
                    sleep((int)(Math.random() * 100));
                } catch (InterruptedException e) { }
            }
    }
    
}