package com.task3;

import java.util.Random;

public class Key {

    public static String generateRandomKey() {
        Random random = new Random();
        String getKey = "";
        for (int i = 0; i < 32; i++) {
            int rand = 48 + random.nextInt(90 - 48);
            while (rand > 57 && rand < 65) {
                rand = 48 + random.nextInt(90 - 48);
            }
            getKey += (char) rand;
        }
        return getKey;
    }
}
