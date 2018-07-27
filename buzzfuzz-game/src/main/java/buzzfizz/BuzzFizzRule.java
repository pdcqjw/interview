package buzzfizz;


/**
 * The BuzzFizzRule determine the number is Buzz or Fizz
 * @author phc
 */
public interface BuzzFizzRule {

    /**
     * @param number the checked number
     * @return true if the number is Fizz
     */
    boolean isFizz(int number);

    /**
     * @param number the checked number
     * @return true if the number is Buzz
     */
    boolean isBuzz(int number);

}