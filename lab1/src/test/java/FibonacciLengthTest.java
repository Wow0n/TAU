import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tau.Fibonacci;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class FibonacciLengthTest {
    private Fibonacci fibonacci;

    @Before
    public void setUp() {
        fibonacci = new Fibonacci();
    }

    @After
    public void tearDown() {
        fibonacci = null;
    }

    @Test
    public void returnsListOfFiveElementsForRangeFive() {
        ArrayList<Integer> fibArray = fibonacci.fibonacciInLength(5);
        ArrayList<Integer> result = new ArrayList<>(Arrays.asList(0, 1, 1, 2, 3));

        assertEquals(5, fibArray.size());
        assertArrayEquals(result.toArray(), fibArray.toArray());
    }

    @Test
    public void returnsEmptyListForRangeZero() {
        ArrayList<Integer> fibArray = fibonacci.fibonacciInLength(0);
        ArrayList<Integer> result = new ArrayList<>();

        assertEquals(0, fibArray.size());
        assertArrayEquals(result.toArray(), fibArray.toArray());
    }

    @Test
    public void returnsEmptyListForNegativeRange() {
        ArrayList<Integer> fibArray = fibonacci.fibonacciInLength(-5);
        ArrayList<Integer> result = new ArrayList<>();

        assertEquals(0, fibArray.size());
        assertArrayEquals(result.toArray(), fibArray.toArray());
    }

    @Test
    public void returnsEmptyListForFractionRange() {
        ArrayList<Integer> fibArray = fibonacci.fibonacciInLength(0.5);
        ArrayList<Integer> result = new ArrayList<>();

        assertEquals(0, fibArray.size());
        assertArrayEquals(result.toArray(), fibArray.toArray());
    }
}