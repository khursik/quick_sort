package ru.sbt.ss.quicksort.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import ru.sbt.ss.quicksort.ParallelQuickSort;

public class ParallelQuicksortBenchmark extends QuickSortBenchmark {
    @Param(value = {"1", "2", "4", "8"})
    int parallelism = 0;
    @Benchmark
    public void parallelUsingForkJoinPool() {
        ParallelQuickSort.parallelQuickSortUsingForkJoinPool(arr, parallelism);
    }
}
