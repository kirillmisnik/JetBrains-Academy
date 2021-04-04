package encryptdecrypt.algorithms;

import encryptdecrypt.AlgorithmType;

public class Shift implements AlgorithmType {

    @Override
    public String encrypt(String data, int key) {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            char letter = data.charAt(i);
            if (letter >= 'a' && letter <= 'z') {
                for (int j = 0; j < key; j++) {
                    if (letter >= 'z') {
                        letter = 'a';
                    } else {
                        letter++;
                    }
                }
            }
            if (letter >= 'A' && letter <= 'Z') {
                for (int j = 0; j < key; j++) {
                    if (letter >= 'Z') {
                        letter = 'A';
                    } else {
                        letter++;
                    }
                }
            }
            code.append(letter);
        }
        return code.toString();
    }

    @Override
    public String decrypt(String data, int key) {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            char letter = data.charAt(i);
            if (letter >= 'a' && letter <= 'z') {
                for (int j = 0; j < key; j++) {
                    if (letter <= 'a') {
                        letter = 'z';
                    } else {
                        letter--;
                    }
                }
            }
            if (letter >= 'A' && letter <= 'Z') {
                for (int j = 0; j < key; j++) {
                    if (letter <= 'A') {
                        letter = 'Z';
                    } else {
                        letter--;
                    }
                }
            }
            code.append(letter);
        }
        return code.toString();
    }
}
