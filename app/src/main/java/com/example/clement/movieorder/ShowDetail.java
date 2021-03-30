package com.example.clement.movieorder;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubePlayer;

public class ShowDetail extends AppCompatActivity {

    private MySQLiteHelper sqlHelper;
    private SQLiteDatabase mydb;
    private Cursor myCursor;

    //View Element
    ImageView backpic;
    ImageView moviepic;
    ImageView poster;
    TextView detail_title;
    TextView detail_director;
    TextView detail_writer;
    TextView detail_type;
    TextView detail_star;
    Button add;
    TextView detail_plot;
    TextView detail_duration;
    TextView detail_rating;
    Button addtocart;

    int sid;
    String url = "";
    int price = 0;
    String price2 = "";
    String price3 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //bundle
        Bundle bundle = getIntent().getExtras();
        long bid = bundle.getLong("rowId");
        sid = (int) bid;
        sid++;

        //connect to db
        sqlHelper = new MySQLiteHelper(this);
        mydb = sqlHelper.getReadableDatabase();
        myCursor = mydb.rawQuery("SELECT * FROM film WHERE _id = " + sid, null);


        //set view
        backpic = findViewById(R.id.backpic);
        moviepic = findViewById(R.id.poster);
        detail_title = findViewById(R.id.detail_title);
        detail_director = findViewById(R.id.detail_director);
        detail_writer = findViewById(R.id.detail_writer);
        detail_type = findViewById(R.id.detail_type);
        detail_star = findViewById(R.id.detail_star);
        poster = findViewById(R.id.poster);
        detail_plot = findViewById(R.id.detail_plot);
        detail_rating = findViewById(R.id.detail_rating);
        detail_duration = findViewById(R.id.detail_duration);
        addtocart = findViewById(R.id.add_to_cart);


        String plot = "";
        String title = "", director = "", writer = "", star = "", type = "", mins = "", year2, rating2;
        int year = 0, rating = 0;

        //get to data needed
        myCursor.moveToFirst();
        if (myCursor.moveToFirst()){
                title = myCursor.getString(myCursor.getColumnIndex("title"));
                director += myCursor.getString(myCursor.getColumnIndex("director"));
                writer += myCursor.getString(myCursor.getColumnIndex("writer"));
                star += myCursor.getString(myCursor.getColumnIndex("stars"));
                type += myCursor.getString(myCursor.getColumnIndex("types"));
                mins += myCursor.getString(myCursor.getColumnIndex("mins"));
                year = myCursor.getInt(myCursor.getColumnIndex("year"));
                price = myCursor.getInt(myCursor.getColumnIndex("price"));
                url = myCursor.getString(myCursor.getColumnIndex("url"));
                plot += myCursor.getString(myCursor.getColumnIndex("plot"));
                rating = myCursor.getInt(myCursor.getColumnIndex("rating"));
        }
        year2 = " (" + Integer.toString(year) + ")";
        title += year2;
        rating2 = " " + Integer.toString(rating) + "/10";
        price2 = "Add to cart ($" + Integer.toString(price) + ")";
        price3 = "HKD " + Integer.toString(price) + ".00";
        //set text
        detail_title.setText(title);
        detail_director.setText(director);
        detail_writer.setText(writer);
        detail_type.setText(type);
        detail_star.setText(star);
        detail_plot.setText(plot);
        detail_duration.setText(mins);
        detail_rating.setText(rating2);
        addtocart.setText(price2);

        backpic.setImageResource(Data.filmpic[sid-1]);
        poster.setImageResource(Data.poster[sid-1]);
    }

    public void WatchTrailer(View v){
        Intent intent = new Intent();
        intent.setClass(ShowDetail.this, popPlayer.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void AddToCart(View v){
        for(int i=0;i<27;i++)
            if(Data.cart[i]==0) {
                Data.cart[i] = sid;
                Data.price[i] = price;
                Toast.makeText(getApplicationContext(),"Item Added", Toast.LENGTH_SHORT).show();
                break;
            }
    }
}
