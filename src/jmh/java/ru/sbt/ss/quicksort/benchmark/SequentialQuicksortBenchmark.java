package ru.sbt.ss.quicksort.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import ru.sbt.ss.quicksort.SequentialQuickSort;

public class SequentialQuicksortBenchmark extends QuickSortBenchmark {
    @Benchmark
    public void sequential() {
        SequentialQuickSort.quickSort(arr, 0, arrSize);
    }
}
