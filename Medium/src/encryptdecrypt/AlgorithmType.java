package encryptdecrypt;

public interface AlgorithmType {

    /**
     * Returns encrypted data
     */
    String encrypt(String data, int key);

    /**
     * Returns decrypted data
     */
    String decrypt(String data, int key);

}
