package com.task3;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Rps {

    private int[] numberOfSteps;
    private Map<Integer, String> map = new HashMap<>();
    private final Random RANDOM = new Random();
    private static Rps instance;

    private Rps() {
    }

    public Map<Integer, String> getMap() {
        return map;
    }

    public static Rps getInstance() {
        if (instance == null) {
            instance = new Rps();
        }
        return instance;
    }

    public void userStep(int numberOfStep) {
        int index = Arrays.stream(numberOfSteps).boxed().collect(Collectors.toList()).indexOf(numberOfStep);
        rotateInline(index);
    }

    public void checkWinAndLos(int compStep) {
        int winners[] = Arrays.copyOfRange(numberOfSteps, 0,  numberOfSteps.length / 2);
        int losers[] = Arrays.copyOfRange(numberOfSteps, numberOfSteps.length / 2, numberOfSteps.length - 1);
        if (Arrays.asList(converIntArrToIntegerArray(winners)).contains(compStep)) {
            System.out.println("You loss!");
        } else if (Arrays.asList(converIntArrToIntegerArray(losers)).contains(compStep)) {
            System.out.println("You win!");
        } else {
            System.out.println("Draw!");
        }
    }

    public void createArrayNumberOfSteps(String[] steps) {
        for (int i = 0; i < steps.length; i++) {
            map.put(i + 1, steps[i]);
        }
        numberOfSteps = Arrays.stream(map.keySet().toArray(new Integer[0]))
                .mapToInt(number -> Integer.parseInt(number.toString()))
                .toArray();
    }

    public int generateComputerStep() {
        return RANDOM.nextInt(numberOfSteps.length) + 1;
    }

    private Integer[] converIntArrToIntegerArray(int[] array) {
        return IntStream.of(array)
                .boxed()
                .toArray(Integer[]::new);
    }

    private void swap(int first, int second) {
        int temp = numberOfSteps[second];
        numberOfSteps[second] = numberOfSteps[first];
        numberOfSteps[first] = temp;
    }

    private void rotateInline(int numberOfPositions) {
        reverse(0, numberOfPositions);
        reverse(numberOfPositions + 1, numberOfSteps.length - 1);
        reverse(0, numberOfSteps.length - 1);
    }

    private void reverse(int start, int end) {
        for (int i = start; i <= (start + end) / 2; i++) {
            swap(i, start + end - i);
        }
    }
}
