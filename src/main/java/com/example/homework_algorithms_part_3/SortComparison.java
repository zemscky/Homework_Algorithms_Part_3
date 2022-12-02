package com.example.homework_algorithms_part_3;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class SortComparison {
    public static void main(String[] args) {
        int[] array = generateRandomArray();
        benchmarkSort(Arrays.copyOf(array, array.length),SortComparison::sortBubble);
        benchmarkSort(Arrays.copyOf(array, array.length),SortComparison::sortSelection);
        benchmarkSort(Arrays.copyOf(array, array.length),SortComparison::sortInsertion);
    }

    private static void benchmarkSort(int[] array, Consumer<int[]> sortFunction) {
        long start = System.currentTimeMillis();
        sortFunction.accept(array);
        long end = System.currentTimeMillis();
        System.out.println("isSorted = " + isSorted(array));
        System.out.println("executionTime = " + (end - start) + " milliseconds");
    }

    private static void sortBubble(int[] array){
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
    private static void sortSelection(int[] array){
        for (int i = 0; i < array.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            int temp = array[i];
            array[i] = array[minElementIndex];
            array[minElementIndex] = temp;
        }
    }
    private static void sortInsertion(int[] array){
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i;
            while (j > 0 && array[j - 1] >= temp) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = temp;
        }
    }

    private static int[] generateRandomArray() {
        return IntStream
                .generate(() -> ThreadLocalRandom.current().nextInt(0,1_000_000))
                .limit(10_000)
                .toArray();
    }
    private static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
