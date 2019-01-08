package com.example.omar.sqlite_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class employee_data extends AppCompatActivity implements View.OnClickListener {
    private TextView id,name,address,salary,job;
    private Button delete,update;
    Employee e;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_data);
        id=findViewById(R.id.id_tv);
        name=findViewById(R.id.name_tv);
        address=findViewById(R.id.address_tv);
        salary=findViewById(R.id.salary_tv);
        job=findViewById(R.id.job_tv);
        delete=findViewById(R.id.delete);
        delete.setOnClickListener(this);
        update=findViewById(R.id.update);
        update.setOnClickListener(this);
        Intent i =getIntent();
        e =(Employee) i.getSerializableExtra("employee");
        id.setText(String.valueOf(e.getId()));
        name.setText(e.getName());
        address.setText(e.getAddress());
        salary.setText(String.valueOf(e.getSalary()));
        job.setText(e.getJob());

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.update){
            Intent i =new Intent(this,Add_employee.class);
            i.putExtra("update",true);
            i.putExtra("employee",e);
            startActivity(i);

        }else if (v.getId()==R.id.delete){
            SQLite s=new SQLite(this);
            s.delete_emp(e);
            Intent i=new Intent(this,Second.class);
            startActivity(i);
            finish();
        }
    }
}
