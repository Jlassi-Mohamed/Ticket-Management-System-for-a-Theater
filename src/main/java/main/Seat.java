package main;

public class Seat {
    private final int row;    // Row index of the seat
    private final int col;    // Column index of the seat
    private boolean reserved; // Reservation status

    // Constructor to initialize the seat with its row and column
    public Seat(int row, int col) {
        this.row = row;
        this.col = col;
        this.reserved = false; // Initially, the seat is not reserved
    }

    // Check if the seat is reserved
    public boolean isReserved() {
        return reserved;
    }

    // Reserve the seat
    public void reserve() {
        this.reserved = true;
    }

    // Cancel the reservation for the seat
    public void cancel() {
        this.reserved = false;
    }

    // Get the row of the seat
    public int getRow() {
        return row;
    }

    // Get the column of the seat
    public int getCol() {
        return col;
    }

    // Optional: Override toString for better seat representation
    @Override
    public String toString() {
        return "Seat{" + "row=" + row + ", col=" + col + ", reserved=" + reserved + '}';
    }
}
