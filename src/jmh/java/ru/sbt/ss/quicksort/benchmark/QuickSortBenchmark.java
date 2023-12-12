package ru.sbt.ss.quicksort.benchmark;

import org.openjdk.jmh.annotations.*;
import ru.sbt.ss.quicksort.SequentialQuickSort;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Fork(3)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
public class QuickSortBenchmark {
    protected int[] arr;
    @Param(value = {"10000000", "100000000"})
    int arrSize = 0;

    @Setup(Level.Iteration)
    public void init() {
        arr = new int[arrSize];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt() % arrSize;
        }
    }
}
