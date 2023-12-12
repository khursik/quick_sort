package ru.sbt.ss.quicksort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class ParallelQuickSortTest {
    private static final int ARR_SIZE = 100_000_000;
    private int[] array;
    @BeforeEach
    public void generateArray() {
        Random random = new Random();
        int[] array = new int[ARR_SIZE];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt() % ARR_SIZE;
        }
        this.array = array;
    }
    @Test
    void writeSortResultInFileTest() {
        long start = System.currentTimeMillis();
        ParallelQuickSort.parallelQuickSortUsingForkJoinPool(array, 4);
        long execTime = System.currentTimeMillis() - start;
        try(FileWriter writer = new FileWriter("par_quicksort_result_3.txt", false)) {
            for (int a : array) {
                writer.write(a + "\n");
            }
            writer.write("Execution time: " + execTime);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void sortArrayInAscendingOrderTest() {
        ParallelQuickSort.parallelQuickSortUsingForkJoinPool(array, 4);
        ascendingArrayCheck(array);
    }

    public void ascendingArrayCheck(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (!(array[i] >= array[i-1])) {
                Assertions.fail("Array has sorted incorrectly! :(");
            }
        }
    }
}
