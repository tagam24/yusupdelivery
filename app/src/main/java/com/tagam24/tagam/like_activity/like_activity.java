package com.tagam24.tagam.like_activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tagam24.tagam.Constants.Constants;
import com.tagam24.tagam.Database.Db;
import com.tagam24.tagam.MainActivity;
import com.tagam24.tagam.R;
import com.tagam24.tagam.cafes_menu.Cafes_menu;
import com.tagam24.tagam.dastawka_recycle;
import com.tagam24.tagam.dil;
import com.tagam24.tagam.gumenje.gumenje_activity;
import com.tagam24.tagam.rate_nahar;
import com.tagam24.tagam.user_info;

import java.util.ArrayList;

public class like_activity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    SectionsPagerAdapter1 sectionsPagerAdapter;
    ImageView cart, liked, home, user, info, location, back;
    TextView counter, title;
    Animation myAnim;
    Context ctx;
    Db db;
    Typeface typebold, typelight, typeregular, typeextrabold;
    dil dd;
public  static Handler s1;
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
        setContentView(R.layout.activity_cafes_menu);
        typebold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Bold.ttf");
        typelight = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Light.ttf");
        typeregular = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Regular.ttf");
        typeextrabold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_ExtraBold.ttf");
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        sectionsPagerAdapter = new SectionsPagerAdapter1(getSupportFragmentManager());
        sectionsPagerAdapter.AddFragment(new cafe_liked());
        sectionsPagerAdapter.AddFragment(new food_liked());
        sectionsPagerAdapter.AddFragment(new cafe_bar_liked());
        viewPager.setAdapter(sectionsPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        rate_nahar.aaa=new ArrayList<>();
        cart = (ImageView) findViewById(R.id.karzina);
        liked = (ImageView) findViewById(R.id.heart);
        home = (ImageView) findViewById(R.id.home);
        counter = (TextView) findViewById(R.id.counter);
        title = (TextView) findViewById(R.id.title);
        counter.setText("" + Constants.cart_list.size());
        user = (ImageView) findViewById(R.id.user);
        info = (ImageView) findViewById(R.id.info);
        location = (ImageView) findViewById(R.id.location);
        back = (ImageView) findViewById(R.id.back);
        title.setTypeface(typebold);
        title.setText(dd.halanlarym);
        info.setVisibility(View.INVISIBLE);
         s1=new Handler(){
             @Override
             public void handleMessage(Message msg) {
                 counter.setText("" + Constants.cart_list.size());
             }
         };
         rate_nahar.idd="";
        Intent i = getIntent();
        if (i.getStringExtra("from").equals("cafe")) {
            tabLayout.getTabAt(0).select();
        } else if (i.getStringExtra("from").equals("food")) {
            tabLayout.getTabAt(1).select();
        } else if (i.getStringExtra("from").equals("beylekiler")) {
            tabLayout.getTabAt(2).select();
        }
        liked.setImageResource(R.drawable.heart_icon);
        liked.setEnabled(false);
        myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.startAnimation(myAnim);
                user.setImageResource(R.drawable.user_icon);

                home.setImageResource(R.drawable.home_white);
                location.setImageResource(R.drawable.map_white);
                liked.setImageResource(R.drawable.heart_white);

                Intent intent = new Intent(like_activity.this, user_info.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);

            }
        });
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.startAnimation(myAnim);
                location.setImageResource(R.drawable.map_icon);

                home.setImageResource(R.drawable.home_white);
                liked.setImageResource(R.drawable.heart_white);
                user.setImageResource(R.drawable.user_white);

            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home.startAnimation(myAnim);
                home.setImageResource(R.drawable.home_icon);

                liked.setImageResource(R.drawable.heart_white);
                location.setImageResource(R.drawable.map_white);
                user.setImageResource(R.drawable.user_white);
                Intent intent = new Intent(like_activity.this, MainActivity.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                finish();

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.startAnimation(myAnim);
                Intent intent=getIntent();
                if(intent.getStringExtra("from").equals("main")){
                    Intent i=new Intent(like_activity.this,MainActivity.class);
                    MainActivity.s2.sendEmptyMessage(1);
                    MainActivity.s4.sendEmptyMessage(1);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                    finish();
                }
                if(intent.getStringExtra("from").equals("cafe")){
                    Intent i=new Intent(like_activity.this,dastawka_recycle.class);
                    dastawka_recycle.s6.sendEmptyMessage(1);
                    overridePendingTransition(R.anim.fade_out,R.anim.fade_in);
                    startActivity(i);
                    finish();
                }
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                finish();
            }
});

    }
    private void changeTabsFont() {
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            Typeface typebold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Bold.ttf");
            Typeface typelight = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Light.ttf");
            Typeface typeregular = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Regular.ttf");
            Typeface typeextrabold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_ExtraBold.ttf");
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(typeregular);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=getIntent();
        if(intent.getStringExtra("from").equals("main")){
           Intent i=new Intent(like_activity.this,MainActivity.class);
            MainActivity.s2.sendEmptyMessage(1);
            MainActivity.s4.sendEmptyMessage(1);
            startActivity(i);
            overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            finish();
        }


        if(intent.getStringExtra("from").equals("cafe")){
          // Intent i=new Intent(like_activity.this,dastawka_recycle.class);
            dastawka_recycle.s6.sendEmptyMessage(1);
        overridePendingTransition(R.anim.fade_out,R.anim.fade_in);
      //      startActivity(i);
            finish();
        }
        if(intent.getStringExtra("from").equals("food")){
           // Intent i=new Intent(like_activity.this,Cafes_menu.class);
            dastawka_recycle.s6.sendEmptyMessage(1);
            overridePendingTransition(R.anim.fade_out,R.anim.fade_in);
          //  startActivity(i);
            finish();
        }
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
        finish();

    }
}
