package com.mahdi.tesh3ilisavingbox;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExpenseDao {

    @Insert
    public void insertExpense(Expense expense);

    @Update
    public void updateExpense(Expense expense);

    @Delete
    public void deleteExpense(Expense expense);

    @Query("DELETE FROM expense_table")
    public void deleteAllExpenses();

    @Query("SELECT * FROM expense_table")
    LiveData<List<Expense>> getAllExpenses();

}
