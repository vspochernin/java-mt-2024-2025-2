package ru.vspochernin.module_3.task_3_10_2;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main2 {

    private static final int[] BALANCES = {500, 200, 300, 400};
    private static final List<Transaction> TRANSACTIONS = List.of(
            new Transaction(0, 1, 100),
            new Transaction(2, 3, 50),
            new Transaction(1, 3, 100));
    private static final int N_THREADS = 4;

    static class Transaction {

        final int fromId;
        final int toId;
        final int amount;

        Transaction(int fromId, int toId, int amount) {
            this.fromId = fromId;
            this.toId = toId;
            this.amount = amount;
        }
    }

    public static void main(String[] args) {
        int[] balances = BALANCES;
        List<Transaction> transactions = TRANSACTIONS;

        ExecutorService executor = Executors.newFixedThreadPool(N_THREADS);
        Object mutex = new Object();

        for (Transaction tx : transactions) {
            executor.execute(() -> {
                // Тоже решил добавить имитацию работы.
                try {
                    Thread.sleep((int) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (mutex) {
                    if (balances[tx.fromId] >= tx.amount) {
                        balances[tx.fromId] -= tx.amount;
                        balances[tx.toId] += tx.amount;
                    } else {
                        System.out.println("Insufficient funds for the user " + tx.fromId);
                    }
                }
            });
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println("Не все задачи успели завершиться в течение 5 секунд");
            } else {
                System.out.println("Все задачи были выполнены успешно");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < balances.length; i++) {
            System.out.println("User " + i + " final balance: " + balances[i]);
        }
    }
}