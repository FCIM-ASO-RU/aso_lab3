package aso_lab3;

import java.util.LinkedList;

public class Store {
    
    private static final int capacity = 5; // обьем склада
    private static final int Z = 50; // обьем продуктов которые нужно произвести и потребить
    private final LinkedList<NumereImpare> storage;

    private int quantity; // количество обьектов на складе
    private int produced; // количество произведенных обьектов
    private int consumed; // количество потребленных обьектов
    public Store() {
        storage = new LinkedList<>();
        quantity = 0;
        produced = 0;
        consumed = 0;
    }
    
    public synchronized NumereImpare get() {
        NumereImpare to_get = null;
        if (quantity - 1 >= 0 && consumed < Z) {
            quantity -= 1;
            consumed++;
            to_get = storage.removeFirst();
            printPutGet();
        } else {
            System.out.println("Store is empty");
        }
        return to_get;
    }
    
    public synchronized boolean put(NumereImpare something) {
        if( quantity + 1 <= capacity && produced < Z) {
            quantity+=1;
            produced++;
            storage.add(something);
            printPutGet();
            return true;
        } else {
            System.out.println("Store is overflow");
            return false;
        }
    }
    synchronized void printPutGet() {
        System.out.printf("Storage count: %2d | produced total count: %2d | consumed total count: %2d |  numbers: %s \n", quantity, produced, consumed, getProductsStr(storage));
    }
    public synchronized boolean getBoolProducerStop() { return produced == Z; }
    public synchronized boolean getBoolConsumerStop() { return consumed == Z; }
    public synchronized String getProductsStr(LinkedList<NumereImpare> _products) {
        NumereImpareStr numstore = new NumereImpareStr();
        return numstore.getProductsStr(_products);
    }
}