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
    private List<Double> incomeList;
    private List<ExpensesObject> transactions;

    public MainService(Double income, Double balance, Double incomeValue, Double expense, List<ExpensesObject> transactions, List<Double> incomeList) {
        this.income = income;
        this.balance = balance;
        this.incomeValue = incomeValue;
        this.expenses = expense;
        this.transactions = transactions;
        this.incomeList = incomeList;
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
                    ExpensesObject expensesObject = null;
                    boolean endLoop = false;
                    while (!endLoop) {

                        viewService.viewAddExpensesDescription();
                        String description = consoleInput.next().toLowerCase().replaceAll("\\s+", "_");

                        viewService.viewAddExpensesAmount();
                        Double amount = consoleInput.nextDouble();

                        ExpensesObject expensesObjectItem = new ExpensesObject();

                        expensesObjectItem.setDescription(description);
                        expensesObjectItem.setAmount(amount);

                        expensesObject = expensesObjectItem;

                        System.out.println("end with 'end' or '.' to continue");
                        String endLoopInput = consoleInput.next();

                        if (endLoopInput.equalsIgnoreCase("end")) {
                            endLoop = true;
                        }

                        expensesObjects.add(expensesObject);
                        expensesList.add(amount);
                    }
                    this.expenses = expensesList.stream().mapToDouble(Double::doubleValue).sum();

                    if (expensesObjects != null) {
                        this.transactions.addAll(expensesObjects);
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
