package com.example.clement.movieorder;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.view.View.OnClickListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart extends AppCompatActivity{

    private MySQLiteHelper sqlHelper;
    private SQLiteDatabase mydb;
    private SQLiteDatabase mydb2;
    private String[] allColumns;
    private Cursor myCursor;
    private SimpleCursorAdapter dbAdapter;
    Button delete, purchase;
    ListView lv1;
    final List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
    int total = 0;
    String purchase2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart_content);

        Toolbar toolbar = findViewById(R.id.shopping_cart_toolbar);
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
        //code
        int itemId;
        lv1 = (ListView)findViewById(R.id.shopping_cart_movie);
        purchase = findViewById(R.id.btn_purchase);
        for(int i=0;i<27;i++){ //for data
            if(Data.cart[i]!=0) { //for cart
                itemId = Data.cart[i];
                Map<String, Object> listItem = new HashMap<String, Object>();
                listItem.put("filmtitle", Data.filmtitle[itemId-1]);
                listItem.put("filmpic", Data.poster[itemId-1]);
                listItem.put("price", Integer.toString(Data.price[i]));
                listItems.add(listItem);
            }
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(ShoppingCart.this, listItems,
                R.layout.shopping_cart_detail	,new String[]{"filmtitle","filmpic","price"},
                new int[]{R.id.cart_title,R.id.cart_poster,R.id.cart_price});


        delete = (Button)findViewById(R.id.btn_delete);
        OnClickListener listenerDel = new OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=(Integer)v.getTag();
                listItems.remove(position);
            }
        };
        lv1.setAdapter(simpleAdapter);

        //show total
        for(int i=0;i<27;i++)
        {
            total += Data.price[i];
        }

        purchase2 = "Purchase ($" + total + ")";
        purchase.setText(purchase2);
    }

    public void delete(View v){

        final int position = lv1.getPositionForView((View) v.getParent());
        listItems.remove(position);
        pushZerosToEnd(Data.cart,27);
        pushZerosToEnd(Data.price,27);
        Data.cart[position] = 0;
        Data.price[position] = 0;

        Toast.makeText(getApplicationContext(),"Item Deleted", Toast.LENGTH_SHORT).show();
        finish();
        startActivity(getIntent());
    }

    //method to help ordering cart
    static void pushZerosToEnd(int arr[], int n)
    {
        int count = 0;  // Count of non-zero elements

        for (int i = 0; i < n; i++)
            if (arr[i] != 0)
                arr[count++] = arr[i]; // here count is

        while (count < n)
            arr[count++] = 0;
    }

    public void purchase(View v){

        sqlHelper = new MySQLiteHelper(this);
        mydb = sqlHelper.getReadableDatabase();
        //myCursor = mydb.rawQuery("SELECT * FROM film WHERE _id = " + sid, null);

        //compute total
        int total = 0;
        for(int i=0;i<27;i++) {
            if(Data.price[i]!=0){
                total+=Data.price[i];
            }
        }
        Button purchase = (Button)findViewById(R.id.btn_purchase);
        purchase.setText("Purchase ($" + total + ")");
        Data.total = total;
        finish();
        startActivity(new Intent(ShoppingCart.this, popPurchase.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}
