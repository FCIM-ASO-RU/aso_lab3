package aso_lab3;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

class Store {
    private final LinkedList<String> stock = new LinkedList<String>();
    private final Semaphore producer_sem = new Semaphore(1, true);
    private final Semaphore consumer_sem = new Semaphore(1, true);
    boolean response = false;

    public boolean store (final String n, final String name) {
        try {
            producer_sem.acquire();

        } catch (InterruptedException e){
            e.printStackTrace();
        }
        if (response) {
            producer_sem.release();
            return  response;
        }
        if (stock.size() <= 8) {
            stock.add(n);
            producer_sem.release();
        } else if (stock.size() == 9) {
            response = true;
            System.out.println("Stock is full");
            producer_sem.release();
        } else {
            System.out.println("Error");
            producer_sem.release();
        }
        return response;
    }

    public String load (final String name) {
        String character = null;
        try {
            consumer_sem.acquire();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        if ((response)) {
            if (stock.isEmpty()){
                response=false;
                System.out.println("Store is empty");
                consumer_sem.release();
                return character;
            }
            character = stock.getFirst();
            stock.removeFirst();
        }
        consumer_sem.release();
        return character;
    }
}