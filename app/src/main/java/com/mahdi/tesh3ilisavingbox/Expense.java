package com.mahdi.tesh3ilisavingbox;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "expense_table")
public class Expense {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int expenseValue;

    private boolean isFa7m;

    private String expenseDescription;

    private int date;

    public Expense(int expenseValue, String expenseDescription, int date,boolean isFa7m) {
        this.expenseValue = expenseValue;
        this.expenseDescription = expenseDescription;
        this.date = date;
        this.isFa7m=isFa7m;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public int getExpenseValue() {
        return expenseValue;
    }

    public String getExpenseDescription() {
        return expenseDescription;
    }

    public boolean isFa7m() {
        return isFa7m;
    }
}
