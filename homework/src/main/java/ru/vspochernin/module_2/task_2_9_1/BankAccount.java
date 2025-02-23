package ru.vspochernin.module_2.task_2_9_1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount {

    private volatile int balance;
    private final Lock lock;
    private final Condition insufficientFundsCondition;
    private final int d = 100;
    private final int w = 50;

    public BankAccount(int balance) {
        this.balance = balance;
        this.lock = new ReentrantLock(true);
        this.insufficientFundsCondition = lock.newCondition();
    }

    public int execute(int n, int m) {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            Thread withdrawThread = new Thread(() -> withdraw(w), "Withdraw-Thread-" + (i + 1));
            threads.add(withdrawThread);
            withdrawThread.start();
        }

        for (int i = 0; i < m; i++) {
            Thread depositThread = new Thread(() -> deposit(d), "Deposit-Thread-" + (i + 1));
            threads.add(depositThread);
            depositThread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

//        System.out.println("Финальный баланс: " + balance);
        return balance;
    }

    public void withdraw(int amount) {
        try {
            lock.lock();
            while (balance < amount) {
                insufficientFundsCondition.await();
            }
            balance -= amount;
//            System.out.println(Thread.currentThread().getName() + " снял " + amount + ".");
//            System.out.println("Текущий баланс: " + balance);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void deposit(int amount) {
        try {
            lock.lock();
            balance += amount;
//            System.out.println(Thread.currentThread().getName() + " внес " + amount + ".");
//            System.out.println("Текущий баланс: " + balance);
            insufficientFundsCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
