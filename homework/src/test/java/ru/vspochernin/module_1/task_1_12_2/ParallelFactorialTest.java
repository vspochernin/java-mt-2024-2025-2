package ru.vspochernin.module_1.task_1_12_2;

import java.math.BigInteger;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author pochernin-vla
 */
public class ParallelFactorialTest {

    @Test
    public void test1() throws InterruptedException {
        BigInteger actual = ParallelFactorial.execute(2, 5);
        BigInteger expected = new BigInteger("120");
        assertEquals(expected, actual);
    }

    @Test
    public void test2() throws InterruptedException {
        BigInteger actual = ParallelFactorial.execute(3, 10);
        BigInteger expected = new BigInteger("3628800");
        assertEquals(expected, actual);
    }

    @Test
    public void test3() throws InterruptedException {
        BigInteger actual = ParallelFactorial.execute(4, 20);
        BigInteger expected = new BigInteger("2432902008176640000");
        assertEquals(expected, actual);
    }

    @Test
    public void test4() throws InterruptedException {
        BigInteger actual = ParallelFactorial.execute(1, 20);
        BigInteger expected = new BigInteger("2432902008176640000");
        assertEquals(expected, actual);
    }

    @Test
    public void test5() throws InterruptedException {
        BigInteger actual = ParallelFactorial.execute(5, 1);
        BigInteger expected = new BigInteger("1");
        assertEquals(expected, actual);
    }
}