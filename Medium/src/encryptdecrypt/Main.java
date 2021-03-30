package encryptdecrypt;

import encryptdecrypt.algorithms.Shift;
import encryptdecrypt.algorithms.Unicode;

public class Main {
    public static void main(String[] args) {
        Parameters params = new Parameters(args);
        Algorithm algorithm = chooseAlgorithm(params.alg);
        String data = proceedCode(algorithm, params.mode, inData(params.data, params.in), params.key);
        outData(data, params.out);
    }

    public static Algorithm chooseAlgorithm(String alg) {
        switch (alg) {
            case "shift":
                return new Algorithm(new Shift());
            case "unicode":
                return new Algorithm(new Unicode());
            default:
                return null;
        }
    }

    public static String proceedCode(Algorithm algorithm, String mode, String data, int key) {
        switch (mode) {
            case "enc":
                return algorithm.encryptData(data, key);
            case "dec":
                return algorithm.decryptData(data, key);
            default:
                return null;
        }
    }

    public static void outData(String data, String out) {
        if (out.isEmpty()) {
            System.out.println(data);
        } else {
            FileProcessor fileProcessor = new FileProcessor(data);
            fileProcessor.writeData(out);
        }
    }

    public static String inData(String data, String in) {
        FileProcessor fileProcessor = new FileProcessor(data);
        return fileProcessor.readData(in);
    }
}

