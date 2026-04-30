package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/*Handles all file input and output operations
* responsible for reading transactions from a csv file
* and writing new transaction back to the file*/
public class FileManager {
    //reads transaction file and converts each line into a transaction object
    // return list of all transactions from the file
    public static List<Transaction> getTransaction() {
        //Creating a list to store transaction objects
        List<Transaction> transactions = new ArrayList<>();
        try {
            //open the file for reading
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/transactions.csv"));
            String input;

            while ((input = reader.readLine()) != null){
                //spilt each line using "|" as delimiter
                String[] parts = input.split("\\|");
                //convert string values into proper data types
                LocalDate date = LocalDate.parse(parts[0]);
                LocalTime time = LocalTime.parse(parts[1]);
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);
                //create transaction object from parsed data
                Transaction transaction = new Transaction(date,time,description,vendor,amount);
                transactions.add(transaction);
            }
            reader.close();
        }catch (IOException e){
            System.out.println("There was a problem reading the transaction file.");
        }
        catch (Exception e){
            System.out.println("Something went wrong.");
        }
        //Reverse list so newest transaction appear first
        Collections.reverse(transactions);
        return transactions;
    }
        //writes a new transaction to the csv file
        public static void writeTransaction(Transaction transaction){
        try{
            File file = new File("src/main/resources/transactions.csv");
            FileWriter fileWriter = new FileWriter(file,true);

//            if(file.length()>0) {
//                fileWriter.write(System.lineSeparator());
//            }
            //Format date and timer for file storage
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            //write transaction in csv format
            fileWriter.write(String.format("%s|%s|%s|%s|%.2f%n",transaction.getDate().format(dateFormatter),
                    transaction.getTime().format(timeFormatter),transaction.getDescription(),
                    transaction.getVendor(),transaction.getAmount()));
            fileWriter.close();
        }
        catch (IOException ex){
            System.out.println("Error writing to file.");
        }catch (Exception e){
            System.out.println("Something went wrong");
        }
        }
}
