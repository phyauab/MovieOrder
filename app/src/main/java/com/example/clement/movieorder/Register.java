package com.example.clement.movieorder;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    //database
    private MySQLiteHelper sqlHelper;

    private SQLiteDatabase mydb;
    private SQLiteDatabase mydb2;
    private Cursor myCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sqlHelper = new MySQLiteHelper(this);
        mydb = sqlHelper.getReadableDatabase();
    }

    public void register(View v)
    {
        EditText userName = (EditText)findViewById(R.id.newusername);
        EditText passWord = (EditText)findViewById(R.id.newpassword);
        EditText age = (EditText)findViewById(R.id.newage);

        String newUserName = userName.getText().toString();
        String newPassWord = passWord.getText().toString();
        String newAge = age.getText().toString();

        boolean success = true;

        if(newUserName.equals("") || newPassWord.equals("") || newAge.equals(""))
        {
            Toast.makeText(getApplicationContext(),"All fields must be field!", Toast.LENGTH_LONG).show();
            return;
        }


        myCursor = mydb.rawQuery("SELECT * FROM client", null);
        myCursor.moveToFirst();
        if (myCursor.moveToFirst()){
            do{
                if(newUserName.equals(myCursor.getString(myCursor.getColumnIndex("username"))))
                    success = false;
            }
            while(myCursor.moveToNext());
        }myCursor.close();

        if (success) {
            mydb2 = sqlHelper.getWritableDatabase();
            mydb2.execSQL("INSERT INTO client(_id, username, password, age, credit) VALUES(NULL, " + "'" + newUserName + "'" + ", '" + newPassWord + "', '" + newAge + "',1000);");
            Toast.makeText(getApplicationContext(),"Register Successfully", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent();
            intent.setClass(Register.this, Login.class);
            Bundle bundle = new Bundle();
            intent.putExtras(bundle);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
        }
        else
            Toast.makeText(getApplicationContext(),"Username Has Been Registered", Toast.LENGTH_SHORT).show();
    }
}
