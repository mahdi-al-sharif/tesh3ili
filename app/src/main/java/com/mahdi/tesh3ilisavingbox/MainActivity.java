package com.mahdi.tesh3ilisavingbox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_MEMBER_REQUEST = 1;
    public static final int ADD_INCOME_REQUEST = 2;
    public static final String EXTRA_MEMBER_NAME = "com.mahdi.tesh3ilisavingbox.EXTRA_MEMBER_NAME";
    public static final String EXTRA_MEMBER_ID = "com.mahdi.tesh3ilisavingbox.EXTRA_MEMBER_ID";

    ExpenseAdapter expenseAdapter;
    EditText editTextPass;
    TextView textViewPass;
    Button buttonDelete;
    RecyclerView recyclerView;
    ImageView imageView;
    CardView cardView;

    private CashViewModel cashViewModel;
    private List<Income> incomes = new ArrayList<>();
    private List<Expense> expenses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expenseAdapter = new ExpenseAdapter();

        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();

        editTextPass = findViewById(R.id.edit_text_password);
        textViewPass = findViewById(R.id.textview_enter_pass);
        buttonDelete = findViewById(R.id.button_delete);
        imageView = findViewById(R.id.image_view);
        cardView = findViewById(R.id.card_view);


        new CountDownTimer(2000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerVisibilityOn();
            }

            @Override
            public void onFinish() {
                timerVisibilityOff();
            }
        }.start();


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        MemberAdapter memberAdapter = new MemberAdapter();
        recyclerView.setAdapter(memberAdapter);


        cashViewModel = ViewModelProviders.of(this).get(CashViewModel.class);
        cashViewModel.getAllMembers().observe(this, new Observer<List<Member>>() {
            @Override
            public void onChanged(List<Member> members) {
                memberAdapter.setMembers(members);


            }
        });
        cashViewModel.getAllIncomes().observe(this, new Observer<List<Income>>() {
            @Override
            public void onChanged(List<Income> incomes) {
                memberAdapter.setIncomes(incomes);
                MainActivity.this.incomes.addAll(incomes);

                TextView tvMonPay = findViewById(R.id.text_view_month_paymentsnum);
                TextView tvAllPay = findViewById(R.id.text_view_all_paymentsnum);

                int month = 0;
                int all = 0;

                for (Income x : incomes) {
                    all += x.getMonthlyIn() + x.getMonthlyInCoal();
                    if (x.getPaymentDate() == Integer.valueOf(dateFormat.format(date))) {
                        month += x.getMonthlyIn() + x.getMonthlyInCoal();
                    }

                }
                tvMonPay.setText(month + " LBP");
                tvAllPay.setText(all + " LBP");
            }
        });


        cashViewModel.getAllExpenses().observe(this, new Observer<List<Expense>>() {
            @Override
            public void onChanged(List<Expense> expenses) {
                MainActivity.this.expenses.addAll(expenses);

                TextView tvMonExp = findViewById(R.id.text_view_month_expensenum);
                TextView tvAllExp = findViewById(R.id.text_view_all_expensenum);

                int month = 0;
                int all = 0;

                for (Expense x : expenses) {
                    all += x.getExpenseValue();
                    if (x.getDate() == Integer.valueOf(dateFormat.format(date))) {
                        month += x.getExpenseValue();
                    }

                }
                tvMonExp.setText(month + " LBP");
                tvAllExp.setText(all + " LBP");

            }
        });


        memberAdapter.setOnItemClickListener(new MemberAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Member member) {
                List<Income> incomesForMember = new ArrayList<>();
                for (Income x : incomes) {
                    if (x.getMemberId() == member.getId()) {
                        incomesForMember.add(x);
                    }
                }
                IncomeForMemberActivity incomeForMemberActivity = new IncomeForMemberActivity();
                incomeForMemberActivity.setIncomes(incomesForMember);
                Intent intent = new Intent(MainActivity.this, IncomeForMemberActivity.class);
                intent.putExtra(EXTRA_MEMBER_NAME, member.getMemberName());
                intent.putExtra(EXTRA_MEMBER_ID, member.getId());
                startActivityForResult(intent, ADD_INCOME_REQUEST);


            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.clear_data:
                invertViews();
                return true;

            case R.id.add_member:
                Intent intent = new Intent(MainActivity.this, AddMemberActivity.class);
                startActivityForResult(intent, ADD_MEMBER_REQUEST);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void deleteData(View view) {

        String pass = editTextPass.getText().toString();

        if (pass.equals("1234")) {
            cashViewModel.deleteAllIncomes();
            cashViewModel.deleteAllExspenses();
            cashViewModel.deleteAllMembers();
            Toast.makeText(this, "all data cleared", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "wrong password", Toast.LENGTH_SHORT).show();
        }

        invertViewsNormal();
        editTextPass.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_MEMBER_REQUEST && resultCode == RESULT_OK) {
            String memberName = data.getStringExtra(AddMemberActivity.EXTRA_MEMBER);
            Member member = new Member(memberName);
            cashViewModel.insertMember(member);
            Toast.makeText(this, "Member Saved", Toast.LENGTH_SHORT).show();

        } else if (requestCode == ADD_INCOME_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(IncomeForMemberActivity.EXTRA_ID, -1);
            int core = data.getIntExtra(IncomeForMemberActivity.EXTRA_CORE, -1);
            int coal = data.getIntExtra(IncomeForMemberActivity.EXTRA_COAL, -1);
            int month = data.getIntExtra(IncomeForMemberActivity.EXTRA_MONTH, -1);
            cashViewModel.insertIncome(new Income(id, core, coal, month));


        } else {
            Toast.makeText(this, "Not Saved", Toast.LENGTH_SHORT).show();
        }
    }


    public void invertViews() {
        editTextPass.setVisibility(View.VISIBLE);
        textViewPass.setVisibility(View.VISIBLE);
        buttonDelete.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    public void invertViewsNormal() {
        editTextPass.setVisibility(View.INVISIBLE);
        textViewPass.setVisibility(View.INVISIBLE);
        buttonDelete.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    public void timerVisibilityOn() {
        recyclerView.setVisibility(View.INVISIBLE);
        cardView.setVisibility(View.INVISIBLE);
        getSupportActionBar().hide();

    }

    public void timerVisibilityOff() {
        recyclerView.setVisibility(View.VISIBLE);
        cardView.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.INVISIBLE);
        getSupportActionBar().show();
    }

    public void goToMonthExpens(View view) {
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();

        Intent intent = new Intent(MainActivity.this, ExpenseActivity.class);
        intent.putExtra("onlyMonth", dateFormat.format(date));
        startActivity(intent);
    }


    public void goToAllExpense(View view) {
        expenseAdapter.setExpenses(expenses);
        Intent intent = new Intent(MainActivity.this, ExpenseActivity.class);
        startActivity(intent);
    }
}