package aso_lab3;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Producer extends Thread {

    private final LinkedList<NumereImpare> produced;
    private final Random random;

    private final Store store;
    private final CyclicBarrier barrier;
    private final Semaphore semaphore;

    public Producer(Store _store, Semaphore _semaphore, CyclicBarrier _barrier,  String name) {
        setName(name);
        produced = new LinkedList<>();
        random = new Random();
        store = _store;
        barrier = _barrier;
        semaphore = _semaphore;
    }

    @Override
    public void run() {
        boolean info;
        try {
            while (true) {
                synchronized (this) {
                    semaphore.acquire();
                    if (store.getBoolProducerStop()) {
                        semaphore.release();
                        barrier.await();
                        System.out.printf("%s: finished | Produced total count: %2d | total: %s \n", getName(), produced.size(), getProductsStr(produced));
                        break;
                    }
                    NumereImpare product = produceNumereImpare();
                    info = store.put(product);
                    if (info) {
                        produced.add(product);
                        System.out.printf("%s: put - %2d | Produced total count: %2d  \n", getName(), product.getNumber(), produced.size());
                    } else {
                        System.out.printf("%s: not put \n", getName());
                    }
                    semaphore.release();
                }
            }
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
            System.out.println(getName() + " broken");
        }
    }
    public synchronized NumereImpare produceNumereImpare() {
        NumereImpare product = new NumereImpare();
        int max = 49;
        int min = 2;
        int tempInt;
        tempInt = random.nextInt(max - min) + min;
        tempInt = tempInt * 2 + 1;
        product.setNumber(tempInt);
        //System.out.println("produceNumereImpare: " + product.getNumber() );
        return product;
    }
    public synchronized String getProductsStr(LinkedList<NumereImpare> _products) {
        NumereImpareStr numprod = new NumereImpareStr();
        return numprod.getProductsStr(_products);
    }
}