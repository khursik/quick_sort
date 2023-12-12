package ru.sbt.ss.quicksort;

import java.util.Random;

public class SequentialQuickSort {

    public static void quickSort(int[] arr, int begin, int end) {
        if (end - begin <= 1) {
            return;
        }
        int m = partition(arr, begin, end);
        quickSort(arr, begin, m);
        quickSort(arr, m, end);
    }

    static int partition(int[] arr, int begin, int end) {
        Random random = new Random();
        int pivotInd = random.nextInt(begin, end);
        int pivot = arr[pivotInd];
        var p1 = begin;
        var p2 = end - 1;
        while (p1 <= p2) {
            while (arr[p1] < pivot) p1++;
            while (arr[p2] > pivot) p2--;
            if (p1 <= p2) swap(arr, p1++, p2--);
        }

        return p1;
    }
    private static void swap(int[] arr, int i, int end) {
        int swapTemp = arr[i];
        arr[i] = arr[end];
        arr[end] = swapTemp;
    }
}
