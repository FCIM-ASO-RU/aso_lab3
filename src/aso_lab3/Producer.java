package aso_lab3;

import java.util.concurrent.CyclicBarrier;

class Producer extends Thread {
    private final CyclicBarrier barr;
    private final Store data;
    private final String name;
    private final String[] vawel;
    static int max = 0;

    public Producer(final Store data, final CyclicBarrier barr, final String name) {
        this.data = data;
        this.name = name;
        this.barr = barr;
        vawel = new String[]{"Lipsi HA", "Za деньги да", "KAK MOMMY", "POPSTAR", "MONEYKEN LOVE"};
    }

    @Override
    public void run() {
        int tmp;
        boolean response;
        int prod = 0;

        while (true) {
            try {
                if (max < 54) {
                    tmp = (int) (Math.random() * 5);
                    response = data.store(vawel[tmp], name);

                    if (response) {
                        barr.await();
                    } else {
                        System.out.println(name + " produced " + vawel[tmp]);
                        prod++;
                        max++;
                        sleep((int) (Math.random() * 1000));
                    }
                } else {
                    tmp = (int) (Math.random() * 5);
                    response = data.store(vawel[tmp], name);
                    if (response) {
                        barr.await();
                    }

                    sleep(2000);
                    System.out.println(name + " produced " + prod + " elements");
                    break;
                }
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }
}
