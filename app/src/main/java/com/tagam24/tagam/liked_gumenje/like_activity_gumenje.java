package com.tagam24.tagam.liked_gumenje;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.tagam24.tagam.Database.Db;
import com.tagam24.tagam.R;
import com.tagam24.tagam.cafes_menu.Cafes_menu;
import com.tagam24.tagam.dastawka_recycle;
import com.tagam24.tagam.dil;
import com.tagam24.tagam.gumenje.gumenje_activity;
import com.tagam24.tagam.network.get_user;

import org.w3c.dom.Text;


public class like_activity_gumenje extends AppCompatActivity {
TabLayout tabLayout;
    ViewPager viewPager;
    SectionsPagerAdapter3 sectionsPagerAdapter;
    Animation myAnim;
    ImageView back,info;
    TextView title;
    Db db;
    dil dd;
    Context ctx;
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
        setContentView(R.layout.activity_cafes_menu);
        Typeface typebold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Bold.ttf");
        Typeface typelight = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Light.ttf");
        Typeface typeregular = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Regular.ttf");
        Typeface typeextrabold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_ExtraBold.ttf");
        RelativeLayout ashaky=(RelativeLayout)findViewById(R.id.ashaky);
        ashaky.setVisibility(View.GONE);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        title=(TextView)findViewById(R.id.title);
        info=(ImageView)findViewById(R.id.info);
        info.setVisibility(View.GONE);
        title.setText(dd.halanlarym);
        title.setTypeface(typebold);
        sectionsPagerAdapter = new SectionsPagerAdapter3(getSupportFragmentManager());
        sectionsPagerAdapter.AddFragment(new footbol_liked());
        sectionsPagerAdapter.AddFragment(new nahar_liked());
        viewPager.setAdapter(sectionsPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        back = (ImageView)findViewById(R.id.back);
        myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.startAnimation(myAnim);
                Intent intent;
                intent = new Intent(like_activity_gumenje.this, gumenje_activity.class);
                intent.putExtra("tab","0");
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);


            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(this, gumenje_activity.class);
        i.putExtra("tab","0");
        startActivity(i);
        finish();
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
    }
}
