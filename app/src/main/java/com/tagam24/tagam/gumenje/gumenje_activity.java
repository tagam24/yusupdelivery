package com.tagam24.tagam.gumenje;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
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
import com.tagam24.tagam.cafes_menu.fragment_dostawka1;
import com.tagam24.tagam.dil;
import com.tagam24.tagam.liked_gumenje.like_activity_gumenje;
import com.tagam24.tagam.network.get_food;


public class gumenje_activity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    SectionsPagerAdapter2 sectionsPagerAdapter;
    ImageView back, like;
    Animation myAnim;
    TextView title;
    Context ctx;
    Db db;
    dil dd;
    public static Handler fin;

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
        setContentView(R.layout.activity_main3);
        Typeface typebold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Bold.ttf");
        Typeface typelight = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Light.ttf");
        Typeface typeregular = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Regular.ttf");
        Typeface typeextrabold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_ExtraBold.ttf");
        viewPager = (ViewPager) findViewById(R.id.dostawka_viewpager);
        sectionsPagerAdapter = new SectionsPagerAdapter2(getSupportFragmentManager());
        sectionsPagerAdapter.AddFragment(new Reciept());
        sectionsPagerAdapter.AddFragment(new football());

        viewPager.setAdapter(sectionsPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.like_cafe_tab);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        Intent i=getIntent();
        tabLayout.getTabAt(Integer.parseInt(i.getStringExtra("tab"))).select();


        myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);

        like = (ImageView) findViewById(R.id.like);
        back = (ImageView) findViewById(R.id.back);
        title = (TextView) findViewById(R.id.title);
        title.setText(dd.guymenje1);
        title.setTypeface(typebold);


        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                like.startAnimation(myAnim);
                like.setImageResource(R.drawable.heart_icon);
                Intent i = new Intent(gumenje_activity.this, like_activity_gumenje.class);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            }
        });
        changeTabsFont();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.startAnimation(myAnim);

                finish();
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Constants.iter = true;

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void changeTabsFont() {
        tabLayout = (TabLayout) findViewById(R.id.like_cafe_tab);
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

        finish();
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
    }
}
