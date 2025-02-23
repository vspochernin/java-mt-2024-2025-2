package ru.vspochernin.module_2.task_2_9_1;

public class Main {

    public static void main(String[] args) {
        System.out.println("---------");
        BankAccount bankAccount1 = new BankAccount(1000);
        bankAccount1.execute(2, 2);
        System.out.println("---------");
        BankAccount bankAccount2 = new BankAccount(500);
        bankAccount2.execute(3, 2);
        System.out.println("---------");
        BankAccount bankAccount3 = new BankAccount(0);
        bankAccount3.execute(1, 1);
    }
}
