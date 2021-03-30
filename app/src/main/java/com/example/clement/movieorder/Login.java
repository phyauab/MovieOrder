package com.example.clement.movieorder;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    //database
    private MySQLiteHelper sqlHelper;

    private SQLiteDatabase mydb;
    private SQLiteDatabase mydb2;
    private Cursor myCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sqlHelper = new MySQLiteHelper(this);
        mydb = sqlHelper.getReadableDatabase();

    }

    public void login(View v)
    {
        //View
        EditText username = (EditText)findViewById(R.id.username);
        EditText password = (EditText)findViewById(R.id.password);

        String inputUserName = username.getText().toString();
        String inputPassWord = password.getText().toString();

        boolean success = false;


        myCursor = mydb.rawQuery("SELECT * FROM client", null);
        myCursor.moveToFirst();
        if (myCursor.moveToFirst()){
            do{
                if(inputUserName.equals(myCursor.getString(myCursor.getColumnIndex("username"))) && inputPassWord.equals(myCursor.getString(myCursor.getColumnIndex("password"))))
                    success = true;
            }
            while(myCursor.moveToNext());
        }myCursor.close();

        if(success == true) {
            Intent intent = new Intent();
            intent.setClass(Login.this, activity_nav.class);
            Bundle bundle = new Bundle();
            bundle.putString("username", inputUserName);
            intent.putExtras(bundle);
            Data.username = inputUserName;
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
            Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(getApplicationContext(),"Login Failed", Toast.LENGTH_SHORT).show();
    }
}
