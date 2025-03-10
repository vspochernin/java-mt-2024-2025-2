package ru.vspochernin.module_5.task_5_12_1;

public class Main {

    public static void main(String[] args) {
        AsyncSumCalculator.calculateSum(10)
                .subscribe(result -> System.out.println("Сумма чисел от 1 до " + 10 + " равна: " + result));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
