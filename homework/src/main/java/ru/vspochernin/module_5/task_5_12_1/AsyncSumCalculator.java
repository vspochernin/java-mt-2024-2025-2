package ru.vspochernin.module_5.task_5_12_1;

import java.util.concurrent.CompletableFuture;

class AsyncSumCalculator {
    public static CompletableFuture<Integer> calculateSum(int n) {
        return CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            for (int i = 1; i <= n; i++) {
                sum += i;
            }
            return sum;
        });
    }
}