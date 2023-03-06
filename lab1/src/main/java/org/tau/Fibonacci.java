package org.tau;

import java.util.ArrayList;

public class Fibonacci {

    public int nthElement(Number n) {
        if (n instanceof Integer && (int) n > -1) {
            int previousFib = 0;
            int currentFib = 1;

            for (int i = 0; i < (int) n - 1; ++i) {
                int newFib = previousFib + currentFib;
                previousFib = currentFib;
                currentFib = newFib;
            }

            return currentFib;
        } else {
            return -1;
        }
    }

    public ArrayList<Integer> fibonacciInLength(Number length) {
        ArrayList<Integer> fibArray = new ArrayList<>();

        if (length instanceof Integer) {
            int firstTerm = 0;
            int secondTerm = 1;

            for (int i = 1; i <= (int) length; ++i) {
                fibArray.add(firstTerm);
                int nextTerm = firstTerm + secondTerm;
                firstTerm = secondTerm;
                secondTerm = nextTerm;
            }

        }
        return fibArray;
    }
}