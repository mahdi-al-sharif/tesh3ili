package com.mahdi.tesh3ilisavingbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddExpenseActivity extends AppCompatActivity {
    EditText editTextValue, editTextDescription;
    NumberPicker numberPicker;
    CheckBox checkBox;
    boolean isFa7m;
    public static final String EXTRA_EXVALUE = "com.mahdi.tesh3ilisavingbox.EXTRA_EXVALUE";
    public static final String EXTRA_EXDESCRIPTION = "com.mahdi.tesh3ilisavingbox.EXTRA_EXDESCRIPTION";
    public static final String EXTRA_EXDATE = "com.mahdi.tesh3ilisavingbox.EXTRA_EXDATE";
    public static final String EXTRA_FA7M = "com.mahdi.tesh3ilisavingbox.EXTRA_FA7M";

    CashViewModel cashViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        cashViewModel = ViewModelProviders.of(this).get(CashViewModel.class);


        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();


        setTitle("Add Expense");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        checkBox = findViewById(R.id.checkBox);

        editTextValue = findViewById(R.id.edit_text_value);
        editTextDescription = findViewById(R.id.edit_text_description);
        numberPicker = findViewById(R.id.number_picker_monthe);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(12);
        numberPicker.setValue(Integer.valueOf(dateFormat.format(date)));
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "Augest", "September", "October", "November", "December"};
        numberPicker.setDisplayedValues(months);
        Intent intent = getIntent();
        if (intent.hasExtra("isMonth")) {
            numberPicker.setEnabled(!intent.getBooleanExtra("isMonth", false));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_member:

                if (editTextValue.getText().toString().isEmpty() || editTextDescription.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Please Enter Data", Toast.LENGTH_SHORT).show();

                } else {

                    int value = Integer.valueOf(editTextValue.getText().toString());
                    int date = numberPicker.getValue();
                    String description = editTextDescription.getText().toString();
                    if (checkBox.isChecked()) {
                        isFa7m = true;
                    } else {
                        isFa7m = false;
                    }
                    boolean fa7m = isFa7m;
                    Expense expense = new Expense(value, description, date, fa7m);
                    cashViewModel.insertExpense(expense);
                    finish();
                    return true;
                }
            default:
                return super.onOptionsItemSelected(item);

        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}