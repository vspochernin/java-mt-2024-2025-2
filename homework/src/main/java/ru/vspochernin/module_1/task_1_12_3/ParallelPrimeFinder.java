package ru.vspochernin.module_1.task_1_12_3;

import java.util.ArrayList;
import java.util.List;

class ParallelPrimeFinder {
    public static List<Long> execute(int t, long l, long r) throws InterruptedException {
        List<Long> result = new ArrayList<>();

        List<List<Long>> parts = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            parts.add(new ArrayList<>());
        }

        long end = l;
        long len = r - l + 1;
        long diff = (len + t - 1) / t;
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            long start = end;
            end = Math.min(start + diff, r);

            Thread thread = new Thread(new PrimePartFinder(parts, i, start, end));
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.join();
        }

        for (List<Long> part : parts) {
            result.addAll(part);
        }

        if (isPrime(r)) {
            result.add(r);
        }
        return result;
    }

    static class PrimePartFinder implements Runnable {

        private final List<List<Long>> parts;
        private final int n;
        private final long l;
        private final long r;

        PrimePartFinder(List<List<Long>> parts, int n, long l, long r) {
            this.parts = parts;
            this.n = n;
            this.l = l;
            this.r = r;
        }

        @Override
        public void run() {
            for (long i = l; i < r; i++) {
                if (isPrime(i)) {
                    parts.get(n).add(i);
                }
            }
        }
    }

    private static boolean isPrime(long n) {
        if (n == 1) {
            return false;
        }
        for (long i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
