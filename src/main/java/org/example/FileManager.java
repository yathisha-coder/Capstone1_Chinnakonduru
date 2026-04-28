package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileManager {
    public static List<Transaction> getTransaction() {
        //Creating a list to store transaction objects
        List<Transaction> transactions = new ArrayList<>();
        try {
            //open the file for reading
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/transactions.csv"));
            String input;

            while ((input = reader.readLine()) != null){
                String[] parts = input.split("\\|");
                LocalDate date = LocalDate.parse(parts[0]);
                LocalTime time = LocalTime.parse(parts[1]);
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);

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
        return transactions;
    }

        public static void writeTransaction(Transaction transaction){
        try{
            File file = new File("src/main/resources/transactions.csv");
            FileWriter fileWriter = new FileWriter(file,true);

//            if(file.length()>0) {
//                fileWriter.write(System.lineSeparator());
//            }

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

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
