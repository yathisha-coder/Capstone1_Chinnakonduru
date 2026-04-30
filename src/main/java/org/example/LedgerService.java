package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.TableFormat.displayFormat;

/*
* Main service class that handles user interaction, menu navigation
* and business logic for the ledger system.*/
public class LedgerService {
    /*Displays the main ledger menu and handles user navigation
     * users can view transactions, deposits,payments, or reports.*/
    public static void getLedger() {
        Scanner scan = new Scanner(System.in);
        //Load all transactions data from the file
        List<Transaction> transactions = FileManager.getTransaction();
        while (true) {
            System.out.println("=========Ledger Menu=====");
            System.out.println("A.All");
            System.out.println("D.Deposits");
            System.out.println("P.Payments");
            System.out.println("R.Reports");
            System.out.println("H.Home Menu");
            String input = scan.nextLine().toUpperCase();

            switch (input) {
                case "A":
//                        displayAllTransaction(transactions);
                    displayFormat(transactions);
                    break;
                case "D":
                    displayAllDeposit(transactions);
                    break;
                case "P":
                    displayAllPayments(transactions);
                    break;
                case "R":
                    displayReports(scan, transactions);
                    break;
                case "H":
                    return;
                default:
                    System.out.println("Invalid Option.Try again!");
            }

        }


    }
//    public static void displayFormat(List<Transaction> transactions){
//        String separator = "+--------------+-----------------+--------------------------------+-----------------+-------------+";
//        String RED = "\u001B[31m";
//        String Green = "\u001b[32m";
//        String RESET = "\u001b[0m";
//        System.out.println(separator);
//
//        System.out.printf("| %-12s | %-15s | %-30s | %-15s | %10s  |%n",
//                "Date", "Time", "Description","Vendor","Amount");
//        System.out.println(separator);
//        for (Transaction transaction : transactions){
//            double amount = transaction.getAmount();
//            String color = (amount < 0)? RED :Green;
//            System.out.println(color);
//            System.out.printf("| %-12s | %-15s | %-30s | %-15s | %10.2f  |%n",
//                    transaction.getDate(),transaction.getTime(),transaction.getDescription(),
//                    transaction.getVendor(),transaction.getAmount());
//            System.out.println(RESET);
//            System.out.println(separator);
//        }
//    }

    //    public static void displayAllTransaction(List<Transaction>  transactions ){
//                 displayFormat(transactions);
//    }
    //Displays only deposit transactions (positive amounts)
    public static void displayAllDeposit(List<Transaction> transactions) {

        List<Transaction> deposits = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
                deposits.add(transaction);
            }
        }
        displayFormat(deposits);
    }

    //Displays only payments transaction (negative amounts)
    public static void displayAllPayments(List<Transaction> transactions) {

        List<Transaction> payments = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                payments.add(transaction);
            }
        }
        displayFormat(payments);
    }

    //Displays Reporting menu and displays them
    public static void displayReports(Scanner scan, List<Transaction> transactions) {
        while (true) {
            System.out.println("--------Report Screen---------");
            System.out.println("1.Month To Date");
            System.out.println("2.Previous Month");
            System.out.println("3.Year To Date");
            System.out.println("4.Previous Year");
            System.out.println("5.Search by Vendor");
//            System.out.println("6.Custom Search");
            System.out.println("7.Back");
            System.out.println("Enter you're Option: ");
            int input = Integer.parseInt(scan.nextLine());

            switch (input) {
                case 1:
                    showsByMonthToDate(transactions);
                    break;
                case 2:
                    showsPreviousMonth(transactions);
                    break;
                case 3:
                    showsYearToDate(transactions);
                    break;
                case 4:
                    showsPreviousYear(transactions);
                    break;
                case 5:
                    searchByVendor(scan, transactions);
                    break;
//                case 6:
//                    showsCustomerSearch(scan, transactions);
//                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid option.Try again!");
            }

        }
    }

    //Displays Month to date
    public static void showsByMonthToDate(List<Transaction> transactions) {
        List<Transaction> result = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (Transaction transaction : transactions) {
            if (transaction.getDate().getMonth() == today.getMonth() &&
                    transaction.getDate().getYear() == today.getYear()) {
                result.add(transaction);
            }
        }
        displayFormat(result);
    }

    //Displays previous month
    public static void showsPreviousMonth(List<Transaction> transactions) {
        List<Transaction> result = new ArrayList<>();
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue() - 1;
        int year = today.getYear();

        if (month == 0) {
            month = 12;
            year--;
        }
        for (Transaction transaction : transactions) {
            if (transaction.getDate().getYear() == year &&
                    transaction.getDate().getMonthValue() == month) {
                result.add(transaction);
            }
        }
        displayFormat(result);
    }

    //Displays year to date
    public static void showsYearToDate(List<Transaction> transactions) {
        List<Transaction> result = new ArrayList<>();
        int currentYear = LocalDate.now().getYear();
        for (Transaction transaction : transactions) {
            if (transaction.getDate().getYear() == currentYear) {
                result.add(transaction);
            }
        }
        displayFormat(result);
    }

    //Displays previous year
    public static void showsPreviousYear(List<Transaction> transactions) {
        List<Transaction> result = new ArrayList<>();
        int lastYear = LocalDate.now().getYear() - 1;
        for (Transaction transaction : transactions) {
            if (transaction.getDate().getYear() == lastYear) {
                result.add(transaction);
            }
        }
        displayFormat(result);
    }

    //Displays Vendor data
    public static void searchByVendor(Scanner scan, List<Transaction> transactions) {
        System.out.println("Enter Vendor Name: ");
        String vendorInput = scan.nextLine();
        List<Transaction> result = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getVendor().toLowerCase().contains(vendorInput)) {
                result.add(transaction);
            }
        }
        displayFormat(result);
    }

//    public static void showsCustomerSearch(Scanner scan, List<Transaction> transactions) {
//
//        System.out.println("Start Date (YYYY-MM-DD or blank): ");
//        String startInput = scan.nextLine();
//
//        System.out.println("End Date (YYYY-MM-DD or blank): ");
//        String endInput = scan.nextLine();
//
//        System.out.println("Description (or blank): ");
//        String description = scan.nextLine().trim().toLowerCase();
//
//        System.out.println("Vendor (or blank): ");
//        String vendor = scan.nextLine().trim().toLowerCase();
//
//        System.out.println("Amount (or blank): ");
//        String amountInput = scan.nextLine();
//
//        List<Transaction> results = new ArrayList<>();
//
//        for (Transaction transaction : transactions) {
//            boolean match = true;
//            // Start Date
//            if (!startInput.isEmpty()) {
//                LocalDate startDate = LocalDate.parse(startInput);
//                if (transaction.getDate().isBefore(startDate)) {
//                    match = false;
//                }
//            }
//
//            // End Date
//            if (!endInput.isEmpty()) {
//                LocalDate endDate = LocalDate.parse(endInput);
//                if (transaction.getDate().isAfter(endDate)) {
//                    match = false;
//                }
//            }
//
//            // Description
//            if (!description.isEmpty()) {
//                if (!transaction.getDescription().toLowerCase().contains(description.toLowerCase())) {
//                    match = false;
//                }
//            }
//
//            // Vendor
//            if (!vendor.isEmpty()) {
//                if (!transaction.getVendor().toLowerCase().contains(vendor.toLowerCase())) {
//                    match = false;
//                }
//            }
//
//            // Amount
//            if (!amountInput.isEmpty()) {
//                double amount = Double.parseDouble(amountInput);
//                if (transaction.getAmount() != amount) {
//                    match = false;
//                }
//            }
//
//        }
//        displayFormat(results);
//    }
}