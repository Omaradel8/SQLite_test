package com.example.omar.sqlite_test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class Second extends AppCompatActivity implements View.OnClickListener , AdapterView.OnItemClickListener {
    private Button logout,add_emp;
    private ListView list;
    ArrayList<Employee> employees;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(this);
        add_emp = findViewById(R.id.add_emp);
        add_emp.setOnClickListener(this);
        list = findViewById(R.id.list);
        SQLite s=new SQLite(this);
        employees= s.getEmployees();
        ArrayAdapter<Employee> adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,employees);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.logout){
            SharedPreferences preferences = getSharedPreferences("pref" , MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isloggedin", false);
            editor.apply();
            Intent i = new Intent(this , Main_Activity.class);
            startActivity(i);
            finish();
        }
        else if (v.getId() == R.id.add_emp){
            Intent i = new Intent(this , Add_employee.class);
            startActivity(i);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Employee e =employees.get(position);
        Intent i =new Intent(this,employee_data.class);
        i.putExtra("employee",e);
        startActivity(i);

    }
}
