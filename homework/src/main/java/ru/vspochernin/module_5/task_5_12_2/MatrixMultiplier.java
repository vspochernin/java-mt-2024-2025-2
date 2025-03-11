package ru.vspochernin.module_5.task_5_12_2;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.List;

class MatrixMultiplier {
    public static int[][] multiply(int[][] a, int[][] b) throws Exception {
        int rowsA = a.length;
        int colsA = a[0].length;
        int colsB = b[0].length;
        int[][] result = new int[rowsA][colsB];

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < rowsA; i++) {
            int row = i;
            Future<?> future = executor.submit(() -> {
                for (int j = 0; j < colsB; j++) {
                    for (int k = 0; k < colsA; k++) {
                        result[row][j] += a[row][k] * b[k][j];
                    }
                }
            });
            futures.add(future);
        }

        for (Future<?> future : futures) {
            future.get();
        }

        executor.shutdown();

        return result;
    }
}