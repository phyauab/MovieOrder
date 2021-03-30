package com.example.clement.movieorder;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    //database
    private MySQLiteHelper sqlHelper;
    private SQLiteDatabase mydb;
    private Cursor myCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.profile_toolbar);
        toolbar.setTitle("Shopping Cart");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),activity_nav.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
            }
        });

        Bundle bundle = new Bundle();
        String username;
        int credit=0;
        String age = "";
        username = Data.username;

        Data.total = 0;
        sqlHelper = new MySQLiteHelper(this);
        mydb = sqlHelper.getReadableDatabase();
        myCursor = mydb.rawQuery("SELECT * FROM client WHERE username = '" + username + "';", null);
        myCursor.moveToFirst();
        if (myCursor.moveToFirst()){
            credit = myCursor.getInt(myCursor.getColumnIndex("credit"));
            age = myCursor.getString(myCursor.getColumnIndex("age"));

        }

        String ownedMovie = "";
        String[] owned = new String[10];
        myCursor = mydb.rawQuery("SELECT * FROM myMovie WHERE owner = '" + Data.username + "';", null);
        myCursor.moveToFirst();
        if (myCursor.moveToFirst()){
            ownedMovie = myCursor.getString(myCursor.getColumnIndex("owned"));
        }
        owned = convertStringToArray(ownedMovie);

        //check if the database is empty
        if(owned[0]!="") {
            for (int i = 0; i < 27; i++)
                Data.owned[i] = owned[i];

            for (int i = 0; i < 27; i++)
                Data.owned2[i] = Integer.parseInt(Data.owned[i]);

            //compute total
            for (int i = 0; i < 27; i++) {
                if (Data.owned2[i] != 0)
                    Data.total++;
            }
        }

        //get view
        TextView profile_username = (TextView)findViewById(R.id.profile_username);
        TextView profile_age = (TextView)findViewById(R.id.profile_age);
        TextView profile_purchased = (TextView)findViewById(R.id.profile_purchased);
        TextView profile_credit = (TextView)findViewById(R.id.profile_credit);

        profile_username.setText(username);
        profile_age.setText(age);
        profile_credit.setText("HKD " + Integer.toString(credit));
        profile_purchased.setText(Integer.toString(Data.total));
    }

    public static String strSeparator = "__,__";
    public static String[] convertStringToArray(String str){
        String[] arr = str.split(strSeparator);
        return arr;
    }
}






