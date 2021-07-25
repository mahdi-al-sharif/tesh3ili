package com.mahdi.tesh3ilisavingbox;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Query;

import java.sql.Date;

@Entity(tableName = "income_table")
public class Income {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int memberId;

    private int monthlyIn;

    private int monthlyInCoal;

    private int paymentDate;



    public Income(int memberId, int monthlyIn, int monthlyInCoal, int paymentDate) {
        this.memberId=memberId;
        this.monthlyIn = monthlyIn;
        this.monthlyInCoal = monthlyInCoal;
        this.paymentDate = paymentDate;

    }

    public int getId() {
        return id;
    }

    public int getMonthlyIn() {
        return monthlyIn;
    }

    public int getMonthlyInCoal() {
        return monthlyInCoal;
    }

    public int getPaymentDate() {
        return paymentDate;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setId(int id) {
        this.id = id;
    }
}
