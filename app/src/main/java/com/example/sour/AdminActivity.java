package com.example.sour;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

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
                dbHelper.Register(mail.getText().toString(),passwd.getText().toString());
                Toast.makeText(getApplicationContext(), "Запись успешно добавлена",Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.buttonDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper db = new DbHelper(AdminActivity.this);
                EditText id=findViewById(R.id.editTextKey);
                String Id = id.getText().toString();
                db.DeleteOne(Id);
            }
        });
        findViewById(R.id.buttonDeleteAll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper db = new DbHelper(AdminActivity.this);
                db.DeleteAll();
            }
        });

        findViewById(R.id.buttonSelect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText id=findViewById(R.id.editTextKey);
                String Id = id.getText().toString();

                DbHelper db = new DbHelper(AdminActivity.this);

                LinkedList<Data> list =  db.GetOne(Id);
                EditText mail = findViewById(R.id.editTextValue);
                EditText passwd=findViewById(R.id.editTextValue2);
                String email = "";
                for (Data d:list) email = d.email;
                mail.setText(email);
                String password = "";

                for (Data d:list) password = d.passwd;

                passwd.setText(password);
                ;

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

                db.UpdateOne(Id, mail, passwd);
            }
        });
    }
}