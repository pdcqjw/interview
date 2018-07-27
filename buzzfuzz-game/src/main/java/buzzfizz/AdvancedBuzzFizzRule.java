package buzzfizz;

/**
 * The original buzzfizz-rule
 * rules:
 *  A number is fizz if it is divisible by 3 or if it has a 3 in it
 *  A number is buzz if it is divisible by 5 or if it has a 5 in it
 * @author phc
 */
public class AdvancedBuzzFizzRule implements BuzzFizzRule {

    private static final String FIZZ_NUMBER_STR = "3";
    private static final String BUZZ_NUMBER_STR = "5";

    @Override
    public boolean isFizz(int number) {
        return number%3 == 0 || contains(number, FIZZ_NUMBER_STR);
    }

    @Override
    public boolean isBuzz(int number) {
        return number%5 == 0 || contains(number,BUZZ_NUMBER_STR);
    }

    private boolean contains(int number, String str) {
        String numberStr = Integer.toString(number);
        return  numberStr.contains(str);
    }
}
