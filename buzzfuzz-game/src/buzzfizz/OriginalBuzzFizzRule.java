package buzzfizz;

/**
 * The original buzzfizz-rule
 * rules:
 *  A number is fizz if it is divisible by 3
 *  A number is buzz if it is divisible by 5
 * @author phc
 */
public class OriginalBuzzFizzRule implements BuzzFizzRule {

    @Override
    public boolean isBuzz(int number) {
        return number%5 == 0;
    }

    @Override
    public boolean isFizz(int number) {
        return number%3 == 0;
    }
}
