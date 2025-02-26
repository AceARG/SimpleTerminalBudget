package org.example.services;

import org.example.model.ExpensesObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainService  {
    private Double income;
    private Double balance;
    private Double incomeValue;
    private Double expenses;
    private List<ExpensesObject> transactions;

    public MainService(Double income, Double balance, Double incomeValue, Double expense, List<ExpensesObject> transactions) {
        this.income = income;
        this.balance = balance;
        this.incomeValue = incomeValue;
        this.expenses = expense;
        this.transactions = transactions;
    }

    public MainService() {}

    public void renderGUI() {
        //Initialize Object Instances
        Scanner consoleInput = new Scanner(System.in);
        ViewService viewService = new ViewService();
        List<Double> expensesList = new ArrayList<>();

        //Local Global Variables
        boolean render = true;
        if (income == null) {
            income = 0.00;
        }
        if (balance == null) {
            balance = 0.00;
        }
        if (incomeValue == null) {
            incomeValue = 0.00;
        }
        if (expenses == null) {
            expenses = 0.00;
        }

        while (render) {
            this.balance = incomeValue - expenses;
            viewService.viewBank(balance, income);//Calculate Balance
            viewService.viewOptions();
            int option = consoleInput.nextInt();

            switch (option) {
                case 1:
                    //ADD INCOME
                    viewService.viewAddIncome();
                    this.income = consoleInput.nextDouble();
                    this.incomeValue = income;
                    break;

                case 2:
                    //ADD EXPENSES
                    List<ExpensesObject> expensesObjects = new ArrayList<>();
                    ExpensesObject expensesObject = new ExpensesObject();
                    boolean endLoop = false;
                    while (!endLoop) {

                        viewService.viewAddExpensesDescription();
                        String description = consoleInput.next().toLowerCase().replaceAll("\\s+", "_");

                        viewService.viewAddExpensesAmount();
                        Double amount = consoleInput.nextDouble();

                        expensesObject.setDescription(description);
                        expensesObject.setAmount(amount);

                        System.out.println("end with 'end' or '.' to continue");
                        String endLoopInput = consoleInput.next();

                        if (endLoopInput.equalsIgnoreCase("end")) {
                            endLoop = true;
                        }

                        expensesObjects.add(expensesObject);
                        expensesList.add(amount);
                    }
                    this.expenses = expensesList.stream().mapToDouble(Double::doubleValue).sum();
                    if (this.transactions == null) {
                        this.transactions = expensesObjects;
                    } else {
                        this.transactions.add(expensesObject);
                    }
                    viewService.viewTransactions(expensesObjects);
                    break;
                case 3:
                    if (this.transactions != null) {
                        viewService.viewTransactions(this.transactions);
                    } else {
                        System.out.println("THERE ARE NO TRANSACTIONS");
                    }

                default:

            }

            if (option == 0) {
                render = false;
            }

        }

    }
}
