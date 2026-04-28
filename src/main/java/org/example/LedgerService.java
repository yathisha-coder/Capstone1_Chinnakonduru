package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LedgerService {
    public static void getLedger(){
        Scanner scan = new Scanner(System.in);
        List<Transaction>  transactions = FileManager.getTransaction();
        while(true){
            System.out.println("=========Ledger Menu=====");
            System.out.println("A.All");
            System.out.println("D.Deposits");
            System.out.println("P.Payments");
            System.out.println("R.Reports");
            System.out.println("H.Home Menu");
            String input = scan.nextLine();

                switch (input.toUpperCase()){
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
    public static void displayFormat(List<Transaction> transactions){
        System.out.printf("%-12s %-15s %-30s %-15s %10s%n",
                "Date", "Time", "Description","Vendor","Amount");
        System.out.println("-------------------------------------------------------------------------------------------");
        for (Transaction transaction : transactions){
            System.out.printf("%-12s %-15s %-30s %-15s %10.2f%n",
                    transaction.getDate(),transaction.getTime(),transaction.getDescription(),
                    transaction.getVendor(),transaction.getAmount());
        }
    }

//    public static void displayAllTransaction(List<Transaction>  transactions ){
//                 displayFormat(transactions);
//    }
    public static void displayAllDeposit(List<Transaction>  transactions ){

        List<Transaction> deposits = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
                deposits.add(transaction);
            }
        }
        displayFormat(deposits);
    }
    public static void displayAllPayments(List<Transaction> transactions){

          List<Transaction> payments = new ArrayList<>();

           for(Transaction transaction: transactions){
               if(transaction.getAmount() < 0){
                   payments.add(transaction);
               }
           }
           displayFormat(payments);
    }

    public static void displayReports(Scanner scan,List<Transaction> transactions){
           while (true){
               System.out.println("--------Report Screen---------");
               System.out.println("1.Month To Date");
               System.out.println("2.Year To Date");
               System.out.println("3.Search by Vendor");
               System.out.println("4.Back");
               int input = scan.nextInt();
               scan.nextLine();
               switch (input){
                   case 1:
                       showsByMonthToDate(transactions);
                       break;

                   case 2:
                       showsYearToDate(transactions);
                       break;

                   case 3:
                       searchByVendor(scan,transactions);
                       break;
                   case 4:
                       return;
                   default:
                       System.out.println("Invalid option.Try again!");
               }

           }
    }
    public static void showsByMonthToDate(List<Transaction> transactions){
          List<Transaction> result = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (Transaction transaction: transactions){
            if(transaction.getDate().getMonth() == today.getMonth() &&
            transaction.getDate().getYear() == today.getYear()){
                result.add(transaction);
            }
        }
        displayFormat(result);
    }

    public static void showsYearToDate(List<Transaction> transactions){
        List<Transaction> result = new ArrayList<>();
        int currentYear = LocalDate.now().getYear();
        for (Transaction transaction : transactions){
            if(transaction.getDate().getYear() == currentYear){
                result.add(transaction);
            }
        }
        displayFormat(result);
    }
    public static void searchByVendor(Scanner scan,List<Transaction> transactions){
        System.out.println("Enter Vendor Name: ");
        String vendorInput = scan.nextLine();
        List<Transaction> result = new ArrayList<>();
        for (Transaction transaction : transactions){
            if(transaction.getVendor().toLowerCase().contains(vendorInput)){
                result.add(transaction);
            }
        }
        displayFormat(result);

    }


}
