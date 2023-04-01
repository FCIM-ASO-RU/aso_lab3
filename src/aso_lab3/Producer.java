package aso_lab2;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

import static aso_lab2.Store.QUANTITY;

// Наследуюясь от класса Thread мы получаем возможность переписать код в методе run и запустить его в новом потоке через start
public class Producer extends Thread {
    private final CyclicBarrier cyclicBarrier;
    private final Store store;

    // Библиотека рандо используется для генерирования нечетных чисел
    private final static Random random = new Random();

    //Статическая переменная a.k.a одна на все объекты, которая определяет количество созданных объектов в данно мслучае
    private static int pro = 0;

    public Producer(Store _store, String _name, CyclicBarrier _cyclicBarrier) {
        store = _store;
        this.cyclicBarrier = _cyclicBarrier;
        //Метод Thread класса которые позволяет переиминовать поток в человеческое название
        setName(_name);
    }

    @Override
    public void run() {
        // По заданию сгенерировано должно быть не более 47 нечетных чисел

        try {
            while (pro <= QUANTITY) {
                boolean isAdded1, isAdded2;
                int oddNumber1 = random.nextInt(50) * 2 + 1;; //формула нечетных чисел, рандомное число в пределах 50, вынесен в отдельный метод
                int oddNumber2 = random.nextInt(50) * 2 + 1;;
                isAdded1 = store.put(getName(), oddNumber1); // у нас один поставщик делает два ообъекта
                isAdded2 = store.put(getName(), oddNumber2); // у нас один поставщик делает два ообъекта
                if (isAdded1 && isAdded2) {
                    cyclicBarrier.await();
                }
               else {
                    pro += 2; // pro = pro + 2 та же запись
                    System.out.println("Producers put " + pro);
                }
            }
            System.out.println("-->" + getName() + "  stopped");
        } catch (Exception ex) {
            System.out.println("Producer ex : " + ex);
        }
    }

}
