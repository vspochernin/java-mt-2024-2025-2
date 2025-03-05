package ru.vspochernin.module_4.task_4_8_1;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class ForkJoinFactorialTask extends RecursiveTask<Long> {

    private static final long THRESHOLD = 10;

    private final long start;
    private final long end;

    ForkJoinFactorialTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public static long factorial(long n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinFactorialTask task = new ForkJoinFactorialTask(2, n);
        return pool.invoke(task);
    }

    @Override
    protected Long compute() {
        if (end - start + 1 <= THRESHOLD) {
            return computeSequential(start, end);
        }

        long mid = (start + end) / 2;
        ForkJoinFactorialTask leftTask = new ForkJoinFactorialTask(start, mid);
        ForkJoinFactorialTask rightTask = new ForkJoinFactorialTask(mid + 1, end);

        return leftTask.join() * rightTask.join();
    }

    private long computeSequential(long start, long end) {
        long result = 1;

        for (long i = start; i <= end; i++) {
            result *= i;
        }

        return result;
    }
}
