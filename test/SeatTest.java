package test;

import main.Seat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SeatTest {

    private Seat seat;

    @BeforeEach
    void setUp() {
        // Setup before each test case
        seat = new Seat(1, 1);  // Creating a seat with row 1 and column 1
    }

    @Test
    void testSeatInitialization() {
        // Test initial status of the seat
        assertFalse(seat.isReserved(), "Seat should not be reserved initially.");
    }

    @Test
    void testReserveSeat() {
        // Test reserving the seat
        seat.reserve();
        assertTrue(seat.isReserved(), "Seat should be reserved after calling reserve.");
    }

    @Test
    void testCancelReservation() {
        // Test canceling the reservation
        seat.reserve();  // First, reserve the seat
        seat.cancel();
        assertFalse(seat.isReserved(), "Seat should not be reserved after calling cancel.");
    }
}
