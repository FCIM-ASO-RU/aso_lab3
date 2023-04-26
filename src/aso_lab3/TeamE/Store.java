package aso_lab3.TeamE;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

// 5 ВАРИАНТ

public class Store {
    
    private static final int capacity = 3; // указываете в соответствии с вариантом (D = 3)
    private boolean available; 
    private final ArrayList<Integer> stock; // меняете ReplaceMe на желаемый вами класс-коллекцию (четные числа)
    
    final Semaphore producers = new Semaphore(1, true);
    final Semaphore consumers = new Semaphore(1, true);
    
    public Store() {
        available = false;
        stock = new ArrayList<>();
    }
    
    public void get() {
        try
        {
            consumers.acquire();

            while (!available) {
                producers.release();
                producers.acquire();
            }

            if (stock.isEmpty()) {
                System.out.println("Store is empty, consumer " + Thread.currentThread().getName() + " cannot get an item.");
            } else {
                int item = stock.remove(0);
                System.out.println("Consumer " + Thread.currentThread().getName() + " got " + item);

                if (stock.isEmpty()) {
                    available = false;
                }
            }

            producers.release();
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
    
    public void put(int number) {
        try
        {
            producers.acquire();

            while (available) {
                consumers.release();
                consumers.acquire();
            }

            if (stock.size() > capacity) {
                System.out.println("Store is full, producer " + Thread.currentThread().getName() + " cannot put " + number);
            } else {
                stock.add(number);
                System.out.println("Producer " + Thread.currentThread().getName() + " put " + number);

                if (stock.size() == capacity) {
                    available = true;
                }
            }

            consumers.release();
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}