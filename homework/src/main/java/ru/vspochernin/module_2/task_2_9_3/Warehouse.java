package ru.vspochernin.module_2.task_2_9_3;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Warehouse {

    private final Map<String, Integer> items;
    private final Lock lock;

    public Warehouse() {
        items = Collections.synchronizedMap(new HashMap<>());
        lock = new ReentrantLock();
    }

    public boolean addItem(String itemType, int count) {
        items.merge(itemType, count, Integer::sum);
        return true;
    }

    public boolean removeItem(String itemType, int count) {
        try {
            lock.lock();
            int cur = items.getOrDefault(itemType, 0);
            if (cur < count) {
                return false;
            }
            items.put(itemType, cur - count);
            return true;
        } finally {
            lock.unlock();
        }
    }

    public int getItemCount(String itemType) {
        return items.getOrDefault(itemType, 0);
    }

    public boolean transfer(Warehouse other, String itemType, int count) {
        try {
            lock.lock();
            int cur = items.getOrDefault(itemType, 0);
            if (cur < count) {
                return false;
            }
            items.put(itemType, cur - count);
            other.addItem(itemType, count);
            return true;
        } finally {
            lock.unlock();
        }
    }
}
