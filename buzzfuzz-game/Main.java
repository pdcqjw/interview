package buzzfuzz-game;

public class Main {

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            if (i % 3 != 0 && i % 5 != 0) {
                System.out.println(i);
            } else {
                String out = "";
                if (i % 3 == 0) {
                    out += "Fizz";
                }

                if (i % 5 == 0) {
                    out += "Buzz";
                }
                System.out.println(out);
            }
        }
    }

}

