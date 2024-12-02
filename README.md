# Ticket Management System for a Theater 🎭

## Project Overview
This project is a **multithreaded Java application** designed to manage ticket reservations and cancellations for a theater. The system ensures synchronization for concurrent access, preventing conflicts during booking or cancellation operations. It also features an intuitive **JavaFX graphical user interface** for users to interact with.

---

## Features
1. **Ticket Booking**:
   - Multiple users (threads) can book tickets simultaneously.
   - Synchronization ensures consistency in ticket availability.

2. **Ticket Cancellation**:
   - Users can cancel their reserved tickets.
   - Canceled tickets become available for other users to book.

3. **Real-Time Seat Status Display**:
   - A graphical grid shows available and reserved seats in real-time.

4. **Thread Synchronization**:
   - Implemented using **semaphores**, **monitors**, and **locks** to manage concurrent access.

---

## Technology Stack
- **Java**: Core programming language.
- **JavaFX**: For building the graphical user interface.
- **Threading and Synchronization**:
  - **Semaphores**: To manage ticket availability.
  - **Monitors**: To ensure safe concurrent access.

---

## How It Works
1. **Concurrency Management**:
   - Each user is represented by a thread attempting to book or cancel tickets.
   - Synchronization mechanisms ensure no two threads manipulate the same ticket data simultaneously.

2. **Graphical Interface**:
   - Seats are displayed as a grid in the GUI.
   - Reserved seats are highlighted, while available seats remain unmarked.

---

## Getting Started

### Prerequisites
- **Java Development Kit (JDK)**: Version 8 or later.
- **JavaFX Library**: Ensure it's set up in your environment.

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/Jlassi-Mohamed/Ticket-Management-System-for-a-Theater.git
