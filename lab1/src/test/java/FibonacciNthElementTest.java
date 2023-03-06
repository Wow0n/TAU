import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tau.Fibonacci;

import static org.junit.Assert.assertEquals;

public class FibonacciNthElementTest {
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
    public void firstFibonacciNumberIsEqualOne() {
        assertEquals(1, fibonacci.nthElement(0));
    }

    @Test
    public void secondFibonacciNumberIsEqualOne() {
        assertEquals(1, fibonacci.nthElement(1));
    }

    @Test
    public void fifthFibonacciNumberIsEqualFive() {
        assertEquals(5, fibonacci.nthElement(5));
    }

    @Test
    public void hugeFibonacciNumberIsCalculatedProperly() {
        assertEquals(1819143227, fibonacci.nthElement(100000000));
    }

    @Test
    public void forNegativeReturnMinusOne() {
        assertEquals(-1, fibonacci.nthElement(-5));
    }

    @Test
    public void forFractionReturnMinusOne() {
        assertEquals(-1, fibonacci.nthElement(0.5));
    }
}