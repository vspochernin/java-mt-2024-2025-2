package ru.vspochernin.module_1.task_1_12_2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class ParallelFactorial {
    public static BigInteger execute(int t, int n) throws InterruptedException {
        if (n == 0) {
            return BigInteger.ONE;
        }

        BigInteger[] parts = new BigInteger[t];
        Arrays.fill(parts, BigInteger.ONE);

        int end = 1;
        int diff = (n + t - 1) / t;
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            int start = end;
            end = Math.min(start + diff, n);

            Thread thread = new Thread(new PartFactorial(parts, i, start, end));
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.join();
        }

        BigInteger result = BigInteger.ONE;
        for (BigInteger part : parts) {
            result = result.multiply(part);
        }

        return result.multiply(BigInteger.valueOf(n));
    }

    static class PartFactorial implements Runnable {

        private final BigInteger[] parts;
        private final int n;
        private final int l;
        private final int r;

        PartFactorial(BigInteger[] parts, int n, int l, int r) {
            this.parts = parts;
            this.n = n;
            this.l = l;
            this.r = r;
        }

        @Override
        public void run() {
            BigInteger result = BigInteger.ONE;
            for (int i = l; i < r; i++) {
                result = result.multiply(BigInteger.valueOf(i));
            }
            parts[n] = result;
        }
    }
}
