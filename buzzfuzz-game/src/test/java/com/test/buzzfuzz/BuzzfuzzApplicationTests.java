package com.test.buzzfuzz;

import com.test.buzzfuzz.game.FizzBuzz;
import com.test.buzzfuzz.game.Printer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuzzfuzzApplicationTests {
    private static final int NUMBER_NOT_DIVISIBLE_3_5 = 1;
    private static final int NUMBER_NOT_DIVISIBLE_3_5_2 = 2;
    private static final int NUMBER_DIVISIBLE_3 = 6;
    private static final int NUMBER_DIVISIBLE_5 = 10;
    private static final int NUMBER_DIVISIBLE_3_AND_5 = 15;
    private static final int NUMBER_ZERO = 0;
    private static final int NUMBER_MAX = 181;

    private FizzBuzz fizzBuzz;
    @Mock
    private Printer printer;

    @Before
    public void setUp() {
        fizzBuzz = new FizzBuzz(printer);
    }

    @Test
    public void printValueWhenNumberNoDIVISIBLE3_5() {
        fizzBuzz.printValue(NUMBER_NOT_DIVISIBLE_3_5);
        verifyPrintValue(String.valueOf(NUMBER_NOT_DIVISIBLE_3_5));
    }

    @Test
    public void printValueWhenNumberNoDIVISIBLE3_5_2() {
        fizzBuzz.printValue(NUMBER_NOT_DIVISIBLE_3_5_2);
        verifyPrintValue(String.valueOf(NUMBER_NOT_DIVISIBLE_3_5_2));
    }


    @Test
    public void printFizzWhenNumberIsDIVISIBLE3() {
        fizzBuzz.printValue(NUMBER_DIVISIBLE_3);
        verifyPrintValue("FIZZ");
    }

    @Test
    public void printBuzzWhenNumberIsDIVISIBLE5() {
        fizzBuzz.printValue(NUMBER_DIVISIBLE_5);
        verifyPrintValue("BUZZ");
    }

    @Test
    public void printFizzBuzzWhenNumberIsDIVISIBLE3And5() {
        fizzBuzz.printValue(NUMBER_DIVISIBLE_3_AND_5);
        verifyPrintValue("FIZZBUZZ");
    }

    private void verifyPrintValue(String value) {
        verify(printer).print(eq(value));
    }

    @Test
    public void contextLoads() {
    }
}
