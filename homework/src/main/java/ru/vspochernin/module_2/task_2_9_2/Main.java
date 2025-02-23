package ru.vspochernin.module_2.task_2_9_2;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        DiningPhilosophers diningPhilosophers = new DiningPhilosophers(5, 3);
        System.out.println(Arrays.toString(diningPhilosophers.execute(100)));
    }
}
