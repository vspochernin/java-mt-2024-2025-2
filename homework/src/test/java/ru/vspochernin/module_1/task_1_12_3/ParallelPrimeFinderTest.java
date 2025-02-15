package ru.vspochernin.module_1.task_1_12_3;

import java.util.List;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author pochernin-vla
 */
public class ParallelPrimeFinderTest {

    @Test
    public void test1() throws InterruptedException {
        List<Long> actual = ParallelPrimeFinder.execute(2, 1, 10);
        List<Long> expected = List.of(2L, 3L, 5L, 7L);
        assertEquals(expected, actual);
    }

    @Test
    public void test2() throws InterruptedException {
        List<Long> actual = ParallelPrimeFinder.execute(3, 10, 20);
        List<Long> expected = List.of(11L, 13L, 17L, 19L);
        assertEquals(expected, actual);
    }

    @Test
    public void test3() throws InterruptedException {
        List<Long> actual = ParallelPrimeFinder.execute(4, 14, 16);
        List<Long> expected = List.of();
        assertEquals(expected, actual);
    }
}