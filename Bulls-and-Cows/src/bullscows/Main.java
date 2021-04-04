package bullscows;

import java.util.Scanner;

/**
 * The type Main.
 * @author kirillmisnik
 * @version 1.1
 */
public class Main {

    /**
     * The constant scanner.
     */
    public static final Scanner scanner = new Scanner(System.in);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) { startGame(); }

    /**
     * Start game.
     */
    public static void startGame() {
        System.out.println("Please, enter the secret code's length:");
        String codeString = scanner.next();
        int codeLength = 0;

        try {
            codeLength = Integer.parseInt(codeString);
        } catch (Exception e) {
            System.out.printf("Error: %s isn't a valid number.", codeString);
            System.exit(0);
        }

        if (codeLength > 36 || codeLength <= 0) {
            System.out.println("Error: can't generate a secret number with a length of > 36" +
                    " because there aren't enough unique symbols.");
            System.exit(0);
        }

        System.out.println("Input the number of possible symbols in the code:");
        String possibleSymbolsString = scanner.next();
        int possibleSymbols = 0;

        try {
            possibleSymbols = Integer.parseInt(possibleSymbolsString);
        } catch (Exception e) {
            System.out.printf("Error: %s isn't a valid number.", possibleSymbolsString);
            System.exit(0);
        }

        if (possibleSymbols > 36 || possibleSymbols <= 0) {
            System.out.println("Error: can't generate a secret number with a length of > 36" +
                    " because there aren't enough unique symbols.");
            System.exit(0);
        }

        if (possibleSymbols < codeLength) {
            System.out.printf("Error: it's not possible to generate a code" +
                    "with a length of %d with %d unique symbols.", possibleSymbols, codeLength);
            System.exit(0);
        }

        System.out.println("Okay, let's start a game!");

        SecretCode secretCode = new SecretCode(codeLength, possibleSymbols);
        String code = secretCode.generateCode();
        secretCode.outputCode();

        boolean win = false;
        int turn = 1;

        while (!win) {
            System.out.printf("Turn %d:\n", turn++);
            String guess = scanner.next();
            win = codeGrader(code, guess);
        }
        System.out.println("Congratulations! You guessed the secret code.");
    }

    /**
     * Code grader boolean.
     *
     * @param code  the code
     * @param guess the guess
     * @return the boolean
     */
    public static boolean codeGrader(String code, String guess) {
        String[] codeChar = code.split("");
        String[] guessChar = guess.split("");

        int bulls = 0;
        int cows = 0;
        int bullsRN = 0;
        int cowsRN = 0;

        for (int i = 0; i < codeChar.length; i++) {
            for (int j = 0; j < guessChar.length; j++) {
                if (codeChar[i].equals(guessChar[j])) {
                    cowsRN = 1;
                    if (i == j) {
                        bullsRN = 1;
                    }
                }
            }

            bulls += bullsRN;
            cows += cowsRN;
            bullsRN = 0;
            cowsRN = 0;
        }
        cows -= bulls;

        displayState(bulls, cows);

        return bulls == code.length();
    }

    private static void displayState(int bulls, int cows) {
        if (cows != 0 && bulls != 0) {
            System.out.printf("Grade: %d bull(s) and %d cow(s).\n", cows, bulls);
        } else if (cows == 0 && bulls != 0) {
            System.out.printf("Grade: %d bull(s).\n", bulls);
        } else if (cows != 0) {
            System.out.printf("Grade: %d cow(s).\n", cows);
        } else {
            System.out.println("Grade: None.");
        }
    }

}
