package com.tagam24.tagam.cafes_menu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;
import com.tagam24.tagam.Constants.Constants;
import com.tagam24.tagam.Database.Db;
import com.tagam24.tagam.MainActivity;
import com.tagam24.tagam.R;
import com.tagam24.tagam.cafe_bar.show_details_bars;
import com.tagam24.tagam.dastawka_recycle;
import com.tagam24.tagam.dil;
import com.tagam24.tagam.karzina.Karzina;
import com.tagam24.tagam.like_activity.food_liked;
import com.tagam24.tagam.like_activity.like_activity;
import com.tagam24.tagam.liked_gumenje.nahar_liked;
import com.tagam24.tagam.models.Model_Cafe;
import com.tagam24.tagam.models.Model_Cafe_show;
import com.tagam24.tagam.network.get_cafe;
import com.tagam24.tagam.network.get_cafe_show;
import com.tagam24.tagam.network.get_food;
import com.tagam24.tagam.network.send_rating;
import com.tagam24.tagam.order.myorder1;
import com.tagam24.tagam.rate_nahar;
import com.tagam24.tagam.user_info;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class Cafes_menu extends AppCompatActivity implements RatingDialogListener {
    Toolbar toolbar;
    ViewPager viewPager;
    SectionsPagerAdapter sectionsPagerAdapter;
    TabLayout tabLayout;
    LinearLayout lback;
    ImageView cart, liked, home, user, info, location, back;
    TextView counter, title;
    Typeface typebold, typelight, typeregular, typeextrabold;
    public static Handler s2, s1,s3,s6;
    Db db;
    dil dd;
    ArrayList<Model_Cafe_show> list1;
    Intent ii;
    Context ctx;
    Animation myAnim;
    public static String[] categories = new String[1000];

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
        Constants.food.clear();
        Constants.iter=true;
        Constants.idfd.clear();
        ii = new Intent();
        ii = getIntent();
        s1 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                counter.setText("" + Constants.cart_list.size());
            }
        };
        s2 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                send_rate();
            }
        };
        s3 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                finish();

            }
        };
        s6 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                nlike();
            }
        };
        list1 = new ArrayList<Model_Cafe_show>();
        //Tabs
        get_cafe_show.get_Data();
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        categories = Constants.cafesel.getCategory().split("-");
        if (categories.length >= 1)
            sectionsPagerAdapter.AddFragment(new fragment_dostawka1(), categories[0]);
        if (categories.length >= 2)
            sectionsPagerAdapter.AddFragment(new fragment_dostawka2(), categories[1]);
        if (categories.length >= 3)
            sectionsPagerAdapter.AddFragment(new fragment_dostawka3(), categories[2]);
        if (categories.length >= 4)
            sectionsPagerAdapter.AddFragment(new fragment_dostawka4(), categories[3]);
        if (categories.length >= 5)
            sectionsPagerAdapter.AddFragment(new fragment_dostawka5(), categories[4]);
        if (categories.length >= 6)
            sectionsPagerAdapter.AddFragment(new fragment_dostawka6(), categories[5]);
        if (categories.length >= 7)
            sectionsPagerAdapter.AddFragment(new fragment_dostawka7(), categories[6]);
        if (categories.length >= 8)
            sectionsPagerAdapter.AddFragment(new fragment_dostawka8(), categories[7]);
        if (categories.length >= 9)
            sectionsPagerAdapter.AddFragment(new fragment_dostawka9(), categories[8]);
        if (categories.length >= 10)
            sectionsPagerAdapter.AddFragment(new fragment_dostawka10(), categories[9]);
        if (categories.length >= 11)
            sectionsPagerAdapter.AddFragment(new fragment_dostawka11(), categories[10]);
        if (categories.length >= 12)
            sectionsPagerAdapter.AddFragment(new fragment_dostawka12(), categories[11]);
        if (categories.length >= 13)
            sectionsPagerAdapter.AddFragment(new fragment_dostawka13(), categories[12]);
        if (categories.length >= 14)
            sectionsPagerAdapter.AddFragment(new fragment_dostawka14(), categories[13]);
        if (categories.length >= 15)
            sectionsPagerAdapter.AddFragment(new fragment_dostawka15(), categories[14]);
        if (categories.length >= 16)
            sectionsPagerAdapter.AddFragment(new fragment_dostawka16(), categories[15]);
        if (categories.length >= 17)
            sectionsPagerAdapter.AddFragment(new fragment_dostawka17(), categories[16]);
        if (categories.length >= 18)
            sectionsPagerAdapter.AddFragment(new fragment_dostawka18(), categories[17]);
        if (categories.length >= 19)
            sectionsPagerAdapter.AddFragment(new fragment_dostawka19(), categories[18]);
        if (categories.length >= 20)
            sectionsPagerAdapter.AddFragment(new fragment_dostawka20(), categories[19]);


        viewPager.setAdapter(sectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        Constants.categoryFood = categories[0];

        tabLayout.setupWithViewPager(viewPager);

        // get_food.get_Data();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Constants.categoryFood = categories[tab.getPosition()];
                Constants.food.clear();
                Constants.size = 0;
                Constants.iter = true;
                Constants.idfd.clear();
                //      fragment_dostawka1.s2.sendEmptyMessage(1);
                //     fragment_dostawka1.s1.sendEmptyMessage(1);
                get_food.get_Data();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        if (tabLayout.getTabCount() > 4) {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            tabLayout.setTabGravity(Gravity.FILL);
        } else {
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
            tabLayout.setTabGravity(Gravity.FILL);
        }
        changeTabsFont();
        gui();
        listener();
        get_food.get_Data();
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
        Constants.iter = true;

        if(dastawka_recycle.s4!=null)
            dastawka_recycle.s4.sendEmptyMessage(4);

/*
        Intent i=new Intent(this,dastawka_recycle.class);
        startActivity(i);
    */
        Intent intent=getIntent();
//        if(intent.getStringExtra("from").equals("cafe"))
      /*  {Intent i=new Intent(Cafes_menu.this,dastawka_recycle.class);
            startActivity(i);
            overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            finish();}*/
        finish();
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
    }

    void gui() {
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


        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/font8.ttf");
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), "fonts/font5.ttf");


        title.setTypeface(typebold);
        title.setText(Constants.cafesel.getName());
    }

    void listener() {
        myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart.startAnimation(myAnim);
                Intent i = new Intent(Cafes_menu.this, Karzina.class);
                i.putExtra("from", "menu");
                startActivity(i);


                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            }
        });
        liked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liked.startAnimation(myAnim);
                liked.setImageResource(R.drawable.heart_icon);
                home.setImageResource(R.drawable.home_white);
                location.setImageResource(R.drawable.map_white);
                user.setImageResource(R.drawable.user_white);
                Intent i = new Intent(Cafes_menu.this, like_activity.class);
                i.putExtra("from", "food");
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.startAnimation(myAnim);
                user.setImageResource(R.drawable.user_icon);
                home.setImageResource(R.drawable.home_white);
                location.setImageResource(R.drawable.map_white);
                liked.setImageResource(R.drawable.heart_white);
                Intent intent = new Intent(Cafes_menu.this, user_info.class);
                intent.putExtra("from","food");
                startActivity(intent);
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);

            }
        });
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Cafes_menu.this, myorder1.class);
                i.putExtra("from","cafe");
                startActivity(i);
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
                dastawka_recycle.s3.sendEmptyMessage(1);
                MainActivity.s5.sendEmptyMessage(1);
                Intent intent = new Intent(Cafes_menu.this, MainActivity.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                finish();

                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);

            }
        });


        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.startAnimation(myAnim);
                Intent intent = new Intent(Cafes_menu.this, cafe_show.class);
                /*get_cafe_show gg = new get_cafe_show();
                gg.get_Data();*/

                Intent i = new Intent(Cafes_menu.this, show_details_bars.class);
                i.putExtra("from", "cafee");
                i.putExtra("id", rate_nahar.aaa.get(0));
                i.putExtra("image", rate_nahar.aaa.get(1));
                i.putExtra("name", rate_nahar.aaa.get(2));
                i.putExtra("address", rate_nahar.aaa.get(3));
                i.putExtra("rating", rate_nahar.aaa.get(4));
                i.putExtra("watch", rate_nahar.aaa.get(5));
                i.putExtra("n_people", rate_nahar.aaa.get(6));
                i.putExtra("work_time", rate_nahar.aaa.get(7));
                i.putExtra("delivery_price", rate_nahar.aaa.get(8));
                i.putExtra("content", rate_nahar.aaa.get(9));
                i.putExtra("min_order", rate_nahar.aaa.get(10));
                i.putExtra("karta_image", rate_nahar.aaa.get(11));
                i.putExtra("work_data", rate_nahar.aaa.get(12));
                i.putExtra("s_rating", rate_nahar.aaa.get(13));
                i.putExtra("number", rate_nahar.aaa.get(14));
                i.putExtra("category", rate_nahar.aaa.get(15));
                startActivity(i);
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.startAnimation(myAnim);
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                Intent intent=getIntent();
                if(intent.getStringExtra("from").equals("cafe"))
                {Intent i=new Intent(Cafes_menu.this,dastawka_recycle.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                finish();
                }
                finish();

            }
        });
    }
    void nlike() {
        liked.setImageResource(R.drawable.heart_bos);
    }

    public void send_rate() {
        AppRatingDialog.Builder appRatingDialog = new AppRatingDialog.Builder();
        appRatingDialog.setPositiveButtonText(dil.tassykla);
        appRatingDialog.setNegativeButtonText(dil.yza);
        appRatingDialog.setNoteDescriptions(Arrays.asList(dil.erbet, dil.kanagatlanarly, dil.gowy, dil.oran_gowy, dil.ajayyp));
        appRatingDialog.setDefaultRating(2);
        appRatingDialog.setTitle(dil.tagamy_bahalndyryn);
        appRatingDialog.setDescription(dil.mynasyp_baha_bermeginizi_hayys);
        appRatingDialog.setStarColor(R.color.colorPrimary1);
        appRatingDialog.setNoteDescriptionTextColor(R.color.black2);
        appRatingDialog.setTitleTextColor(R.color.black2);
        appRatingDialog.setCommentInputEnabled(false);
        appRatingDialog.setDescriptionTextColor(R.color.black2);
        appRatingDialog.setWindowAnimation(R.style.MyDialogFadeAnimation);
        appRatingDialog.setCancelable(false);
        appRatingDialog.setCanceledOnTouchOutside(false);
        appRatingDialog.create((FragmentActivity) this).show();
    }

    @Override
    public void onPositiveButtonClicked(int postion, @NotNull String s) {

        db.inser_Rate(rate_nahar.id);
        send_rating.get_Data(rate_nahar.id, "" + postion, "naharlar");
    }

    @Override
    public void onNegativeButtonClicked() {

    }

    @Override
    public void onNeutralButtonClicked() {

    }


}
