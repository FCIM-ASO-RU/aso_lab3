package aso_lab3;

import java.util.concurrent.CyclicBarrier;

public class Producer extends Thread {
    
    private static final int max = 0; // указываете в соответствии с вариантом
    private static final NumereImpare[] products = new NumereImpare[]{}; // меняете на то, что должно производится
    
    private final Store store;
    private final CyclicBarrier barrier;
    
    public Producer(Store _store, CyclicBarrier _barrier, String name) {
        store = _store;
        barrier = _barrier;
        setName(name);
    }
    
    @Override
    public void run() {
        
    }
    
}