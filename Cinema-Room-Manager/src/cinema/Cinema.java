package cinema;

import java.util.Scanner;

public class Cinema {

    final public static Scanner scanner = new Scanner(System.in);
    public char[][] theatre;
    public int numOfRows;
    public int numOfSeatsInRow;

    int purchasedTickets = 0;
    int currentIncome = 0;

    public static void main(String[] args) {
        Cinema cinemaTheatre = new Cinema();
        cinemaTheatre.StartWorking();
    }

    public void StartWorking() {
        MakeRoom();

        boolean exit = false;

        while (!exit) {
            System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
            String action = scanner.next();

            switch (action) {
                case "0":
                    exit = true;
                    break;
                case "1":
                    DisplaySeats();
                    break;
                case "2":
                    BuyTicket();
                    break;
                case "3":
                    CinemaStatistics();
                    break;
                default:
                    System.out.println("\nWrong input!\n");
                    break;
            }
        }
    }

    public void BuyTicket() {
        System.out.println("Enter a row number:");
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seat = scanner.nextInt();

        int price;

        if (this.numOfRows * this.numOfSeatsInRow <= 60) {
            price = 10;
        } else {
            price = row > this.numOfRows / 2 ? 8 : 10;
        }

        OccupySeat(row, seat);

        System.out.printf("Ticket price: $%d\n\n", price);

        this.purchasedTickets++;
        this.currentIncome += price;
    }

    public void MakeRoom() {
        System.out.println("Enter the number of rows:");
        this.numOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        this.numOfSeatsInRow = scanner.nextInt();

        this.theatre = new char[numOfRows][numOfSeatsInRow];

        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfSeatsInRow; j++) {
                this.theatre[i][j] = 'S';
            }
        }
    }

    public void OccupySeat(int row, int seat) {
        if (row <= this.numOfRows && row > 0 && seat <= this.numOfSeatsInRow && seat > 0) {
            if (this.theatre[row - 1][seat - 1] == 'S') {
                this.theatre[row - 1][seat - 1] = 'B';
            } else {
                System.out.println("\nThat ticket has already been purchased!\n");
                BuyTicket();
            }
        } else {
            System.out.println("\nWrong input!\n");
            BuyTicket();
        }
    }

    public void DisplaySeats() {
        System.out.println("Cinema:");

        System.out.print(" ");
        for (int i = 1; i <= this.numOfSeatsInRow; i++) {
            System.out.print(" " + i);
        }
        System.out.println();

        for (int i = 0; i < this.numOfRows; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < this.numOfSeatsInRow; j++) {
                System.out.print(this.theatre[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public void CinemaStatistics() {

        int profit;

        if (this.numOfRows * this.numOfSeatsInRow <= 60) {
            profit = this.numOfRows * this.numOfSeatsInRow * 10;
        } else {
            profit = (this.numOfRows / 2 * this.numOfSeatsInRow) * 10
                    + (this.numOfRows % 2 == 0 ? this.numOfRows
                    * this.numOfSeatsInRow / 2 : (this.numOfRows / 2 + 1) * this.numOfSeatsInRow) * 8;
        }

        int totalTickets = this.numOfRows * this.numOfSeatsInRow;
        double percentage = (double) this.purchasedTickets / totalTickets;

        System.out.printf("\nNumber of purchased tickets: %d\n", this.purchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", percentage * 100);
        System.out.printf("Current income: $%d\n", this.currentIncome);
        System.out.printf("Total income: $%d\n\n", profit);
    }
}