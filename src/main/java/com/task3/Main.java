package com.task3;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Rps rps = Rps.getInstance();
        if (args.length % 2 != 0 && checkRepetition(args) && args.length > 1) {
            rps.createArrayNumberOfSteps(args);
            new Menu().startMenu();
        } else {
            System.out.println("Error");
        }
    }

    public static boolean checkRepetition(String[] cells) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < cells.length; i++) {
            if (set.contains(cells[i]))
                return false;
            set.add(cells[i]);
        }
        return true;
    }
}
