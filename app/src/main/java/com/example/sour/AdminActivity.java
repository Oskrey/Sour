package com.example.sour;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        findViewById(R.id.buttonInsert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText mail =findViewById(R.id.editTextValue);
                EditText passwd =findViewById(R.id.editTextValue2);
                DbHelper dbHelper = new DbHelper(AdminActivity.this);
                dbHelper.Add(mail.getText().toString(),passwd.getText().toString());
                Toast.makeText(getApplicationContext(), "Запись успешно добавлена",Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.buttonUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText mail =findViewById(R.id.editTextValue);
                EditText passwd =findViewById(R.id.editTextValue2);

            }

        });
        findViewById(R.id.buttonDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper db = new DbHelper(AdminActivity.this);
                db.DeleteAll();
            }
        });
        findViewById(R.id.buttonUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper db = new DbHelper(AdminActivity.this);

                EditText id=findViewById(R.id.editTextKey);
                String Id = id.getText().toString();

                EditText ml=findViewById(R.id.editTextValue);
                String mail = ml.getText().toString();

                EditText pass=findViewById(R.id.editTextValue2);
                String passwd = pass.getText().toString();

                db.Update(Id, mail, passwd);
            }
        });
    }
}