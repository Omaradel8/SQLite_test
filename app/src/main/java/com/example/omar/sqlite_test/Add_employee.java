package com.example.omar.sqlite_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Add_employee extends AppCompatActivity  implements View.OnClickListener {
    private EditText name,address,salary,job;
    private Button save;
    boolean flag;
    Employee emp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_employee);
        name = findViewById(R.id.name_et);
        address = findViewById(R.id.address_et);
        salary = findViewById(R.id.salary_et);
        job = findViewById(R.id.job_et);
        save = findViewById(R.id.save);
        save.setOnClickListener(this);

        Intent i =getIntent();
        flag=i.getBooleanExtra("update",false);
        if (flag){
            emp=(Employee) i.getSerializableExtra("employee");
            name.setText(emp.getName());
            address.setText(emp.getAddress());
            salary.setText(String.valueOf(emp.getSalary()));
            job.setText(emp.getJob());
        }


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.save){
            SQLite s = new SQLite(this);
            Employee e=new Employee();
            e.setName(name.getText().toString());
            e.setAddress(address.getText().toString());
            e.setSalary(Double.parseDouble(salary.getText().toString()));
            e.setJob(job.getText().toString());
            if (flag){
                e.setId(emp.getId());
                s.update_emp(e);
            }else {
                s.addEmployee(e);
            }
            Intent i = new Intent(this , Second.class);
            startActivity(i);
            finish();

        }

    }
}
