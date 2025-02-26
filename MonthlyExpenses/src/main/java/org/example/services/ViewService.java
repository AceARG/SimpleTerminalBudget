package org.example.services;

import org.example.model.ExpensesObject;

import java.util.List;

//Library Of Methods to Display Templates Return Type -> String
public class ViewService {
    //Bank
    public void viewBank(Double balance, Double income) {
        System.out.println("=========================");
        System.out.println("BANK : R" + balance);
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
