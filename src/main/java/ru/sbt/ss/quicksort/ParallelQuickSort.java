package ru.sbt.ss.quicksort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class ParallelQuickSort {
    public static void parallelQuickSortUsingForkJoinPool(int[] arr, int threads) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(threads);
        forkJoinPool.invoke(new RecursiveAction() {
            @Override
            public void compute() {
                parallelQuickSortUsingForkJoinPool(arr, 0, arr.length);
            }
        });
        forkJoinPool.shutdown();
    }

    static void parallelQuickSortUsingForkJoinPool(int[] arr, int i, int j) {
        if (j - i < 1_000) {
            SequentialQuickSort.quickSort(arr, i, j);
            return;
        }

        int m = SequentialQuickSort.partition(arr, i, j);
        List<RecursiveAction> tasks = new ArrayList<>();
        if (m > i) {
            tasks.add(new RecursiveAction() {
                @Override
                protected void compute() {
                    parallelQuickSortUsingForkJoinPool(arr, i, m);
                }
            });
        }
        if (j > m) {
            tasks.add(new RecursiveAction() {
                @Override
                protected void compute() {
                    parallelQuickSortUsingForkJoinPool(arr, m, j);
                }
            });
        }
        tasks.forEach(ForkJoinTask::fork);
        tasks.forEach(ForkJoinTask::join);
    }
}
