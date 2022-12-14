package com.example.sour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.LinkedList;

public class ActivityLogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        findViewById(R.id.imageButtonReturn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLogIn.this, MainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.buttonLog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper db = new DbHelper(ActivityLogIn.this);

                String login = ((EditText) findViewById(R.id.editTextTextEmailAddress)).getText().toString();
                String password = ((EditText) findViewById(R.id.editTextTextPassword)).getText().toString();


                if (login.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Введите логин",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (db.Login(login, password)) {
                    if(login.equals("admin") || password.equals("admin") ){
                        Intent switcher = new Intent(ActivityLogIn.this, AdminActivity.class);
                        ActivityLogIn.this.startActivity(switcher);
                    }
                    else
                    Toast.makeText(getApplicationContext(), "Есть такой!",
                            Toast.LENGTH_SHORT).show();// переход в новое окно с приветствием
                } else {
                    Toast.makeText(getApplicationContext(), "данные некорректны",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}