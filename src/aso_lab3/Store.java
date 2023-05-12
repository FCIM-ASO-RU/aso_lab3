package aso_lab3;

import java.util.concurrent.Semaphore;

public class Store {
    private boolean available;
    private char number = ' ';

    final Semaphore producers = new Semaphore(1, true);
    final Semaphore consumers = new Semaphore(1, true);


    public synchronized char get() {
        while (!available) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        available = false;
        notifyAll();
        return number;
    }

    public synchronized void put(char c) {
        while (available) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        this.number = c;
        available = true;
        notifyAll();
    }

}