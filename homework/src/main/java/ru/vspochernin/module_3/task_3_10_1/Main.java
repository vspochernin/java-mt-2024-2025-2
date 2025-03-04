package ru.vspochernin.module_3.task_3_10_1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {

    private static final double A = 3.0;
    private static final double B = 4.0;
    private static final double C = 10.0;
    private static final double D = 16.0;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Double> sumSquaresFuture = CompletableFuture.supplyAsync(() -> calculateSumSquares(A, B));
        CompletableFuture<Double> logFuture = CompletableFuture.supplyAsync(() -> calculateLog(C));
        CompletableFuture<Double> sqrtFuture = CompletableFuture.supplyAsync(() -> calculateSqrt(D));

        CompletableFuture<Double> multiplyFuture =
                sumSquaresFuture.thenCombine(logFuture, (sumSquares, log) -> sumSquares * log);

        CompletableFuture<Double> divisionFuture =
                multiplyFuture.thenCombine(sqrtFuture, (multiply, sqrt) -> multiply / sqrt);

        System.out.println("Final result of the formula: " + divisionFuture.get());
    }

    private static double calculateSumSquares(double a, double b) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double result = (a * a + b * b);
        System.out.println("Calculating sum of squares: " + result);
        return result;
    }

    private static double calculateLog(double c) {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double result = Math.log(c);
        System.out.println("Calculating log(c): " + result);
        return result;
    }

    private static double calculateSqrt(double d) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double result = Math.sqrt(d);
        System.out.println("Calculating sqrt(d): " + result);
        return result;
    }
}