package com.task3;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {

    private Rps rps = Rps.getInstance();
    private Key key = new Key();
    private Scanner scanner = new Scanner(System.in);
    private final int EXIT = 0;

    public void startMenu() {

        int computerStep = rps.generateComputerStep();
        String hmacKey = key.generateRandomKey();
        System.out.printf("HMAC: %s%n", new HMAC.Builder()
                .setKey(hmacKey)
                .setValue(String.valueOf(computerStep))
                .setShaType("HmacSHA256")
                .build()
                .generateHmac());
        printMenu();
        String numberMenu = scanner.next();
        while (!validate(numberMenu)) {
            System.out.println("Invalid menu item!");
            printMenu();
            numberMenu = scanner.next();
        }
        int stepUser = Integer.parseInt(numberMenu);
        if (stepUser != EXIT) {
            rps.userStep(stepUser);
            System.out.println("You move: " + rps.getMap().get(stepUser));
            System.out.println("Computer move: " + rps.getMap().get(computerStep));
            rps.checkWinAndLos(computerStep);
            System.out.println("HMAC key: " + hmacKey);
        }
    }

    private void printMenu() {
        System.out.println("Available moves: ");
        for (int i = 1; i < rps.getMap().size() + 1; i++) {
            System.out.printf("%d - %s%n", i, rps.getMap().get(i));
        }
        System.out.println(EXIT + " - exit");
        System.out.print("Enter you movie: ");
    }

    private boolean validate(String number) {
        final String GIVEN_STRING = "[0-" + rps.getMap().size() + "]";
        Pattern pattern = Pattern.compile(GIVEN_STRING);
        Matcher matcher;
        matcher = pattern.matcher(number);
        return matcher.matches();
    }
}
