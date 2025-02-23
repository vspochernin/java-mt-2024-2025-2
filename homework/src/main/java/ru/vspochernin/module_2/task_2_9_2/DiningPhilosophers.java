package ru.vspochernin.module_2.task_2_9_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class DiningPhilosophers {

    private final List<Philosopher> philosophers;
    private final List<Lock> forks = new ArrayList<>();
    private final int meals;

    public DiningPhilosophers(int philosophers, int meals) {
        this.philosophers = new ArrayList<>(philosophers);
        for (int i = 0; i < philosophers; i++) {
            this.philosophers.add(new Philosopher(i, meals));
            this.forks.add(new ReentrantLock());
        }
        this.meals = meals;
    }

    public int[] execute(long t) {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < philosophers.size(); i++) {
            int finalI = i;
            threads.add(new Thread(() -> {
                Philosopher philosopher = philosophers.get(finalI);
                while (philosopher.mealsRest > 0) {
                    try {
                        think();
                        eat(philosopher, t);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            },
                    "Философ " + (i + 1)));
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        int[] result = new int[philosophers.size()];
        Arrays.fill(result, meals);
        return result;
    }

    private void think() throws InterruptedException {
        Thread.sleep((long) (Math.random() * 300));
    }

    private void eat(Philosopher philosopher, long t) throws InterruptedException {
        int leftFork = philosopher.id;
        int rightFork = (philosopher.id + 1) % forks.size();

        int minFork = Math.min(leftFork, rightFork);
        int maxFork = Math.max(leftFork, rightFork);

        try {
            forks.get(minFork).lock();
            try {
                forks.get(maxFork).lock();
                philosopher.mealsRest--;
                Thread.sleep(t);
            } finally {
                forks.get(maxFork).unlock();
            }
        } finally {
            forks.get(minFork).unlock();
        }
    }

    static class Philosopher {

        public final int id;
        public int mealsRest;

        Philosopher(int id, int mealsRest) {
            this.id = id;
            this.mealsRest = mealsRest;
        }
    }
}
