package ru.vspochernin.module_1.task_1_12_1;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author pochernin-vla
 */
public class ParallelMaxFinderTest {

    @Test
    public void test1() throws InterruptedException {
        long actual = ParallelMaxFinder.execute(2, new long[]{1, 3, 5, 7, 9});
        long expected = 9;
        assertEquals(expected, actual);
    }

    @Test
    public void test2() throws InterruptedException {
        long actual = ParallelMaxFinder.execute(3, new long[]{-5, 0, 12, 3, 8, -2, 15, 7, 6, 4});
        long expected = 15;
        assertEquals(expected, actual);
    }

    @Test
    public void test3() throws InterruptedException {
        long actual = ParallelMaxFinder.execute(4, new long[]{100, 200, 300, 400, 500, 600, 700, 800});
        long expected = 800;
        assertEquals(expected, actual);
    }
}