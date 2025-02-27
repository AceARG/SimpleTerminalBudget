package org.example.services;

import org.example.models.ExpensesObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

//Library Of Methods to Display Templates Return Type -> String
public class ViewService {
    //path to logo.txt
    String filePath = "logo.txt";

    //Logo
    public void viewLogo() {

        ClassLoader classLoader = ViewService.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filePath);

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
    //Bank
    public void viewBank(Double balance, Double income, Double expenses) {
        System.out.println("=========================");
        System.out.println("BANK : R" + balance);
        System.out.println("EXPENSES : R" + expenses);
        System.out.println("INCOME : R" + income);
        System.out.println("-------------------------");
    }

    //Options
    //ADD INCOME
    //ADD EXPENSES
    public void viewOptions() {
        System.out.println("1. ADD INCOME");
        System.out.println("2. ADD EXPENSES");
        System.out.println("3. VIEW TRANSACTIONS");
        System.out.println("0. EXIT");
        System.out.println("-------------------------");
    }

    //Transactions Array of expenses Objects
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

    //Add Expenses
    public void viewAddExpensesDescription() {
        System.out.println("ADD EXPENSES");
        System.out.println("-------------------------");
        System.out.println("Description:");

    }

    public void viewAddExpensesAmount() {
        System.out.println("Amount:");
    }

    public void viewAddIncome(){
        System.out.println("INCOME");
        System.out.println("-------------------------");
    }

}
