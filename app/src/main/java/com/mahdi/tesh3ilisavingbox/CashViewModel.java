package com.mahdi.tesh3ilisavingbox;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CashViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<Member>> allMembers;
    private LiveData<List<Income>> allIncomes;
    private LiveData<List<Expense>> allExpenses;


    public CashViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
        allMembers = repository.getAllMembers();
        allIncomes = repository.getAllIncome();
        allExpenses = repository.getAllExpense();
    }

    public void insertMember(Member member) {
        repository.insertMember(member);
    }

    public void updateMember(Member member) {
        repository.updateMember(member);
    }

    public void deleteMember(Member member) {
        repository.deleteMember(member);
    }

    public void deleteAllMembers(){
        repository.deleteAllMembers();
    }

    public LiveData<List<Member>> getAllMembers(){
        return allMembers;
    }


    public void insertIncome(Income income) {
        repository.insertIncome(income);
    }

    public void updateIncome(Income income) {
        repository.updateIncome(income);
    }

    public void deleteIncome(Income income) {
        repository.deleteIncome(income);
    }

    public void deleteAllIncomes(){
        repository.deleteAllIncomes();
    }

    public LiveData<List<Income>> getAllIncomes(){
        return allIncomes;
    }




    public void insertExpense(Expense expense) {
        repository.insertExpense(expense);
    }

    public void updateExpense(Expense expense) {
        repository.updateExpense(expense);
    }

    public void deleteExpense(Expense expense) {
        repository.deleteExpense(expense);
    }

    public void deleteAllExspenses(){
        repository.deleteAllExpenses();
    }

    public LiveData<List<Expense>> getAllExpenses(){
        return allExpenses;
    }
}
