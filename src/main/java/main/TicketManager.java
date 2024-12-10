package main;

import java.util.concurrent.Semaphore;

public class TicketManager {
    private final Seat[][] seats;
    private final Semaphore semaphore;

    public TicketManager(int rows, int cols, int maxConcurrentUsers) {
        this.seats = new Seat[rows][cols];
        this.semaphore = new Semaphore(maxConcurrentUsers);
        initializeSeats(rows, cols);
    }

    private void initializeSeats(int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seats[i][j] = new Seat(i, j);
            }
        }
    }

    public boolean isSeatReserved(int row, int col) {
        synchronized (seats[row][col]) {
            return seats[row][col].isReserved();
        }
    }

    public boolean reserveSeat(int row, int col) throws InterruptedException {
        semaphore.acquire();
        try {
            synchronized (seats[row][col]) {
                if (!seats[row][col].isReserved()) {
                    seats[row][col].reserve();
                    System.out.println("Seat [" + row + "," + col + "] reserved successfully.");
                    return true;
                } else {
                    System.out.println("Seat [" + row + "," + col + "] is already reserved.");
                    return false;
                }
            }
        } finally {
            semaphore.release();
        }
    }

    public boolean cancelSeat(int row, int col) {
        synchronized (seats[row][col]) {
            if (seats[row][col].isReserved()) {
                seats[row][col].cancel();
                System.out.println("Seat [" + row + "," + col + "] reservation canceled.");
            } else {
                System.out.println("Seat [" + row + "," + col + "] is not reserved.");
            }
        }
        return false;
    }

    public void displaySeatStatus() {
        System.out.println("\nSeat Status:");
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j].isReserved() ? "[X] " : "[O] ");
            }
            System.out.println();
        }
    }

    public int getRows() {
        return seats.length;
    }

    public int getCols() {
        return seats[0].length;
    }
}
