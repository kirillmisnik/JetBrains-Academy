package encryptdecrypt;

public class Algorithm {

    private final AlgorithmType strategy;

    public Algorithm(AlgorithmType strategy) {
        this.strategy = strategy;
    }

    public String encryptData(String data, int key) {
        return this.strategy.encrypt(data, key);
    }

    public String decryptData(String data, int key) {
        return this.strategy.decrypt(data, key);
    }
}