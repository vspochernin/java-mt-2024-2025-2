package ru.vspochernin.module_5.task_5_12_3;

public class Main {

    public static void main(String[] args) throws Exception {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6}
        };

        int[][] transposed = MatrixTransposer.transpose(matrix);

        for (int[] row : transposed) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
