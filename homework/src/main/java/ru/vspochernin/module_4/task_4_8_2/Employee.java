package ru.vspochernin.module_4.task_4_8_2;

import java.util.List;
import java.util.Map;

class Employee {
    private String name;
    private String department;
    private double salary;
    private int age;

    public Employee(String name, String department, double salary, int age) {
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.age = age;
    }

    public String getName() {
        return name;
    }


    public String getDepartment() {
        return department;
    }


    public double getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }
}


class StreamApiTask {
    public static Map<String, List<Employee>> foo(List<Employee> employee) {
        // Ваше решение
        return null;
    }
}