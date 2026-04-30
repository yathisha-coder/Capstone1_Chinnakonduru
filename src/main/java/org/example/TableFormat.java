package org.example;

import de.vandermeer.asciitable.AsciiTable;

import java.util.List;

/* Handles display formatting of transactions using ASCII table
* Improves readability of output in console.*/
public class TableFormat {
    /*Displays a list of transactions in a structured table format
    * using the ASCII table library.*/
        public static void displayFormat(List<Transaction> transactions) {

        AsciiTable at = new AsciiTable();

        // Header
        at.addRule();
        //Add the table header
        at.addRow("Date", "Time", "Description", "Vendor", "Amount");
        at.addRule();

        // Rows
        for (Transaction t : transactions) {
            at.addRow(
                    t.getDate(),
                    t.getTime(),
                    t.getDescription(),
                    t.getVendor(),
                    String.format("%.2f", t.getAmount())
            );

            at.addRule();
        }

        // Print table
        System.out.println(at.render());
    }
}
