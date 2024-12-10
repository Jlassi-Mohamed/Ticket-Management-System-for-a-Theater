package src.gui;

import javax.swing.*;
import java.awt.*;
import main.TicketManager;
import src.gui.components.SeatGrid;

public class TheaterGUI {
    private TicketManager ticketManager;
    private SeatGrid seatGrid;

    public TheaterGUI() {
        ticketManager = new TicketManager(5, 5, 10);

        seatGrid = new SeatGrid(ticketManager);

        JFrame frame = new JFrame("Theater Ticket Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Header
        JLabel header = new JLabel("Welcome to Theater Ticket Management", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        frame.add(header, BorderLayout.NORTH);

        // Main content with padding
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.add(seatGrid, BorderLayout.CENTER);
        frame.add(centerPanel, BorderLayout.CENTER);

        // Footer with buttons
        JPanel footer = new JPanel();
        JButton confirmButton = new JButton("Confirm Booking");
        JButton resetButton = new JButton("Reset");
        footer.add(confirmButton);
        footer.add(resetButton);
        frame.add(footer, BorderLayout.SOUTH);

        // Frame settings
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TheaterGUI());
    }
}
