package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        turnOn();
    }

    public static void turnOn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int numberOfRows = scanner.nextInt() + 1;
        System.out.println("Enter the number of seats in each row:");
        int numberOfSeats = scanner.nextInt() + 1;
        char[][] cinemaSeats = createChart(numberOfRows, numberOfSeats);
        System.out.println();
        chooseAction(cinemaSeats, numberOfRows, numberOfSeats);

    }

    public static char[][] createChart(int numOfRows, int numOfSeats) {
        char[][] cinemaSeats = new char[numOfRows][numOfSeats];
        for (int i = 0; i < cinemaSeats[0].length; i++) {
            cinemaSeats[0][i] = (char) i;
        }
        cinemaSeats[0][0] = ' ';

        for (int i = 1; i < cinemaSeats[0].length; i++) {
            cinemaSeats[0][i] = (char) (i + 48);
        }

        for (char i = 1; i < cinemaSeats.length; i++) {
            cinemaSeats[i][0] = (char) (i + 48);

            for (int y = 1; y < cinemaSeats[i].length; y++) {
                cinemaSeats[i][y] = 'S';
            }
        }
        return cinemaSeats;
    }

    private static void insertChart(char[][] cinemaSeats) {
        System.out.println("Cinema:");
        for (char[] cinemaSeat : cinemaSeats) {
            for (char c : cinemaSeat) {
                System.out.print(c + " ");

            }
            System.out.println();
        }
    }

    public static void chooseAction(char[][] chart, int numOfRows, int numOfSeats) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Show the seats \n2. Buy a ticket \n3. Statistics \n0. Exit");
        int actionNumber = scanner.nextInt();
        System.out.println();
        switch (actionNumber) {
            case 1:
                insertChart(chart);
                System.out.println();
                chooseAction(chart, numOfRows, numOfSeats);
                break;
            case 2:
                buyTicket(chart, numOfRows, numOfSeats);
                System.out.println();
                chooseAction(chart, numOfRows, numOfSeats);
                break;
            case 3:
                statistics(chart, numOfRows, numOfSeats);
                System.out.println();
                chooseAction(chart, numOfRows, numOfSeats);
                break;
            case 0:

        }
    }

    public static void statistics(char[][] chart, int numOfRows, int numOfSeats) {
        numOfRows--;
        numOfSeats--;
        int numberOfTickets = 0;
        for (char[] chars : chart) {
            for (char aChar : chars) {
                if (aChar == 'B') {
                    numberOfTickets++;
                }
            }
        }
        System.out.println("Number of purchased tickets: " + numberOfTickets);
        double totalSeats = numOfRows * numOfSeats;
        double percentage = (numberOfTickets * 100) / totalSeats;
        String str = String.format("Percentage: %.2f", percentage);
        System.out.println(str + '%');
        int firstRowsTickets = 0;
        for (int i = 0; i <= 4; i++) {
            for (int y = 0; y < chart[i].length; y++) {
                if (chart[i][y] == 'B') {
                    firstRowsTickets++;
                }
            }
        }
        System.out.println(firstRowsTickets);
        int backRowTickets = numberOfTickets - firstRowsTickets;
        System.out.println(backRowTickets);
        int currentIncome;
        if (totalSeats <= 60) {
            currentIncome = numberOfTickets * 10;
        } else {
            currentIncome = (firstRowsTickets * 10) + (backRowTickets * 8);
        }
        System.out.println("Current income: $" + currentIncome);
        int totalIncome;
        if (totalSeats <= 60) {
            totalIncome = (int) (totalSeats * 10);
        } else {
            int firstRowSeats = 4 * numOfSeats;
            int firstRowIncome = firstRowSeats * 10;
            int backRowSeats = (int) (totalSeats - firstRowSeats);
            int backRowIncome = backRowSeats * 8;
            totalIncome = firstRowIncome + backRowIncome;
        }
        System.out.println("Total income: $" + totalIncome);

    }

    public static void buyTicket(char[][] seats, int numOfRows, int numOfSeats) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int rowNumber = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNumber = scanner.nextInt();
        while (rowNumber > numOfRows || seatNumber > numOfSeats || rowNumber < 0 || seatNumber < 0 || rowNumber == numOfRows++ || seatNumber == numOfSeats++) {
            System.out.println();
            System.out.println("Wrong input!");
            System.out.println();
            System.out.println("Enter a row number:");
            rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNumber = scanner.nextInt();
        }
        int ticketPrice;
        System.out.println();
        if (seats[rowNumber][seatNumber] == 'B') {
            System.out.println("That ticket has already been purchased!");
            buyTicket(seats, numOfRows, numOfSeats);
        } else if (numOfSeats * numOfRows <= 60) {
            ticketPrice = 10;
            System.out.println("Ticket price: $" + ticketPrice);
        } else if (rowNumber > 4) {
            ticketPrice = 8;
            System.out.println("Ticket price: $" + ticketPrice);
        } else {
            ticketPrice = 10;
            System.out.println("Ticket price: $" + ticketPrice);
        }

        System.out.println();
        seats[rowNumber][seatNumber] = 'B';

    }

}

