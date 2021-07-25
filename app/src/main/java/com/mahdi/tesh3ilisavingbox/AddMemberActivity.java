package com.mahdi.tesh3ilisavingbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddMemberActivity extends AppCompatActivity {
    EditText editTextUserName;
    public static final String EXTRA_MEMBER = "com.mahdi.tesh3ilisavingbox.EXTRA_NAME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
        editTextUserName = findViewById(R.id.editTextPersonName);
        setTitle("Add Member");

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String memberName = editTextUserName.getText().toString();
        switch (item.getItemId()) {
            case R.id.save_member:
                if (memberName.trim().isEmpty()) {
                    Toast.makeText(this, "please enter a name", Toast.LENGTH_SHORT).show();
                } else {
                    Intent data = new Intent();
                    data.putExtra(EXTRA_MEMBER, memberName);
                    setResult(RESULT_OK, data);
                    finish();
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}