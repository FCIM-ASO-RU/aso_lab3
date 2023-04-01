package aso_lab2;

import java.util.concurrent.CyclicBarrier;

import static aso_lab2.Store.QUANTITY;

public class Consumer extends Thread {
    private final CyclicBarrier cyclicBarrier;
    private final Store store;
    private static int cons = 0;

    public Consumer(Store _store, String _name, CyclicBarrier _cyclicBarrier) {
        store = _store;
        setName(_name);
        this.cyclicBarrier = _cyclicBarrier;
    }

    @Override
    public void run() {
        // Так как было сгенерировано 47 объектов то и потреблено столько же

        try {
            while (cons <= QUANTITY) {
                //Код выполняемый в synchronized выполняется одним объектом и этот объект держит его ключ, пока объект не отдаст ключ никто не может выполнять этот же код
                Integer response1, response2;
                response1 = store.get(getName()); //Потребитель потребляет два объекта
               response2 = store.get(getName()); //Потребитель потребляет два объекта
                if (response1 == null && response2 == null) {
                    cyclicBarrier.await();
                } else {
                    cons += 2;
                    System.out.println("Consumers got: " + cons);
                }
                sleep(2000);
            }
            System.out.println('\n' + "-->" + getName() + "  stopped");
        } catch (Exception ex) {
            System.out.println("Consumer ex : " + ex);
        }
    }

}

