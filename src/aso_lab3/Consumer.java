package aso_lab3;

import java.util.LinkedList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Consumer extends Thread {

    private final LinkedList<NumereImpare> consumed;

    private final Store store;
    private final CyclicBarrier barrier;
    private final Semaphore semaphore;

    public Consumer(Store _store, Semaphore _semaphore, CyclicBarrier _barrier, String name) {
        setName(name);
        store = _store;
        barrier = _barrier;
        semaphore = _semaphore;
        consumed = new LinkedList<>();
    }

    @Override
    public void run() {
        NumereImpare taken;
        try {
            while (true) {
                synchronized (this) {
                    semaphore.acquire();
                    if (store.getBoolConsumerStop()) {
                        semaphore.release();
                        barrier.await();
                        System.out.printf("%s: finished | consumed total count: %2d | total: %s \n", getName(), consumed.size(), getProductsStr(consumed));
                        break;
                    }
                    taken = store.get();
                    if (taken != null) {
                        consumed.add(taken);
                        System.out.printf("%s: get - %2d | Consumed total count: %2d  \n", getName(), taken.getNumber(), consumed.size());
                        sleep(20);
                    } else {
                        System.out.printf("%s: not get \n", getName());
                    }
                    semaphore.release();
                }
            }
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
            System.out.println(getName() + " broken");
        }
    }

    public synchronized String getProductsStr(LinkedList<NumereImpare> _products) {
        NumereImpareStr numcons = new NumereImpareStr();
        return numcons.getProductsStr(_products);
    }
}