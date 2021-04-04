package bullscows;

import java.util.Random;

/**
 * The type Secret code.
 * @author kirillmisnik
 * @version 1.1
 */
public class SecretCode {

    /**
     * The constant random.
     */
    public static final Random random = new Random();

    private static final String[] possibleCharacters = generatePossibleCharacters();
    private final int codeLength;
    private final int possibleSymbols;

    /**
     * Instantiates a new Secret code.
     *
     * @param codeLength      the code length
     * @param possibleSymbols the possible symbols
     */
    public SecretCode(int codeLength, int possibleSymbols) {
        this.codeLength = codeLength;
        this.possibleSymbols = possibleSymbols;
    }

    private static String[] generatePossibleCharacters() {
        String[] possibleCharacters = new String[36];

        for (int i = 0; i < 36; i++) {
            if (i < 10) {
                possibleCharacters[i] = String.valueOf(i);
            } else {
                possibleCharacters[i] = String.valueOf((char) (i + 87));
            }
        }
        return possibleCharacters;
    }

    /**
     * Generate code string.
     *
     * @return the string
     */
    public String generateCode() {
        StringBuilder codeSB = new StringBuilder();

        for (int i = 0; i < this.codeLength; i++) {
            int number = random.nextInt(this.possibleSymbols);
            String character = possibleCharacters[number];

            if (codeSB.toString().contains(character)) {
                number = 0;
                while (codeSB.toString().contains(character)) {
                    number++;
                    character = possibleCharacters[number];
                }
            }
            codeSB.append(possibleCharacters[number]);
        }
        return codeSB.toString();
    }

    /**
     * Output code.
     */
    public void outputCode() {
        StringBuilder codeHidden = new StringBuilder();

        codeHidden.append("*".repeat(Math.max(0, this.codeLength)));

        if (this.possibleSymbols > 11) {
            System.out.printf("The secret is prepared: %s (0-9, a-%s).\n",
                    codeHidden, possibleCharacters[this.possibleSymbols - 1]);
        } else {
            System.out.printf("The secret is prepared: %s (0-%s).\n",
                    codeHidden, possibleCharacters[this.possibleSymbols - 1]);
        }
    }

}