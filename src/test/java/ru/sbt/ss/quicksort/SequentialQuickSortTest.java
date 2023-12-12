package ru.sbt.ss.quicksort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class SequentialQuickSortTest {
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
        SequentialQuickSort.quickSort(array, 0, ARR_SIZE);
        long execTime = System.currentTimeMillis() - start;
        try(FileWriter writer = new FileWriter("seq_quicksort_result_3.txt", false)) {
            for (int a : array) {
                writer.write(a + "\n");
            }
            writer.write("Execution time: " + execTime);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ascendingArrayCheck(array);
    }

    @Test
    void sortArrayInAscendingOrderTest() {
        SequentialQuickSort.quickSort(array, 0, ARR_SIZE);
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
