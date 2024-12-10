package test;

import main.TicketManager;

public class TicketManagerTest {
    public static void main(String[] args) {
        // Initialize TicketManager with 5 rows, 5 columns, and a max of 2 concurrent users
        TicketManager ticketManager = new TicketManager(5, 5, 2);

        // Create multiple threads to simulate concurrent reservations
        Runnable reserveTask = () -> {
            try {
                for (int row = 0; row < ticketManager.getRows(); row++) {
                    for (int col = 0; col < ticketManager.getCols(); col++) {
                        if (ticketManager.reserveSeat(row, col)) {
                            Thread.sleep(100); // Simulate time taken for reservation
                        }
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        };

        // Start multiple threads
        Thread user1 = new Thread(reserveTask, "User1");
        Thread user2 = new Thread(reserveTask, "User2");
        Thread user3 = new Thread(reserveTask, "User3");

        user1.start();
        user2.start();
        user3.start();

        // Wait for threads to finish
        try {
            user1.join();
            user2.join();
            user3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Main thread interrupted: " + e.getMessage());
        }

        // Display final seat status
        ticketManager.displaySeatStatus();
    }
}