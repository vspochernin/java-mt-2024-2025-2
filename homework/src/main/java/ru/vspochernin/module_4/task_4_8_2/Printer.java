package ru.vspochernin.module_4.task_4_8_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Printer {
    public static void main(String[] args) {
        testEmptyList();
        testFoo();
        testNoMatchingEmployees();
    }

    private static void testFoo() {
        System.out.println("Тестируем метод foo() с сотрудниками...");

        List employees = Arrays.asList(
                new Employee("John", "IT", 60000, 35),
                new Employee("Alice", "HR", 70000, 40),
                new Employee("Bob", "IT", 50000, 25),
                new Employee("Eve", "Finance", 80000, 45),
                new Employee("Charlie", "HR", 70000, 32),
                new Employee("Dave", "Finance", 55000, 38)
        );

        Map result = StreamApiTask.foo(employees);

        System.out.println("Результат:");
        printResult(result);
    }

    private static void testEmptyList() {
        System.out.println("Тестируем пустой список...");

        List employees = new ArrayList<>();
        Map result = StreamApiTask.foo(employees);

        System.out.println("Результат: " + result);
    }

    private static void testNoMatchingEmployees() {
        System.out.println("Тестируем список без подходящих сотрудников...");

        List employees = Arrays.asList(
                new Employee("Bob", "IT", 50000, 25),
                new Employee("Sam", "HR", 49000, 28)
        );

        Map result = StreamApiTask.foo(employees);

        System.out.println("Результат: " + result);
    }

    private static void printResult(Map result) {
        result.forEach((department, employees) -> {
            System.out.println("Отдел " + department + ": " + employees);
        });
    }
}