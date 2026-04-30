# Capstone Finance Ledger Application

## Project Description
This is a Java-based Finance Ledger application that allows users to track financial transactions, view reports, and filter data using different criteria.

The application reads and writes data from a CSV file and provides a menu-driven interface for interaction.

---

## Features

- Add and store transactions
- View all transactions
- Filter deposits and payments
- Monthly and yearly reports
- Search by vendor
- Custom search (date, amount, description, vendor)
- Formatted table display using ASCII tables

---

## Technologies Used

- Java 17
- File I/O (BufferedReader, FileWriter)
- Java Time API (LocalDate, LocalTime)
- Maven
- AsciiTable library

---

##  Project Structure

- Transaction → Data model for transactions
- FileManager → Handles reading/writing CSV files
- LedgerService → Business logic and menu system
- TableFormat → Formats output in table form

---

## How to Run

1. Clone the repository
2. Open in IntelliJ or any IDE
3. Run the `Main` class
4. Use menu options to interact

---

##  File Format

Transactions are stored in:
Date | Time | Description | Vendor | Amount
