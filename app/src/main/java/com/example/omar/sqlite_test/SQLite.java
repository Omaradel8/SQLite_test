package com.example.omar.sqlite_test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class SQLite  extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "sqlapp";
    private static final int VERSION_NUMBER = 1;
    private static final String TABLE_NAME = "employee";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_SALARY = "salary";
    private static final String KEY_JOB = "job";


    public SQLite(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createstatement = "CREATE TABLE "+TABLE_NAME+"("+
                KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_NAME+" VARCHAR(100), " +
                KEY_ADDRESS+" VARCHAR(100), " +
                KEY_SALARY+" INTEGER ," +
                KEY_JOB+" VARCHAR(100) )";
        db.execSQL(createstatement);
        Log.e("rrr","error");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropstatement = "DROP TABLE IF EXISTS " +TABLE_NAME;
        db.execSQL(dropstatement);
        onCreate(db);

    }

    public ArrayList<Employee> getEmployees(){
        ArrayList<Employee> employees = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor=db.rawQuery(" select * from "+TABLE_NAME, null,null);
        while(cursor.moveToNext()){
            Employee emp = new Employee();
            emp.setId(cursor.getInt(0));
            emp.setName(cursor.getString(1));
            emp.setAddress(cursor.getString(2));
            emp.setSalary(cursor.getInt(3));
            emp.setJob(cursor.getString(4));
            employees.add(emp);

        }
        cursor.close();
        db.close();
        return employees;
    }

    public void addEmployee(Employee employee){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,employee.getName());
        values.put(KEY_ADDRESS,employee.getAddress());
        values.put(KEY_SALARY,employee.getSalary());
        values.put(KEY_JOB,employee.getJob());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public void update_emp(Employee employee){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,employee.getName());
        values.put(KEY_ADDRESS,employee.getAddress());
        values.put(KEY_SALARY,employee.getSalary());
        values.put(KEY_JOB,employee.getJob());
        db.update(TABLE_NAME,values,KEY_ID+"=?",new String[]{String.valueOf(employee.getId())});
        db.close();


    }
    public void delete_emp(Employee employee){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,KEY_ID+"=?" , new String[]{String.valueOf(employee.getId())});
        db.close();


    }


}
