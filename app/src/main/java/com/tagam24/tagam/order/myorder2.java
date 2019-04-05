package com.tagam24.tagam.order;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.tagam24.tagam.Constants.Constants;
import com.tagam24.tagam.Database.Db;
import com.tagam24.tagam.MainActivity;
import com.tagam24.tagam.R;
import com.tagam24.tagam.dil;
import com.tagam24.tagam.network.get_myorders;
import com.tagam24.tagam.network.get_myorders_item;

import java.util.ArrayList;


public class myorder2 extends AppCompatActivity  implements SwipeRefreshLayout.OnRefreshListener{
    public static ArrayList<Model_myorder2> list;
    public static Handler s1;
    Db db;
    dil dd;
    RecyclerView recyclerView;
    Context ctx;
    SwipeRefreshLayout swipeRefreshLayout;
     RecycleAdapter_myorder2 recycleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ctx = this;
        db = new Db(this);
        dd = new dil();
        dd.set_text();

        if (db.get_color().equals("orange")) setTheme(R.style.AppTheme1);
        else if (db.get_color().equals("blue")) setTheme(R.style.AppTheme2);
        else if (db.get_color().equals("green")) setTheme(R.style.AppTheme3);
        else if (db.get_color().equals("pink")) setTheme(R.style.AppTheme4);
        else {
            setTheme(R.style.AppTheme1);
            db.inser_color("orange");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder2);
        Typeface typebold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Bold.ttf");
        Typeface typelight = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Light.ttf");
        Typeface typeregular = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Regular.ttf");
        Typeface typeextrabold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_ExtraBold.ttf");
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(this);
        TextView textView=(TextView)findViewById(R.id.title);
        textView.setText(dd.menin_zakazym);
        textView.setTypeface(typebold);

        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();
         recycleAdapter = new RecycleAdapter_myorder2(list, getApplicationContext());
        recyclerView.setAdapter(recycleAdapter);
        s1 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                swipeRefreshLayout.setRefreshing(false);
                recycleAdapter.setData(Constants.myorder);
            }
        };
        Constants.size = 0;
        Constants.myorder.clear();
        Constants.iter = true;
        get_myorders_item.get_Data();
        ImageView back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(myorder2.this, myorder1.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
    }

    @Override
    public void onRefresh() {
        Constants.myorder.clear();
        recycleAdapter.setData(Constants.myorder);
        Constants.iter=true;
        Constants.size=0;
        get_myorders_item.get_Data();
    }
}

