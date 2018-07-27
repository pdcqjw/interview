import buzzfizz.AdvancedBuzzFizzRule;
import buzzfizz.BuzzFizzGame;
import buzzfizz.BuzzFizzRule;

public class Main {

    public static void main(String[] args) {
        /* 1.define some params
         * start - the game start number
         * last - the game last number
         */
        int start = 1;
        int last = 100;

        //2.create a game
        BuzzFizzGame buzzFizzGame = new BuzzFizzGame(start,last);

        //3.choose the game rule and set the game rule
        BuzzFizzRule rule = new AdvancedBuzzFizzRule();
        buzzFizzGame.setRule(rule);

        //4.start game
        buzzFizzGame.start();

    }
}
