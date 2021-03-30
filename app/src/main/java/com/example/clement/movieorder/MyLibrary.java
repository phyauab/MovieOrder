package com.example.clement.movieorder;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyLibrary extends AppCompatActivity {

    private MySQLiteHelper sqlHelper;
    private SQLiteDatabase mydb;
    private Cursor myCursor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library);

        //set navigation bar
        Toolbar toolbar = findViewById(R.id.library_toolbar);
        toolbar.setTitle("My Libaray");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),activity_nav.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
            }
        });

        String ownedMovie = "";
        String[] owned = new String[10];

        sqlHelper = new MySQLiteHelper(this);
        mydb = sqlHelper.getReadableDatabase();

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
        }

        //put movies to the listview
        int itemId;
        ListView lv1 = (ListView)findViewById(R.id.library_listview);
        final List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
        for(int i=0;i<27;i++){ //for data
            if(Data.owned2[i]!=0) { //for cart
                itemId = Data.owned2[i];
                Map<String, Object> listItem = new HashMap<String, Object>();
                listItem.put("filmtitle", Data.filmtitle[itemId-1]);
                listItem.put("filmpic", Data.poster[itemId-1]);
                listItems.add(listItem);
            }
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(MyLibrary.this, listItems,
                R.layout.library_row	,new String[]{"filmtitle","filmpic"},
                new int[]{R.id.library_title,R.id.library_pic});

        lv1.setAdapter(simpleAdapter);
    }

    public static String strSeparator = "__,__";
    public static String[] convertStringToArray(String str){
        String[] arr = str.split(strSeparator);
        return arr;
    }
}
