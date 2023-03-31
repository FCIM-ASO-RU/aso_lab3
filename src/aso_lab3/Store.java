package aso_lab3;

import java.util.concurrent.Semaphore;

public class Store {
    
    private static final int capacity = 0; // указываете в соответствии с вариантом
    private boolean available; 
    private final ReplaceMe stock; // меняете ReplaceMe на желаемый вами класс-коллекцию
    
    final Semaphore producers = new Semaphore(1, true);
    final Semaphore consumers = new Semaphore(1, true);
    
    public Store() {
        available = false;
        stock = new ReplaceMe();
    }
    
    public void get() {
        
    }
    
    public void put(ReplaceMe something) {
        // меняете ReplaceMe на то, что будете помещать внутрь
    }
    
}