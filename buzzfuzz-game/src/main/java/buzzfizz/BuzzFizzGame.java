package buzzfizz;

/**
 * @author phc
 */
public class BuzzFizzGame {

    private static final int DEFAULT_STEPS = 1;

    private static final String BUZZ_INSTEAD_STR = "Buzz";

    private static final String FIZZ_INSTEAD_STR = "Fizz";

    /**
     * the rule is determine what the number is Buzz or Fizz
     */
    private BuzzFizzRule rule;

    private int start;

    private int last;

    private int steps;

    public BuzzFizzGame(int start,int last) {
        this.start = start;
        this.last = last;
        this.steps = DEFAULT_STEPS;
    }

    public BuzzFizzGame(int start,int last,int steps) {
        this.start = start;
        this.last = last;
        this.steps = steps;
    }

    public void setRule(BuzzFizzRule rule) {
        this.rule = rule;
    }

    public void start(){
        String lineSeparator = System.getProperty("line.separator");
        for (int insNumber = this.start;insNumber<=this.last;insNumber+=this.steps){
            String output = String.valueOf(insNumber);
            boolean isBuzz = this.rule.isBuzz(insNumber);
            boolean isFizz = this.rule.isFizz(insNumber);
            if (isBuzz||isFizz) {
                output = (isFizz ? FIZZ_INSTEAD_STR:"") + (isBuzz ? BUZZ_INSTEAD_STR:"");
            }
            System.out.println(output+lineSeparator);
        }
    }
}
