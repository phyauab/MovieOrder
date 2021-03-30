package com.example.clement.movieorder;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//a class for invoice
public class popPurchase extends AppCompatActivity {

    private MySQLiteHelper sqlHelper;
    private SQLiteDatabase mydb;
    private SQLiteDatabase mydb2;
    private Cursor myCursor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_purchase);

        //toolbar
        Toolbar toolbar = findViewById(R.id.invoice_toolbar);
        toolbar.setTitle("Invoice");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ShoppingCart.class));
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                finish();
            }
        });

        //connect to database
        sqlHelper = new MySQLiteHelper(this);
        mydb = sqlHelper.getReadableDatabase();
        mydb2 = sqlHelper.getWritableDatabase();
        myCursor = mydb.rawQuery("SELECT * FROM client WHERE username = '" + Data.username + "';", null);

        int credit = 0;
        int creditBefore = 0;
        myCursor.moveToFirst();
        if (myCursor.moveToFirst()){
            credit = myCursor.getInt(myCursor.getColumnIndex("credit"));
        }
        creditBefore = credit;
        if(credit>Data.total) {
            credit -= Data.total;
        }
        else
            finish();

        mydb2.execSQL("UPDATE client SET credit = " + credit + " WHERE username = '" + Data.username + "';");

        //listview
        int itemId = 0;
        ListView item_deduct = (ListView)findViewById(R.id.item_deduct);
        final List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
        for(int i=0;i<27;i++){ //for data
            if(Data.cart[i]!=0) { //for cart
                Data.total++;
                itemId = Data.cart[i];
                Map<String, Object> listItem = new HashMap<String, Object>();
                listItem.put("filmtitle", Data.filmtitle[itemId-1]);
                listItem.put("price", Integer.toString(Data.price[i]));
                listItems.add(listItem);
            }
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(popPurchase.this, listItems,
                R.layout.invoice_row	,new String[]{"filmtitle", "price"},
                new int[]{R.id.invoice_title, R.id.invoice_price});
        item_deduct.setAdapter(simpleAdapter);

        TextView credit_before = (TextView)findViewById(R.id.credit_before);
        TextView credit_total = (TextView)findViewById(R.id.credit_total);
        TextView credit_after = (TextView)findViewById(R.id.credit_after);

        credit_before.setText("$" + Integer.toString(creditBefore));
        credit_total.setText("$" + Integer.toString(Data.total));
        credit_after.setText(("$" + Integer.toString(credit)));

        for(int i=0;i<27;i++){
            Data.owned[i] = Integer.toString(Data.cart[i]);
        }
        String owned = "";
        owned = convertArrayToString(Data.owned);

        mydb2.execSQL("INSERT INTO myMovie VALUES ("
                + "'" + Data.username + "'"
                + ", "
                + "'" + owned + "'"
                + ");"
        );

        //reset cart and price
        for(int i=0;i<27;i++){
            Data.price[i] = 0;
            Data.cart[i] = 0;
        }

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

    }

    public static String strSeparator = "__,__";
    public static String convertArrayToString(String[] array){
        String str = "";
        for (int i = 0;i<array.length; i++) {
            str = str+array[i];
            // Do not append comma at the end of last element
            if(i<array.length-1){
                str = str+strSeparator;
            }
        }
        return str;
    }

}
