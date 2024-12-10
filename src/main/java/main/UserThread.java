package main;

public class UserThread extends Thread {
    private final TicketManager ticketManager;
    private final int action;
    private final int row;
    private final int col;

    public UserThread(TicketManager ticketManager, int action, int row, int col) {
        this.ticketManager = ticketManager;
        this.action = action;
        this.row = row;
        this.col = col;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((long) (Math.random() * 1000));

            if (action == 1) {
                boolean success = ticketManager.reserveSeat(row, col);
                if (success) {
                    System.out.println(Thread.currentThread().getName() + " successfully reserved seat [" + row + "," + col + "]");
                } else {
                    System.out.println(Thread.currentThread().getName() + " failed to reserve seat [" + row + "," + col + "]");
                }
            } else if (action == 2) {
                ticketManager.cancelSeat(row, col);
                System.out.println(Thread.currentThread().getName() + " canceled the reservation for seat [" + row + "," + col + "]");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
