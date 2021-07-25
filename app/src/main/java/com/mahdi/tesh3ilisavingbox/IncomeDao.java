package com.mahdi.tesh3ilisavingbox;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface IncomeDao {

    @Insert
    public void inserPayment(Income income);

    @Update
    public void updatePayment(Income income);

    @Delete
    public void deletePayment(Income income);

    @Query("DELETE FROM income_table")
    public void deleteAllPayments();

    @Query("SELECT * FROM income_table")
    LiveData<List<Income>> getAllIncomes();


}
