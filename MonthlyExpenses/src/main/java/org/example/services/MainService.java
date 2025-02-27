package org.example.services;

import org.example.models.ExpensesObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainService  {
    private Double income;
    private Double balance;
    private Double incomeValue;
    private Double expenses;
    private final List<Double> incomeList;
    private final List<ExpensesObject> transactions;

    public MainService() {
        income = 0.00;
        balance = 0.00;
        incomeValue = 0.00;
        expenses = 0.00;
        incomeList = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    //render gui
    public void renderGUI() {
        //INITIALIZE OBJECTS
        Scanner consoleInput = new Scanner(System.in);
        ViewService viewService = new ViewService();
        List<Double> expensesList = new ArrayList<>();

        //IS RENDERING
        boolean render = true;

        //LOGO DISPLAY
        viewService.viewLogo();

        while (render) {
            this.balance = incomeValue - expenses;
            viewService.viewBank(balance, income, expenses);//CALCULATE BALANCE

            viewService.viewOptions();
            int option = consoleInput.nextInt();

            switch (option) {
                case 1:
                    //ADD INCOME
                    viewService.viewAddIncome();
                    income = consoleInput.nextDouble();

                    this.incomeList.add(income);

                    this.incomeValue = this.incomeList.stream().mapToDouble(Double::doubleValue).sum();
                    this.income = this.incomeValue;
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
                    //VIEW TRANSACTIONS
                    if (this.transactions.isEmpty()) {
                        System.out.println("THERE ARE NO TRANSACTIONS");

                    } else {
                        viewService.viewTransactions(transactions);
                    }

                default:

            }

            //EXIT
            if (option == 0) {
                render = false;
            }

        }

    }
}
