package aso_lab3;

import java.util.concurrent.CyclicBarrier;

class Consumer extends Thread {
    private final CyclicBarrier barr;
    private final Store data;
    private final String name;
    static int max = 0;

    public Consumer(final Store data, final CyclicBarrier barr,final String name) {
        this.data = data;
        this.name = name;
        this.barr = barr;
    }

    public void run() {
        String tmp = null;
        int cons = 0;
        while (true) {
            try {
                if (max < 54) {
                    tmp = data.load(name);
                    if (tmp == null) {
                        barr.await();
                    } else {
                        System.out.println("" + name + " consumed " + tmp);
                        cons++;
                        max++;
                        System.out.println("Consumed: " + max);
                        sleep((int) (Math.random() * 1000));
                    }
                } else {
                    tmp = data.load(name);
                    sleep(2000);
                    System.out.println(name + " consumed " + cons + " elements");
                    break;
                }
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }
}