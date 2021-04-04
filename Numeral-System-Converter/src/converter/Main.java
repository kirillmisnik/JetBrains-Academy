package converter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            int sourceBase = scanner.nextInt();
            String number = scanner.next();
            int newBase = scanner.nextInt();
            if (sourceBase <= 0 || newBase <= 0 || sourceBase > 36 || newBase > 36) {
                System.out.println("Error!");
                System.exit(0);
            }
            convert(number, newBase, sourceBase);
        } catch (Exception e) {
            System.out.println("Error!");
            System.exit(0);
        }
    }

    public static void convert(String number, int newBase, int sourceBase) {
        if (number.contains(".")) {
            String[] numberArray = number.split("\\.");
            String integerPartDecimal = integerConvert(numberArray[0], 10, sourceBase);
            String fractionalPartDecimal = fractionalConvert(numberArray[1], 10, sourceBase);

            if (newBase == 10) {
                System.out.printf("%s.%.5s", integerPartDecimal, fractionalPartDecimal);
            } else {
                String integerPart = integerConvert(numberArray[0], newBase, sourceBase);
                String fractionalPart = fractionalConvert(String.valueOf(fractionalPartDecimal), newBase, sourceBase);

                System.out.printf("%s.%.5s", integerPart, fractionalPart);
            }
        } else {
            System.out.println(integerConvert(number, newBase, sourceBase));
        }
    }

    public static String integerConvert(String number, int newBase, int sourceBase) {
        if (newBase == 1) {
            return "1".repeat(Math.max(0, Integer.parseInt(number)));
        } else if (sourceBase == 1) {
            return Long.toString(number.length(), newBase);
        } else {
            return Long.toString(Integer.parseInt(number, sourceBase), newBase);
        }
    }

    public static String fractionalConvert(String number, int newBase, int sourceBase) {
        double fractionalPartDecimal = 0;
        if (newBase == 10) {
            String[] numberArray = number.split("");
            for (int i = 1; i <= number.length(); i++) {
                fractionalPartDecimal += Integer.parseInt(numberArray[i - 1], sourceBase) / Math.pow(sourceBase, i);
            }
            return String.valueOf(fractionalPartDecimal);
        } else {
            double value = Double.parseDouble(number);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                stringBuilder.append(Long.toString((int) (value * newBase), newBase));
                value = (value * newBase) - (int) (value * newBase);
            }
            return stringBuilder.toString();
        }
    }
}