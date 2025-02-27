package org.example.services;

import org.example.models.ExpensesObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

//Library Of Methods To Display Templates
public class ViewService {
    //logo
    public void viewLogo() {
        String fileName = "logo.txt";

        //LOAD | ACCESS RESOURCES
        ClassLoader classLoader = ViewService.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        try {
            assert inputStream != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
    //bank
    public void viewBank(Double balance, Double income, Double expenses) {
        System.out.println("=========================");
        System.out.println("BANK : R" + balance);
        System.out.println("EXPENSES : R" + expenses);
        System.out.println("INCOME : R" + income);
        System.out.println("-------------------------");
    }

    //options
    public void viewOptions() {
        System.out.println("1. ADD INCOME");
        System.out.println("2. ADD EXPENSES");
        System.out.println("3. VIEW TRANSACTIONS");
        System.out.println("0. EXIT");
        System.out.println("-------------------------");
    }

    //transactions
    public void viewTransactions(List<ExpensesObject> transactions) {
        System.out.println("=========================");
        System.out.println("TRANSACTIONS");
        System.out.println("[EXPENSES]");
        for (ExpensesObject transaction : transactions) {
            String description = transaction.getDescription();
            Double cost = transaction.getAmount();
            System.out.println(description + " : R" + cost);
        }
        System.out.println("-------------------------");
    }

    //add expenses description
    public void viewAddExpensesDescription() {
        System.out.println("ADD EXPENSES");
        System.out.println("-------------------------");
        System.out.println("Description:");

    }

    //add expenses amount
    public void viewAddExpensesAmount() {
        System.out.println("Amount:");
    }

    //add income
    public void viewAddIncome(){
        System.out.println("INCOME");
        System.out.println("-------------------------");
    }

}
