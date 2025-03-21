package ru.vspochernin.module_5.task_5_12_3;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.List;

class MatrixTransposer {

    public static int[][] transpose(int[][] a) {
        if (a.length == 0 || a[0].length == 0) {
            return a;
        }
        int rows = a.length;
        int cols = a[0].length;
        int[][] result = new int[cols][rows];

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < cols; i++) {
            final int colIndex = i;
            Future<?> future = executor.submit(() -> {
                for (int j = 0; j < rows; j++) {
                    result[colIndex][j] = a[j][colIndex];
                }
            });
            futures.add(future);
        }

        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        executor.shutdown();

        return result;
    }
}