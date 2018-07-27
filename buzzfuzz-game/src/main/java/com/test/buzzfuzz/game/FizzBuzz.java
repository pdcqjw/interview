package com.test.buzzfuzz.game;

/**
 * @author wangzengfeng
 * @date 2018/7/27 15:56
 */
public class FizzBuzz {

    private static final String FIZZ = "FIZZ";
    private static final String BUZZ = "BUZZ";
    private static final int NUMBER_THREE = 3;
    private static final int NUMBER_FIVE = 5;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 180;
    private static final int NUMBER_TEN = 10;

    private Printer printer;

    public FizzBuzz(Printer writer) {
        this.printer = writer;
    }

    public void printValue(int number) {
        printer.print(getValue(number));
    }

    private String getValue(int number) {
        numberIsOutOfRange(number);
        String response = "";
        if (isDivisibleAndEqual(number, NUMBER_THREE)) {
            response += FIZZ;
        }
        if (isDivisibleAndEqual(number, NUMBER_FIVE)) {
            response += BUZZ;
        }
        if (response.isEmpty()) {
            response = String.valueOf(number);
        }
        return response;
    }

    private void numberIsOutOfRange(int number) {
        if (number < MIN_NUMBER) {
            try {
                throw new Exception("Number must be greater than 0");
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (number > MAX_NUMBER) {
            try {
                throw new Exception("Number must be less than 181");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private boolean isDivisible(int number, int of) {
        return number % of == 0;
    }

    private boolean isDivisibleAndEqual(int number, int of) {
        boolean flag = false;
        if (number % of == 0) {
            flag = true;
        }
        if (getLength(number) == NUMBER_THREE) {
            flag = true;
        }
        if (getLength(number) == NUMBER_FIVE) {
            flag = true;
        }
        return flag;
    }

    private int getLength(int x) {
        if (x < NUMBER_TEN) {
            return 1;
        }
        return getLength(x / NUMBER_TEN) + 1;
    }
}