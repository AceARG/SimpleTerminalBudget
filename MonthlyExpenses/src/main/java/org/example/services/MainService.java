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
    private ExpensesObject transaction;
    private List<Double> incomeList;
    private List<ExpensesObject> transactions;

    public MainService(Double income, Double balance, Double incomeValue, Double expense, List<ExpensesObject> transactions, List<Double> incomeList, ExpensesObject transaction) {
        this.income = income;
        this.balance = balance;
        this.incomeValue = incomeValue;
        this.expenses = expense;
        this.transactions = transactions;
        this.incomeList = incomeList;
        this.transaction = transaction;
    }

    public MainService() {}

    public void renderGUI() {
        //Initialize Object Instances
        Scanner consoleInput = new Scanner(System.in);
        ViewService viewService = new ViewService();
        List<Double> expensesList = new ArrayList<>();

        //Local Global Variables
        boolean render = true;
        income = 0.00;
        balance = 0.00;
        incomeValue = 0.00;
        expenses = 0.00;
        transaction = new ExpensesObject();
        incomeList = new ArrayList<>();
        transactions = new ArrayList<>();

        while (render) {
            this.balance = incomeValue - expenses;
            viewService.viewBank(balance, income);//Calculate Balance
            viewService.viewOptions();
            int option = consoleInput.nextInt();

            switch (option) {
                case 1:
                    //ADD INCOME

                    viewService.viewAddIncome();
                    income = consoleInput.nextDouble();

                    this.incomeList.add(income);

                    if (this.incomeList != null) {
                        this.incomeValue = this.incomeList.stream().mapToDouble(Double::doubleValue).sum();
                        this.income = this.incomeValue;
                    } else {
                        incomeValue = income;
                    }
                    break;

                case 2:
                    //ADD EXPENSES
                    List<ExpensesObject> expensesObjects = new ArrayList<>();
                    boolean endLoop = false;
                    while (!endLoop) {

                        viewService.viewAddExpensesDescription();
                        String description = consoleInput.next().toLowerCase().replaceAll("\\s+", "_");

                        viewService.viewAddExpensesAmount();
                        Double amount = consoleInput.nextDouble();

                        ExpensesObject expensesObject = new ExpensesObject(description, amount);

                        System.out.println("end with 'end' or '.' to continue");
                        String endLoopInput = consoleInput.next();

                        if (endLoopInput.equalsIgnoreCase("end")) {
                            endLoop = true;
                        }

                        expensesObjects.add(expensesObject);
                        expensesList.add(amount);

                    }
                    this.expenses = expensesList.stream().mapToDouble(Double::doubleValue).sum();
                    this.transactions.addAll(expensesObjects);

                    viewService.viewTransactions(expensesObjects);
                    break;

                case 3:
                    if (this.transactions.isEmpty()) {
                        System.out.println("THERE ARE NO TRANSACTIONS");

                    } else {
                        viewService.viewTransactions(transactions);
                    }

                default:

            }

            if (option == 0) {
                render = false;
            }

        }

    }
}
