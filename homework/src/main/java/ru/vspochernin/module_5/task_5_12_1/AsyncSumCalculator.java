package ru.vspochernin.module_5.task_5_12_1;

import java.util.concurrent.CompletableFuture;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

class AsyncSumCalculator {
    public static Single<Integer> calculateSum(int n) {
        CompletableFuture<Integer> sumFuture = CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            for (int i = 1; i <= n; i++) {
                sum += i;
            }
            return sum;
        });
        return Single.fromFuture(sumFuture)
                .subscribeOn(Schedulers.computation());
    }
}