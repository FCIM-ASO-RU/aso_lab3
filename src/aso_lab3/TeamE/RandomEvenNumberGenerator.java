package aso_lab3.TeamE;

import java.util.Random;

// 5 ВАРИАНТ

public class RandomEvenNumberGenerator {
    private static final Random random = new Random();

    static int generate() {
        int num;
        do {
            num = random.nextInt(100);
        } while (num % 2 != 0);
        return num;
    }
}