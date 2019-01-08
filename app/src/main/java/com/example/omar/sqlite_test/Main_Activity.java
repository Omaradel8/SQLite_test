package com.example.omar.sqlite_test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main_Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText user,pass;

    private Button login;

    private String username = "Mahmoud";
    private String password = "123456";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);
        login = findViewById(R.id.login);
        login.setOnClickListener(this);
        SharedPreferences preferences = getSharedPreferences("pref",MODE_PRIVATE);
        boolean islogged = preferences.getBoolean("isloggedin",false);
        if (islogged){
            Intent i = new Intent(this , Second.class);
            startActivity(i);
            finish();
        }


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login){
            if (user.getText().toString().equals(username) && pass.getText().toString().equals(password)){
                SharedPreferences preferences = getSharedPreferences("pref",MODE_PRIVATE);
                SharedPreferences.Editor editor =preferences.edit();
                editor.putBoolean("isloggedin",true);
                editor.apply();
                Intent i = new Intent(this , Second.class);
                startActivity(i);
                finish();
            }
        }

    }
}
