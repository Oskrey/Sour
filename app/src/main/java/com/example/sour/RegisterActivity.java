package com.example.sour;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViewById(R.id.imageButtonReturn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.buttonLog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper db =new DbHelper(RegisterActivity.this);

                EditText email = findViewById(R.id.editTextTextEmailAddress);
                EditText passwd = findViewById(R.id.editTextTextPassword);

            }
        });
        findViewById(R.id.buttonLog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper db = new DbHelper(RegisterActivity.this);

                EditText email = findViewById(R.id.editTextTextEmailAddress);
                EditText passwd = findViewById(R.id.editTextTextPassword);

                if (email.getText().toString().isEmpty()) {
                    db.Add(email.getText().toString(),passwd.getText().toString());
                    Toast.makeText(getApplicationContext(), "Запись успешно добавлена",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Пользователь существует!\nНо только вот что значит существование?!😂",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
