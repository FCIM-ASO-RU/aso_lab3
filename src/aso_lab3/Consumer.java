package aso_lab3;

import java.util.concurrent.CyclicBarrier;

public class Consumer extends Thread {
    
    private static final int max = 0; // указываете в соответствии с вариантом
    
    private final Store store;
    private final CyclicBarrier barrier;
    
    public Consumer(Store _store, CyclicBarrier _barrier, String name) {
        store = _store;
        barrier = _barrier;
        setName(name);
    }
    
    @Override
    public void run() {
        
    }
    
}