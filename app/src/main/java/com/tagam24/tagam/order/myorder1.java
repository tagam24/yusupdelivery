package com.tagam24.tagam.order;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.tagam24.tagam.Constants.Constants;
import com.tagam24.tagam.Database.Db;
import com.tagam24.tagam.MainActivity;
import com.tagam24.tagam.R;
import com.tagam24.tagam.dastawka_recycle;
import com.tagam24.tagam.dil;
import com.tagam24.tagam.network.get_myorders;
import com.tagam24.tagam.network.get_user;

import java.util.ArrayList;


public class myorder1 extends AppCompatActivity   implements SwipeRefreshLayout.OnRefreshListener{
    public static ArrayList<Model_myorder1> list;
    public static Handler s1;
    Context ctx;
    Db db;
    dil dd;
    SwipeRefreshLayout swipeRefreshLayout;
     RecycleAdapter_myorder1 recycleAdapter;
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
        setContentView(R.layout.activity_myorder1);

        Typeface typebold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Bold.ttf");
        Typeface typelight = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Light.ttf");
        Typeface typeregular = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Regular.ttf");
        Typeface typeextrabold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_ExtraBold.ttf");

        TextView title=(TextView)findViewById(R.id.title);
        title.setText(dd.menin_zakazym);
        title.setTypeface(typebold);
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        Constants.orderid.clear();
        Constants.iter=true;
        
         recycleAdapter = new RecycleAdapter_myorder1(list,this);
        recyclerView.setAdapter(recycleAdapter);
        s1 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                swipeRefreshLayout.setRefreshing(false);
                recycleAdapter.setData(Constants.orderid);
            }
        };
        Constants.size = 0;
        get_myorders.get_Data();

        ImageView back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=getIntent();
        if (intent.getStringExtra("from").equals("main")){
            MainActivity.s4.sendEmptyMessage(1);
            finish();
        }
        if (intent.getStringExtra("from").equals("cafe")){
            dastawka_recycle.s7.sendEmptyMessage(1);
            finish();
        }
        if (intent.getStringExtra("from").equals("cart")){
//Intent i=new Intent(this,MainActivity.class);
//startActivity(i);
            MainActivity.s4.sendEmptyMessage(1);
            finish();
        }
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
    }

    @Override
    public void onRefresh() {
            Constants.orderid.clear();
             recycleAdapter.setData(Constants.orderid);
             Constants.iter=true;
             Constants.size=0;
            get_myorders.get_Data();
    }
}

