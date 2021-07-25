package com.mahdi.tesh3ilisavingbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IncomeForMemberActivity extends AppCompatActivity {
    public static final String EXTRA_ID="com.mahdi.tesh3ilisavingbox.EXTRA_ID";
    public static final String EXTRA_CORE="com.mahdi.tesh3ilisavingbox.EXTRA_CORE";
    public static final String EXTRA_COAL="com.mahdi.tesh3ilisavingbox.EXTRA_COAL";
    public static final String EXTRA_MONTH="com.mahdi.tesh3ilisavingbox.EXTRA_MONTH";
    List<Income> incomes=new ArrayList<>();

    private EditText editTextCore;
    private EditText editTextCoal;
    private NumberPicker numberPicker;
    private int memberID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_for_member);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();

        editTextCoal=findViewById(R.id.edit_text_coal);
        editTextCore=findViewById(R.id.edit_text_core);
        numberPicker=findViewById(R.id.number_picker_month);


        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(12);
        numberPicker.setValue(Integer.valueOf(dateFormat.format(date)));
        String[] months={"January","February","March","April","May","June","July","Augest","September","October","November","December"};
        numberPicker.setDisplayedValues(months);



        Intent intent=getIntent();
        setTitle(intent.getStringExtra(MainActivity.EXTRA_MEMBER_NAME));
        memberID=intent.getIntExtra(MainActivity.EXTRA_MEMBER_ID,-1);
    }

    public void setIncomes(List<Income> incomes){
        this.incomes= incomes;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_save_income,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int coal=0;
        switch (item.getItemId()){

            case R.id.save_income:
                if (editTextCore.getText().toString().isEmpty()){
                    Toast.makeText(this, "please enter values", Toast.LENGTH_SHORT).show();
                    return true;
                }else if (editTextCoal.getText().toString().isEmpty()){
                    coal=0;
                }else{
                    coal=Integer.valueOf(editTextCoal.getText().toString());
                }

                int core=Integer.valueOf(editTextCore.getText().toString());
                Intent intent=new Intent(IncomeForMemberActivity.this,MainActivity.class);
                intent.putExtra(EXTRA_ID,memberID);
                intent.putExtra(EXTRA_CORE,core);
                intent.putExtra(EXTRA_COAL,coal);
                intent.putExtra(EXTRA_MONTH,numberPicker.getValue());
                setResult(RESULT_OK,intent);
                finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}