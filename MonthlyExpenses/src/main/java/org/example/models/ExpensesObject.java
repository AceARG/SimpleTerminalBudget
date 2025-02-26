package org.example.models;

public class ExpensesObject {
    private String description;
    private Double amount;

    public ExpensesObject(String description, Double amount) {
        this.description = description;
        this.amount = amount;
    }
    public ExpensesObject(){}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ExpensesObject{" +
                "description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }
}
