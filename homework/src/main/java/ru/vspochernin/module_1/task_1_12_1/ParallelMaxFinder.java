package ru.vspochernin.module_1.task_1_12_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ParallelMaxFinder {
    public static long execute(int t, long[] array) throws InterruptedException {
        if (array.length == 0) {
            return 0L;
        }

        long[] maxValues = new long[t];
        Arrays.fill(maxValues, Long.MIN_VALUE);

        int end = 0;
        int diff = (array.length + t - 1) / t;
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            int start = end;
            end = Math.min(end + diff, array.length);

            MaxInPartFinder maxInPartFinder = new MaxInPartFinder(array, i, maxValues, start, end);
            Thread thread = new Thread(maxInPartFinder);
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long maxValue = Long.MIN_VALUE;
        for (long value : maxValues) {
            if (value > maxValue) {
                maxValue = value;
            }
        }

        return maxValue;
    }

    static class MaxInPartFinder implements Runnable {

        private final long[] array;
        private final int n;
        private final long[] maxValues;
        private final int l;
        private final int r;

        MaxInPartFinder(long[] array, int n, long[] maxValues, int l, int r) {
            this.array = array;
            this.n = n;
            this.maxValues = maxValues;
            this.l = l;
            this.r = r;
        }

        @Override
        public void run() {
            for (int i = l; i < r; i++) {
                if (array[i] > maxValues[n]) {
                    maxValues[n] = array[i];
                }
            }
        }
    }
}