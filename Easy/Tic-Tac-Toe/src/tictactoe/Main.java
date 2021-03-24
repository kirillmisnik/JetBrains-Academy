package tictactoe;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String str = "_________";

        char[][] board = new char[3][3];

        int charPosition = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char currentSymbol = str.charAt(charPosition);
                board[i][j] = currentSymbol;
                charPosition++;
            }
        }

        printGrid(board);

        int turn = 1;

        while (gameCondition(board)) {
            if (turn > 9) {
                System.out.println("Draw");
                break;
            }
            System.out.println("Enter the coordinates:");
            String inputX = scanner.next();
            String inputY = scanner.next();
            turn = makeMove(board, turn, inputX, inputY);
            switch (winCondition(board)) {
                case "X":
                    System.out.println("X wins");
                    break;
                case "O":
                    System.out.println("O wins");
                    break;
            }
        }

    }

    public static void printGrid(char[][] board) {
        System.out.println("+-------+");
        for (char[] subBoard : board) {
            System.out.printf("| %c %c %c |\n", subBoard[0], subBoard[1], subBoard[2]);
        }
        System.out.println("+-------+");
    }

    public static int makeMove(char[][] board, int turn, String inputX, String inputY) {
        while (true) {

            if (!inputX.matches("\\d") || !inputY.matches("\\d")) {
                System.out.println("You should enter numbers!");
                break;
            }

            int x = Integer.parseInt(inputX) - 1;
            int y = Integer.parseInt(inputY) - 1;

            if (x < 0 || x >= 3 || y < 0 || y >= 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                break;
            }

            if (board[x][y] == '_') {
                if (turn % 2 == 0) {
                    board[x][y] = 'O';
                } else {
                    board[x][y] = 'X';
                }
                printGrid(board);
                turn++;
                return turn;

            } else if (board[x][y] == 'X' || board[x][y] == 'O'){
                System.out.println("This cell is occupied! Choose another one!");
                break;
            }

        }
        return turn;
    }

    public static boolean gameCondition(char[][] board) {
        boolean gameNotFinished = true;
        if (!winCondition(board).equals("Nobody")) {
            gameNotFinished = false;
        }

        return gameNotFinished;
    }

    public static String winCondition(char[][] board) {

        String winner = "Nobody";

        /* Победа по вертикали */

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 3; j++) {

                // Победа X
                if (board[i][j] == 'X' && board[i + 1][j] == 'X' && board[i + 2][j] == 'X') {
                    winner = "X";
                    break;
                }

                // Победа O
                if (board[i][j] == 'O' && board[i + 1][j] == 'O' && board[i + 2][j] == 'O') {
                    winner = "O";
                    break;
                }
            }
        }

        /* Победа по горизонтали */

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1; j++) {

                // Победа X
                if (board[i][j] == 'X' && board[i][j + 1] == 'X' && board[i][j + 2] == 'X') {
                    winner = "X";
                    break;
                }

                // Победа O
                if (board[i][j] == 'O' && board[i][j + 1] == 'O' && board[i][j + 2] == 'O') {
                    winner = "O";
                    break;
                }
            }
        }

        /* Победа по диагонали */

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 1; j++) {

                // Победа X
                if (board[i][j] == 'X' && board[i + 1][j + 1] == 'X' && board[i + 2][j + 2] == 'X' ||
                        board[i][j + 2] == 'X' && board[i + 1][j + 1] == 'X' && board[i + 2][j] == 'X') {
                    winner = "X";
                    break;
                }

                // Победа O
                if (board[i][j] == 'O' && board[i + 1][j + 1] == 'O' && board[i + 2][j + 2] == 'O' ||
                        board[i][j + 2] == 'O' && board[i + 1][j + 1] == 'O' && board[i + 2][j] == 'O') {
                    winner = "O";
                    break;
                }
            }
        }

        return winner;
    }
}
