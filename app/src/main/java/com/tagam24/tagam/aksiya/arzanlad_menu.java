package com.tagam24.tagam.aksiya;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;
import com.tagam24.tagam.Constants.Constants;
import com.tagam24.tagam.Database.Db;
import com.tagam24.tagam.MainActivity;
import com.tagam24.tagam.R;
import com.tagam24.tagam.dastawka_recycle;
import com.tagam24.tagam.dil;
import com.tagam24.tagam.karzina.Karzina;
import com.tagam24.tagam.like_activity.like_activity;
import com.tagam24.tagam.models.Model_Food;
import com.tagam24.tagam.network.get_cafe_show;
import com.tagam24.tagam.network.get_foodAksiya;
import com.tagam24.tagam.network.send_rating;
import com.tagam24.tagam.order.myorder1;
import com.tagam24.tagam.rate_nahar;
import com.tagam24.tagam.user_info;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;


public class arzanlad_menu extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, RatingDialogListener {
    Toolbar toolbar;
    ViewPager viewPager;
    SectionsPagerAdapter1 sectionsPagerAdapter;
    TabLayout tabLayout;
    int color;
    ArrayList<Integer> list_bar, list_cat;
    RecycleAdapter_foodAksiya recycleAdapter;
    public static ArrayList<Model_Food> list;
    Context context;
    SwipeRefreshLayout swipeRefreshLayout;
    LinearLayout lback;
    ImageView cart, liked, home,back,info,user,location;
    TextView counter, title;
    public static Handler s1, s2, s3,s4,s5;
    Db db;
    dil dd;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ctx = this;
        db = new Db(this);
        dd = new dil();
        dd.set_text();
        context = this;
        if (db.get_color().equals("orange")) setTheme(R.style.AppTheme1);
        else if (db.get_color().equals("blue")) setTheme(R.style.AppTheme2);
        else if (db.get_color().equals("green")) setTheme(R.style.AppTheme3);
        else if (db.get_color().equals("pink")) setTheme(R.style.AppTheme4);
        else setTheme(R.style.AppTheme1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafes_menu2);
        Constants.food.clear();
        Constants.iter = true;
        Constants.size = 0;
        get_cafe_show.get_Data();
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(this);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_dostawka_food);
        recycleAdapter = new RecycleAdapter_foodAksiya(Constants.food, context);
        recyclerView.setAdapter(recycleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        list = new ArrayList<>();
        s1 = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                Log.d("s1Handler", "" + Constants.food.size());
                swipeRefreshLayout.setRefreshing(false);
                recycleAdapter.setData(Constants.food);
            }
        };

        s2 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                swipeRefreshLayout.setRefreshing(true);
            }
        };
        s4=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                            send_rate();
            }
        };
        s4=new Handler(){
            @Override
            public void handleMessage(Message msg) {
           counter.setText(""+Constants.cart_list.size());
            }
        };
        get_foodAksiya.get_Data();


        ;
        gui();
        listener();
        s5= new Handler() {
            @Override
            public void handleMessage(Message msg) {
                counter.setText("" + Constants.cart_list.size());
            }
        };
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
      /*  Intent i = new Intent(this, aksiya_recycle.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);*/
      Constants.iter=true;
        finish();
    }

    void gui() {

        cart = (ImageView) findViewById(R.id.karzina);
        liked = (ImageView) findViewById(R.id.like);
        home = (ImageView) findViewById(R.id.home);
        location = (ImageView) findViewById(R.id.location);
        user = (ImageView) findViewById(R.id.user);
        counter = (TextView) findViewById(R.id.counter);
        back=(ImageView)findViewById(R.id.back);
        info=(ImageView)findViewById(R.id.info);
        counter.setText("" + Constants.cart_list.size());
        title = (TextView) findViewById(R.id.title);

        Typeface typebold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Bold.ttf");
        Typeface typelight = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Light.ttf");
        Typeface typeregular = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Regular.ttf");
        Typeface typeextrabold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_ExtraBold.ttf");

        Intent i = getIntent();
        title.setText(i.getStringExtra("name"));
        title.setTypeface(typebold);
    }


    void listener() {
        list_bar=new ArrayList<>();
        list_bar.add(0);
        list_bar.add(0);
        list_bar.add(0);
        list_bar.add(0);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animation myAnim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
                cart.startAnimation(myAnim);
                Intent i = new Intent(arzanlad_menu.this, Karzina.class);
                i.putExtra("from", "menu");
                startActivity(i);
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation myAnim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
                back.startAnimation(myAnim);

                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                finish();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation myAnim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
                home.startAnimation(myAnim);
                if (list_bar.get(0) == 0) {
                    home.setImageResource(R.drawable.home_icon);
                    list_bar.set(0, 1);
                }

                location.setImageResource(R.drawable.map_white);
                liked.setImageResource(R.drawable.heart_white);
                user.setImageResource(R.drawable.user_white);
                Intent i = new Intent(arzanlad_menu.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);

                list_bar.set(1, 0);
                list_bar.set(2, 0);
                list_bar.set(3, 0);
            }
        });

        liked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation myAnim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
                liked.startAnimation(myAnim);
                if (list_bar.get(1) == 0) {
                    liked.setImageResource(R.drawable.heart_icon);
                    list_bar.set(1, 1);
                }

                location.setImageResource(R.drawable.map_white);
                home.setImageResource(R.drawable.home_white);
                user.setImageResource(R.drawable.user_white);

                list_bar.set(0, 0);
                list_bar.set(2, 0);
                list_bar.set(3, 0);
                Intent intent=new Intent(arzanlad_menu.this, like_activity.class);
                intent.putExtra("from","cafe");
                startActivity(intent);
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);

            }

        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation myAnim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
                location.startAnimation(myAnim);
                if (list_bar.get(2) == 0) {
                    location.setImageResource(R.drawable.map_icon);
                    list_bar.set(2, 1);
                }

                liked.setImageResource(R.drawable.heart_white);
                home.setImageResource(R.drawable.home_white);
                user.setImageResource(R.drawable.user_white);

                list_bar.set(1, 0);
                list_bar.set(0, 0);
                list_bar.set(3, 0);

                Intent intent=new Intent(arzanlad_menu.this, myorder1.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);


            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation myAnim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
                user.startAnimation(myAnim);
                if (list_bar.get(1) == 0) {
                    user.setImageResource(R.drawable.user_icon);
                    list_bar.set(1, 1);
                    Intent i = new Intent(arzanlad_menu.this, user_info.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                }

                location.setImageResource(R.drawable.map_white);
                home.setImageResource(R.drawable.home_white);
                liked.setImageResource(R.drawable.heart_white);

                list_bar.set(1, 0);
                list_bar.set(2, 0);
                list_bar.set(0, 0);

            }
        });
    }

    @Override
    public void onRefresh() {
        Constants.food.clear();
        Constants.size = 0;
        Constants.iter = true;
        recycleAdapter.setData(Constants.food);
        get_foodAksiya.get_Data();
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
    public void onNegativeButtonClicked() {

    }

    @Override
    public void onNeutralButtonClicked() {

    }

    @Override
    public void onPositiveButtonClicked(int postion, @NotNull String s) {

        db.inser_Rate( rate_nahar.id);
        send_rating.get_Data( rate_nahar.id, "" + postion, "naharlar");
    }
}

