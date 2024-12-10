package src.gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.TicketManager;

public class SeatGrid extends JPanel {
    private TicketManager ticketManager;

    public SeatGrid(TicketManager ticketManager) {
        this.ticketManager = ticketManager;
        setupSeats();
    }

    private void setupSeats() {
        // Set the layout to a grid of 5x5
        setLayout(new GridLayout(5, 5, 5, 5)); // 5x5 grid with 5px gap between buttons

        // Create and add buttons to the grid
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                JButton seatButton = new JButton();
                seatButton.setPreferredSize(new Dimension(100, 100));
                updateButtonStyle(seatButton, row, col);

                int finalRow = row;
                int finalCol = col;
                seatButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            handleSeatClick(finalRow, finalCol, seatButton);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });

                add(seatButton);
            }
        }
    }

    private void handleSeatClick(int row, int col, JButton seatButton) throws InterruptedException {
        if (ticketManager.isSeatReserved(row, col)) {
            ticketManager.cancelSeat(row, col);
            updateButtonStyle(seatButton, row, col);
        } else {
            boolean success = ticketManager.reserveSeat(row, col);
            if (success) {
                updateButtonStyle(seatButton, row, col);
            }
        }
    }

    private void updateButtonStyle(JButton seatButton, int row, int col) {
        // Update button style based on seat reservation status
        if (ticketManager.isSeatReserved(row, col)) {
            seatButton.setBackground(Color.RED);
            seatButton.setOpaque(true);
            seatButton.setBorderPainted(false);
        } else {
            seatButton.setBackground(Color.GREEN);
            seatButton.setOpaque(true);
            seatButton.setBorderPainted(false);
        }
    }
}
