package src.gui;

import main.TicketManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiUserTheaterGUI {
    private final TicketManager ticketManager;

    public MultiUserTheaterGUI(int rows, int cols, int maxConcurrentUsers, int userCount) {
        this.ticketManager = new TicketManager(rows, cols, maxConcurrentUsers);

        // Create a JFrame for each user
        for (int user = 1; user <= userCount; user++) {
            createUserInterface(user);
        }
    }

    private void createUserInterface(int userNumber) {
        JFrame frame = new JFrame("Theater Ticket Management - User " + userNumber);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel seatPanel = new JPanel(new GridLayout(ticketManager.getRows(), ticketManager.getCols(), 5, 5));

        for (int row = 0; row < ticketManager.getRows(); row++) {
            for (int col = 0; col < ticketManager.getCols(); col++) {
                JButton seatButton = new JButton("Seat " + (row * ticketManager.getCols() + col + 1));
                seatButton.setBackground(Color.GREEN);
                seatButton.addActionListener(new SeatButtonListener(row, col, seatButton));
                seatPanel.add(seatButton);
            }
        }

        frame.add(seatPanel, BorderLayout.CENTER);

        JLabel infoLabel = new JLabel("Click a seat to reserve it.", JLabel.CENTER);
        frame.add(infoLabel, BorderLayout.NORTH);

        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private class SeatButtonListener implements ActionListener {
        private final int row;
        private final int col;
        private final JButton button;

        public SeatButtonListener(int row, int col, JButton button) {
            this.row = row;
            this.col = col;
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            SwingUtilities.invokeLater(() -> {
                try {
                    if (ticketManager.reserveSeat(row, col)) {
                        button.setBackground(Color.RED);
                        button.setEnabled(false);
                        JOptionPane.showMessageDialog(null,
                                "Seat reserved successfully!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Seat is already reserved.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    JOptionPane.showMessageDialog(null,
                            "Error reserving seat.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Prompt for user input
                String input = JOptionPane.showInputDialog("Enter the number of users:");
                int userCount = Integer.parseInt(input);

                // Start the application
                new MultiUserTheaterGUI(5, 5, 2, userCount);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Invalid number. Please enter a valid integer.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
