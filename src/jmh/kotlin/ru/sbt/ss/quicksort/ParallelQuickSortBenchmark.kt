package ru.sbt.ss.quicksort

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Param
import ru.sbt.ss.quicksort.sort.parallelQuickSortUsingCoroutines

@Suppress("unused")
open class ParallelQuickSortBenchmark : QuickSortBenchmark() {
    @Param(value = ["4"])
    var parallelism: Int = 0

    @Benchmark
    fun parallelUsingCoroutines() {
        array.parallelQuickSortUsingCoroutines(parallelism)
    }
}