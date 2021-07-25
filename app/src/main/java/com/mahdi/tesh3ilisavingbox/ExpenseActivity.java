package com.mahdi.tesh3ilisavingbox;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ExpenseActivity extends AppCompatActivity {

    CashViewModel cashViewModel;
    ExpenseAdapter expenseAdapter;
    boolean isMonth=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Expenses");

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        expenseAdapter = new ExpenseAdapter();
        recyclerView.setAdapter(expenseAdapter);


        cashViewModel = ViewModelProviders.of(this).get(CashViewModel.class);
        cashViewModel.getAllExpenses().observe(this, new Observer<List<Expense>>() {
            @Override
            public void onChanged(List<Expense> expenses) {
                Intent intent = getIntent();

                if (intent.hasExtra("onlyMonth")) {
                    isMonth=true;
                    int month = Integer.valueOf(intent.getStringExtra("onlyMonth"));
                    List<Expense> monthlyExpenses = new ArrayList<>();
                    for (Expense x : expenses) {
                        if (x.getDate() == month) {
                            monthlyExpenses.add(x);
                        }
                    }
                    expenseAdapter.setExpenses(monthlyExpenses);
                } else {
                    expenseAdapter.setExpenses(expenses);
                }


            }
        });
    }

    public void addExpense(View view) {
        Intent intent = new Intent(this, AddExpenseActivity.class);
        if (isMonth){
            intent.putExtra("isMonth",true);
        }
        startActivity(intent);
    }


}