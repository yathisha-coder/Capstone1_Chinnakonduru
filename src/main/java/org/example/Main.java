package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (true){
            System.out.println("=====Home Menu Screen=====");
            System.out.println("D.Add Deposit");
            System.out.println("P.Make Payment(Debit)");
            System.out.println("L.Ledger");
            System.out.println("X.Exit");
            System.out.println("Enter you're choice: ");
            String input = scan.nextLine();

            switch (input.toUpperCase()){
                case "D":
                    addDeposit(scan);
                    break;
                case "P":
                    makePayment(scan);
                    break;
                case "L":
                    LedgerService.getLedger();
                    break;
                case "X":
                    System.out.println("Thank you. Have a Good Day!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option.Try again");
            }
        }
    }

    public static Transaction createTransaction(String description, String vendor, double amount){
        return new Transaction(LocalDate.now(),LocalTime.now(),
                description,vendor,amount);
    }

    public static void addDeposit(Scanner scan){
        System.out.println("=====Deposit Screen====");
        System.out.println("Description: ");
        String description = scan.nextLine();
        System.out.println("Vendor: ");
        String vendor = scan.nextLine();
        System.out.println("Amount: ");
        double amount = Double.parseDouble(scan.nextLine());

        Transaction transaction = createTransaction(description.toUpperCase(),vendor.toUpperCase(),amount);
        FileManager.writeTransaction(transaction);
        System.out.println("Deposit saved!");

    }
    public static void makePayment(Scanner scan){
        System.out.println("Description:");
        String description = scan.nextLine();
        System.out.println("Vendor:");
        String vendor = scan.nextLine();
        System.out.println("Amount");
        double amount = Double.parseDouble(scan.nextLine());
        //converting to negative for [payments
        amount = -amount;
        Transaction transaction = createTransaction(description,vendor,amount);
        FileManager.writeTransaction(transaction);
        System.out.println("Payment saved!");

    }

}