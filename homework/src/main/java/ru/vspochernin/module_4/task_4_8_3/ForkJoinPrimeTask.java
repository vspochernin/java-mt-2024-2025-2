package ru.vspochernin.module_4.task_4_8_3;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

class ForkJoinPrimeTask extends RecursiveTask<Long> {

    private static final int THRESHOLD = 10_000;

    private final long left;
    private final long right;

    ForkJoinPrimeTask(long left, long right) {
        this.left = left;
        this.right = right;
    }

    public static long countPrimes(long l, long r) {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinPrimeTask task = new ForkJoinPrimeTask(l, r);
        return pool.invoke(task);
    }

    @Override
    protected Long compute() {
        if (right - left + 1 <= THRESHOLD) {
            long counter = 0;
            for (long i = left; i <= right; i++) {
                if (isPrime(i)) counter++;
            }
            return counter;
        } else {
            long mid = (left + right) / 2;

            ForkJoinPrimeTask leftTask = new ForkJoinPrimeTask(left, mid);
            ForkJoinPrimeTask rightTask = new ForkJoinPrimeTask(mid + 1, right);

            leftTask.fork();
            rightTask.fork();

            return leftTask.join() + rightTask.join();
        }
    }

    private static boolean isPrime(long n) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        for (long i = 5; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}