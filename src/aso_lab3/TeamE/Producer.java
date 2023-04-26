package aso_lab3.TeamE;

import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

// 5 ВАРИАНТ

public class Producer extends Thread {
    
    private static final int max = 33; // указываете в соответствии с вариантом (Z = 33)
    private static final ArrayList<Integer> products = new ArrayList<>(); // меняете на то, что должно производится (четные числа)
    
    private final Store store;
    private final CyclicBarrier barrier;
    
    public Producer(Store _store, CyclicBarrier _barrier, String name) {
        store = _store;
        barrier = _barrier;
        setName(name);
    }
    
    @Override
    public void run() {
        try {
            for (int i = 0; i < max; i++) {
                int product = RandomEvenNumberGenerator.generate();
                products.add(product);
                store.put(product);

                barrier.await(); // synchronize threads after each iteration
            }

            System.out.println("Producer " + getName() + " finished");

        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
}