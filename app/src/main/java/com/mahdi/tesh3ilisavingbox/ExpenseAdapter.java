package com.mahdi.tesh3ilisavingbox;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseHolder> {
    List<Expense> expenses=new ArrayList<>();

    @NonNull
    @Override
    public ExpenseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expense_item, parent, false);
        return new ExpenseAdapter.ExpenseHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseHolder holder, int position) {
        Expense currentExpense =expenses.get(position);
        if (currentExpense.isFa7m()){
            holder.textViewAmount.setTextColor(Color.parseColor("#b00020"));
        }
        holder.textViewAmount.setText(String.valueOf(currentExpense.getExpenseValue()));
        holder.textViewDescription.setText(currentExpense.getExpenseDescription());

        String[] months={"January","February","March","April","May","June","July","Augest","September","October","November","December"};
        holder.textViewDate.setText(months[(currentExpense.getDate())-1]);

    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }


    public void setExpenses(List<Expense> expenses){
        this.expenses=expenses;
        notifyDataSetChanged();
    }



    class ExpenseHolder extends RecyclerView.ViewHolder{
        private TextView textViewAmount,textViewDescription,textViewDate;

        public ExpenseHolder(@NonNull View itemView) {
            super(itemView);
            textViewAmount=itemView.findViewById(R.id.text_view_amount);
            textViewDate=itemView.findViewById(R.id.text_view_date);
            textViewDescription=itemView.findViewById(R.id.text_view_description);
        }
    }
}
