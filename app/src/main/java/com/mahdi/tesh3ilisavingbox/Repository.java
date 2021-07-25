package com.mahdi.tesh3ilisavingbox;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {

    private MemberDao memberDao;
    private IncomeDao incomeDao;
    private ExpenseDao expenseDao;

    private LiveData<List<Member>> allMembers;
    private LiveData<List<Income>> allIncome;
    private LiveData<List<Expense>> allExpense;

    public Repository(Application application){
        CashDatabase database=CashDatabase.getInstance(application);
        memberDao=database.memberDao();
        incomeDao=database.incomeDao();
        expenseDao=database.expenseDao();
        allMembers=memberDao.getAllMembers();
        allIncome=incomeDao.getAllIncomes();
        allExpense=expenseDao.getAllExpenses();
    }

    // MEMBERS

    public void insertMember(Member member){
        new InsertMemberAsyncTask(memberDao).execute(member);
    }


    public void updateMember(Member member){
        new UpdateMemberAsyncTask(memberDao).execute(member);
    }

    public void deleteMember(Member member){
        new DeleteMemberAsyncTask(memberDao).execute(member);
    }

    public void deleteAllMembers(){
        new DeleteAllMembersAsyncTask(memberDao).execute();
    }

    public LiveData<List<Member>> getAllMembers(){
        return allMembers;
    }



    private static class InsertMemberAsyncTask extends AsyncTask<Member,Void,Void>{
        MemberDao memberDao;

        public InsertMemberAsyncTask(MemberDao memberDao) {
            this.memberDao = memberDao;
        }


        @Override
        protected Void doInBackground(Member... members) {
            memberDao.insertMember(members[0]);
            return null;
        }
    }

    private static class UpdateMemberAsyncTask extends AsyncTask<Member,Void,Void>{
        MemberDao memberDao;

        public UpdateMemberAsyncTask(MemberDao memberDao) {
            this.memberDao = memberDao;
        }


        @Override
        protected Void doInBackground(Member... members) {
            memberDao.updateMember(members[0]);
            return null;
        }
    }

    private static class DeleteMemberAsyncTask extends AsyncTask<Member,Void,Void>{
        MemberDao memberDao;

        public DeleteMemberAsyncTask(MemberDao memberDao) {
            this.memberDao = memberDao;
        }


        @Override
        protected Void doInBackground(Member... members) {
            memberDao.deleteMember(members[0]);
            return null;
        }
    }

    private static class DeleteAllMembersAsyncTask extends AsyncTask<Void,Void,Void>{
        MemberDao memberDao;

        public DeleteAllMembersAsyncTask(MemberDao memberDao) {
            this.memberDao = memberDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            memberDao.deleteAllMembers();
            return null;
        }
    }





    //INCOME


    public void insertIncome(Income income){
        new InsertIncomeAsyncTask(incomeDao).execute(income);
    }

    public void updateIncome(Income income){
        new UpdateIncomeAsyncTask(incomeDao).execute(income);
    }

    public void deleteIncome(Income income){
        new DeleteIncomeAsyncTask(incomeDao).execute(income);
    }

    public void deleteAllIncomes(){
        new DeleteAllIncomesAsyncTask(incomeDao).execute();
    }

    public LiveData<List<Income>> getAllIncome(){
        return allIncome;
    }

    private static class InsertIncomeAsyncTask extends AsyncTask<Income,Void,Void>{
        IncomeDao incomeDao;

        public InsertIncomeAsyncTask(IncomeDao incomeDao) {
            this.incomeDao = incomeDao;
        }


        @Override
        protected Void doInBackground(Income... incomes) {
            incomeDao.inserPayment(incomes[0]);
            return null;
        }
    }

    private static class UpdateIncomeAsyncTask extends AsyncTask<Income,Void,Void>{
        IncomeDao incomeDao;

        public UpdateIncomeAsyncTask(IncomeDao incomeDao) {
            this.incomeDao = incomeDao;
        }

        @Override
        protected Void doInBackground(Income... incomes) {
            incomeDao.updatePayment(incomes[0]);
            return null;
        }
    }

    private static class DeleteIncomeAsyncTask extends AsyncTask<Income,Void,Void>{
        IncomeDao incomeDao;

        public DeleteIncomeAsyncTask(IncomeDao incomeDao) {
            this.incomeDao = incomeDao;
        }

        @Override
        protected Void doInBackground(Income... incomes) {
            incomeDao.deletePayment(incomes[0]);
            return null;
        }
    }

    private static class DeleteAllIncomesAsyncTask extends AsyncTask<Void,Void,Void>{
        IncomeDao incomeDao;

        public DeleteAllIncomesAsyncTask(IncomeDao incomeDao) {
            this.incomeDao = incomeDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            incomeDao.deleteAllPayments();
            return null;
        }
    }




    //EXPENSE


    public void insertExpense(Expense expense){
        new InsertExpenseAsyncTask(expenseDao).execute(expense);
    }

    public void updateExpense(Expense expense){
        new UpdateExpenseAsyncTask(expenseDao).execute(expense);
    }

    public void deleteExpense(Expense expense){
        new DeleteExpenseAsyncTask(expenseDao).execute(expense);
    }

    public void deleteAllExpenses(){
        new DeleteAllExpensesAsyncTask(expenseDao).execute();
    }

    public LiveData<List<Expense>> getAllExpense(){
        return allExpense;
    }

    private static class InsertExpenseAsyncTask extends AsyncTask<Expense,Void,Void>{
        ExpenseDao expenseDao;

        public InsertExpenseAsyncTask(ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }

        @Override
        protected Void doInBackground(Expense... expenses) {
            expenseDao.insertExpense(expenses[0]);
            return null;
        }
    }


    private static class UpdateExpenseAsyncTask extends AsyncTask<Expense,Void,Void>{
        ExpenseDao expenseDao;

        public UpdateExpenseAsyncTask(ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }

        @Override
        protected Void doInBackground(Expense... expenses) {
            expenseDao.updateExpense(expenses[0]);
            return null;
        }
    }

    private static class DeleteExpenseAsyncTask extends AsyncTask<Expense,Void,Void>{
        ExpenseDao expenseDao;

        public DeleteExpenseAsyncTask(ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }

        @Override
        protected Void doInBackground(Expense... expenses) {
            expenseDao.deleteExpense(expenses[0]);
            return null;
        }
    }


    private static class DeleteAllExpensesAsyncTask extends AsyncTask<Void,Void,Void>{
        ExpenseDao expenseDao;

        public DeleteAllExpensesAsyncTask(ExpenseDao expenseDao) {
            this.expenseDao = expenseDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            expenseDao.deleteAllExpenses();
            return null;
        }
    }

}
