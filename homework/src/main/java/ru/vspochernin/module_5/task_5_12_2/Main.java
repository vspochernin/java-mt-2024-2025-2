package ru.vspochernin.module_5.task_5_12_2;

public class Main {
    public static void main(String[] args) throws Exception {
        int[][] A = {
                {1, 2},
                {3, 4}
        };
        int[][] B = {
                {2, 0},
                {1, 2}
        };

        int[][] C = MatrixMultiplier.multiply(A, B);

        for (int[] row : C) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
