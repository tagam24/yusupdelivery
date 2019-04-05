package com.tagam24.tagam.karzina;

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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tagam24.tagam.Constants.Constants;
import com.tagam24.tagam.Database.Db;
import com.tagam24.tagam.MainActivity;
import com.tagam24.tagam.R;
import com.tagam24.tagam.Registration1;
import com.tagam24.tagam.aksiya.aksiya_recycle;
import com.tagam24.tagam.aksiya.arzanlad_menu;
import com.tagam24.tagam.cafes_menu.Cafes_menu;
import com.tagam24.tagam.cafes_menu.fragment_dostawka1;
import com.tagam24.tagam.cafes_menu.fragment_dostawka10;
import com.tagam24.tagam.cafes_menu.fragment_dostawka11;
import com.tagam24.tagam.cafes_menu.fragment_dostawka12;
import com.tagam24.tagam.cafes_menu.fragment_dostawka13;
import com.tagam24.tagam.cafes_menu.fragment_dostawka14;
import com.tagam24.tagam.cafes_menu.fragment_dostawka15;
import com.tagam24.tagam.cafes_menu.fragment_dostawka16;
import com.tagam24.tagam.cafes_menu.fragment_dostawka17;
import com.tagam24.tagam.cafes_menu.fragment_dostawka18;
import com.tagam24.tagam.cafes_menu.fragment_dostawka19;
import com.tagam24.tagam.cafes_menu.fragment_dostawka2;
import com.tagam24.tagam.cafes_menu.fragment_dostawka20;
import com.tagam24.tagam.cafes_menu.fragment_dostawka3;
import com.tagam24.tagam.cafes_menu.fragment_dostawka4;
import com.tagam24.tagam.cafes_menu.fragment_dostawka5;
import com.tagam24.tagam.cafes_menu.fragment_dostawka6;
import com.tagam24.tagam.cafes_menu.fragment_dostawka7;
import com.tagam24.tagam.cafes_menu.fragment_dostawka8;
import com.tagam24.tagam.cafes_menu.fragment_dostawka9;
import com.tagam24.tagam.dastawka_recycle;
import com.tagam24.tagam.dil;
import com.tagam24.tagam.network.Api;
import com.tagam24.tagam.network.get_foodKarzina;
import com.tagam24.tagam.network.send_orders;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tagam24.tagam.rate_nahar;
import com.tagam24.tagam.send_karzina;

public class Karzina extends AppCompatActivity {
    RecyclerView rv;
    SwipeRefreshLayout swipeRefreshLayout;
    RecycleAdapter_karzina recycleAdapter;
    MaterialRippleLayout send;
    TextView cafe_name, tt_price, dostawka_price, eltip_berme, zakas_tassykla, title, gyzyklandyr;
    RoundedImageView image;
    RecycleAdapter_foodKarzina adapter;
    public static Handler s1, s3,s4;
    String im;
    Intent i;
    Db db;
    ImageView back, info;
    dil dd;
    Typeface typebold, typelight, typeregular, typeextrabold;
    Animation myAnim;
    Context ctx;
    Float aaa;
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
        else setTheme(R.style.AppTheme1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karzina);
        typebold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Bold.ttf");
        typelight = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Light.ttf");
        typeregular = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Regular.ttf");
        typeextrabold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_ExtraBold.ttf");
        //SDK VERSION
        myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        send = (MaterialRippleLayout) findViewById(R.id.send);
        cafe_name = (TextView) findViewById(R.id.cafe_name);
        image = (RoundedImageView) findViewById(R.id.image);
        tt_price = (TextView) findViewById(R.id.tt_price);
        if(!(rate_nahar.aaa==null))
         aaa = Constants.tt_price + Float.parseFloat(rate_nahar.aaa.get(8));
        if(!(rate_nahar.aaa==null))  tt_price.setText("" + aaa + " " + dil.manat); else tt_price.setText( " " + dil.manat);
        tt_price.setTypeface(typeregular);

        dostawka_price = (TextView) findViewById(R.id.dostawka_price);
        if(!(rate_nahar.aaa==null)) dostawka_price.setText(rate_nahar.aaa.get(8) + " " + dil.manat); else dostawka_price.setText(" " + dil.manat);
        dostawka_price.setTypeface(typeregular);
        cafe_name.setText(Constants.cafesel.getName());
        info = (ImageView) findViewById(R.id.info);
        back = (ImageView) findViewById(R.id.back);
        cafe_name.setTypeface(typebold);
        zakas_tassykla = (TextView) findViewById(R.id.zakaz);
        eltip_berme = (TextView) findViewById(R.id.t_eltipberme);
        title = (TextView) findViewById(R.id.title);
        zakas_tassykla.setText(dil.zakazy_tassykla);
        title.setText(dil.karzina);
        eltip_berme.setText(dil.eltip_bermek);
        gyzyklandyr = (TextView) findViewById(R.id.gyzyklandyr);
        gyzyklandyr.setText(dil.gyzyklandyrar);
        gyzyklandyr.setTypeface(typebold);


        zakas_tassykla.setTypeface(typeregular);
        title.setTypeface(typebold);
        eltip_berme.setTypeface(typeregular);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.startAnimation(myAnim);
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                finish();
            }
        });

        i = getIntent();
        if (Constants.cafesel.getLogo().equals("")) im = Constants.cafesel.getImage();
        else im = Constants.cafesel.getLogo();
        Glide.with(this)
                .load("http://" + Api.url + "tagam24/images/" + im).asBitmap().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(image);

 send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(Constants.cart_list.size()==0)){
                Float A = Constants.tt_price+Integer.parseInt(rate_nahar.aaa.get(8));
                Float B = Float.parseFloat(rate_nahar.aaa.get(10));

                if (B > A) {
                    Toast.makeText(Karzina.this, dd.min_zakaz + " " + B + " " + dd.manat, Toast.LENGTH_LONG).show();
                } else {

                    Intent i;
                    if (Constants.user == null) {
                        i = new Intent(Karzina.this, Registration1.class);
                        i.putExtra("from", "cart");
                    } else i = new Intent(Karzina.this, send_karzina.class);
                    startActivity(i);

                }}else  Toast.makeText(Karzina.this,"asdsad",Toast.LENGTH_LONG).show();
            }
        });
        rv = (RecyclerView) findViewById(R.id.cart_recycle);
        recycleAdapter = new RecycleAdapter_karzina(Constants.cart_list, this);
        rv.setAdapter(recycleAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RecycleAdapter_foodKarzina(Constants.cart_food, this);
        s1 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Float aaa = Constants.tt_price + Float.parseFloat(rate_nahar.aaa.get(8));
                tt_price.setText("" + aaa + " " + dil.manat);
                tt_price.setTypeface(typeregular);
                recycleAdapter.setData(Constants.cart_list);

            }
        };
        RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.recycle3);
        recyclerView2.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(layoutManager);
        recyclerView2.setAdapter(adapter);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.startAnimation(myAnim);
                Constants.cart_list.clear();
                Constants.cart_food.clear();
                adapter.setData(Constants.cart_food);
if(Cafes_menu.s1!=null)                Cafes_menu.s1.sendEmptyMessage(1);
if(arzanlad_menu.s5!=null)arzanlad_menu.s5.sendEmptyMessage(1);
                Constants.ids.clear();
                Constants.tt_price=0;
                tt_price.setText("" + "0" + " " + dil.manat);
                recycleAdapter.notifyDataSetChanged();
            }
        });
        s3 = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                adapter.setData(Constants.cart_food);
            }
        };
        s4 = new Handler() {
            @Override
            public void handleMessage(Message msg) {

           finish();
            }
        };
        get_foodKarzina.get_Data();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Constants.cart_food.clear();
        adapter.setData(Constants.cart_food);
        if(arzanlad_menu.s1!=null) arzanlad_menu.s1.sendEmptyMessage(1);
        if(arzanlad_menu.s5!=null)arzanlad_menu.s5.sendEmptyMessage(1);
        if(Cafes_menu.s1!=null){Cafes_menu.s1.sendEmptyMessage(1);

            if (Cafes_menu.categories.length >= 1 && Cafes_menu.categories[0].equals(Constants.categoryFood))
                fragment_dostawka1.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 2 && Cafes_menu.categories[1].equals(Constants.categoryFood))
                fragment_dostawka2.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 3 && Cafes_menu.categories[2].equals(Constants.categoryFood))

                fragment_dostawka3.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 4 && Cafes_menu.categories[3].equals(Constants.categoryFood))
                fragment_dostawka4.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 5 && Cafes_menu.categories[4].equals(Constants.categoryFood))
                fragment_dostawka5.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 6 && Cafes_menu.categories[5].equals(Constants.categoryFood))
                fragment_dostawka6.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 7 && Cafes_menu.categories[6].equals(Constants.categoryFood))
                fragment_dostawka7.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 8 && Cafes_menu.categories[7].equals(Constants.categoryFood))
                fragment_dostawka8.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 9 && Cafes_menu.categories[8].equals(Constants.categoryFood))
                fragment_dostawka9.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 10 && Cafes_menu.categories[9].equals(Constants.categoryFood))
                fragment_dostawka10.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 11 && Cafes_menu.categories[10].equals(Constants.categoryFood))
                fragment_dostawka11.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 12 && Cafes_menu.categories[11].equals(Constants.categoryFood))
                fragment_dostawka12.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 13 && Cafes_menu.categories[12].equals(Constants.categoryFood))
                fragment_dostawka13.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 14 && Cafes_menu.categories[13].equals(Constants.categoryFood))
                fragment_dostawka14.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 15 && Cafes_menu.categories[14].equals(Constants.categoryFood))
                fragment_dostawka15.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 16 && Cafes_menu.categories[15].equals(Constants.categoryFood))
                fragment_dostawka16.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 17 && Cafes_menu.categories[16].equals(Constants.categoryFood))
                fragment_dostawka17.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 18 && Cafes_menu.categories[17].equals(Constants.categoryFood))
                fragment_dostawka18.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 19 && Cafes_menu.categories[18].equals(Constants.categoryFood))
                fragment_dostawka19.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 20 && Cafes_menu.categories[19].equals(Constants.categoryFood))
                fragment_dostawka20.s1.sendEmptyMessage(1);}
        if(dastawka_recycle.s4!=null)dastawka_recycle.s4.sendEmptyMessage(1);
        if(aksiya_recycle.s4!=null)aksiya_recycle.s4.sendEmptyMessage(1);
        if(MainActivity.s2!=null)MainActivity.s2.sendEmptyMessage(1);
        overridePendingTransition(R.anim.fade_out,R.anim.fade_in);
        finish();

    }
}

