package encryptdecrypt.algorithms;

import encryptdecrypt.AlgorithmType;

public class Unicode implements AlgorithmType {

    @Override
    public String encrypt(String data, int key) {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            code.append((char) (data.charAt(i) + key));
        }
        return code.toString();
    }

    @Override
    public String decrypt(String data, int key) {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            code.append((char) (data.charAt(i) - key));
        }
        return code.toString();
    }
}
