package com.tagam24.tagam.aksiya;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tagam24.tagam.Constants.Constants;
import com.tagam24.tagam.Database.Db;
import com.tagam24.tagam.MainActivity;
import com.tagam24.tagam.R;
import com.tagam24.tagam.cafes_menu.Cafes_menu;
import com.tagam24.tagam.dil;
import com.tagam24.tagam.karzina.Karzina;
import com.tagam24.tagam.like_activity.like_activity;
import com.tagam24.tagam.models.Model_Cafe;
import com.tagam24.tagam.network.get_cafeAksiya;
import com.tagam24.tagam.network.get_cafe_show;
import com.tagam24.tagam.network.search_cafe;
import com.tagam24.tagam.order.myorder1;
import com.tagam24.tagam.user_info;

import java.util.ArrayList;

public class aksiya_recycle extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    static public String data;
    public static ArrayList<Model_Cafe> list;
    public static ArrayList<Integer> num;
    public static Handler s1, s2, s3, s4;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView count;
    Animation myAnim;
    Typeface typebold, typelight, typeregular, typeextrabold;
    ArrayList<Integer> list_bar;
    RecycleAdapter_aksiyacafe recycleAdapter;
    EditText search;
    ImageView imageView_home, imageView_favorite, imageView_location, imageView_user, imageView_karzina, clear;
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
        else setTheme(R.style.AppTheme1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aksiya_recycle2);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(this);
        search = (EditText) findViewById(R.id.serach);
        Constants.cafe.clear();
        count = (TextView) findViewById(R.id.counter);
        myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        Constants.cafe.clear();
        Constants.iter = true;
        Constants.size = 0;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list = new ArrayList<>();
        num = new ArrayList<>();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        list_bar = new ArrayList<>();
        EditText editText = (EditText) findViewById(R.id.serach);
        typebold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Bold.ttf");
        typelight = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Light.ttf");
        typeregular = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Regular.ttf");
        typeextrabold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_ExtraBold.ttf");
        //SDK VERSION

        clear = (ImageView) findViewById(R.id.clear);
        search = (EditText) findViewById(R.id.serach);
        search.setHint(dil.Gozleg);
        search.setTypeface(typeregular);
        clear = (ImageView) findViewById(R.id.clear);
        clear.setVisibility(View.INVISIBLE);
        search.setCursorVisible(false);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setCursorVisible(true);
                clear.setVisibility(View.VISIBLE);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (search.getText().toString().equals("")) {
                    ((InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(search.getWindowToken(), 0);
                    clear.setVisibility(View.INVISIBLE);
                    search.setCursorVisible(false);
                } else
                    search.setText("");

            }
        });

        list_bar.add(0);
        list_bar.add(0);
        list_bar.add(0);
        list_bar.add(0);
        recycleAdapter = new RecycleAdapter_aksiyacafe(Constants.cafe, this);
        recyclerView.setAdapter(recycleAdapter);

        s1 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                swipeRefreshLayout.setRefreshing(false);
                recycleAdapter.setData(Constants.cafe);
            }
        };
        s2 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                swipeRefreshLayout.setRefreshing(true);

            }
        };
        s3 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                finish();

            }
        };
        gui();
        listener();

        get_cafeAksiya.get_Data();
        s4 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                count.setText("" + Constants.cart_list.size());
            }
        };
        count.setText("" + Constants.cart_list.size());

    }

    void gui() {
        imageView_location = (ImageView) findViewById(R.id.location);
        imageView_favorite = (ImageView) findViewById(R.id.heart);
        imageView_user = (ImageView) findViewById(R.id.user);
        imageView_home = (ImageView) findViewById(R.id.home);
        imageView_karzina = (ImageView) findViewById(R.id.karzina);
    }

    void listener() {
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Constants.cafe.clear();
                Log.d("cafe", charSequence.toString());
                recycleAdapter.setData(Constants.cafe);
                Constants.size = 0;
                Constants.srcCafe = charSequence.toString();
                search_cafe.get_Data();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        imageView_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView_home.startAnimation(myAnim);
                if (list_bar.get(0) == 0) {
                    imageView_home.setImageResource(R.drawable.home_icon);
                    list_bar.set(0, 1);
                }

                imageView_location.setImageResource(R.drawable.map_white);
                imageView_favorite.setImageResource(R.drawable.heart_white);
                imageView_user.setImageResource(R.drawable.user_white);

                list_bar.set(1, 0);
                list_bar.set(2, 0);
                list_bar.set(3, 0);
                Intent i = new Intent(aksiya_recycle.this, MainActivity.class);
                startActivity(i);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            }
        });

        imageView_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView_favorite.startAnimation(myAnim);
                if (list_bar.get(1) == 0) {
                    imageView_favorite.setImageResource(R.drawable.heart_icon);
                    list_bar.set(1, 1);
                }

                imageView_location.setImageResource(R.drawable.map_white);
                imageView_home.setImageResource(R.drawable.home_white);
                imageView_user.setImageResource(R.drawable.user_white);

                list_bar.set(0, 0);
                list_bar.set(2, 0);
                list_bar.set(3, 0);

                Intent i = new Intent(aksiya_recycle.this, like_activity.class);
                i.putExtra("from","cafe");
                startActivity(i);

                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);

            }
        });

        imageView_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView_location.startAnimation(myAnim);
                if (list_bar.get(2) == 0) {
                    imageView_location.setImageResource(R.drawable.map_icon);
                    list_bar.set(2, 1);
                }
                Intent i = new Intent(aksiya_recycle.this, myorder1.class);
                i.putExtra("from","aksiya");
                startActivity(i);
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);

                imageView_favorite.setImageResource(R.drawable.heart_white);
                imageView_home.setImageResource(R.drawable.home_white);
                imageView_user.setImageResource(R.drawable.user_white);

                list_bar.set(1, 0);
                list_bar.set(0, 0);
                list_bar.set(3, 0);

            }
        });

        imageView_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView_user.startAnimation(myAnim);
                if (list_bar.get(1) == 0) {
                    imageView_user.setImageResource(R.drawable.user_icon);
                    list_bar.set(1, 1);
                    Intent i = new Intent(aksiya_recycle.this, user_info.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                }

                imageView_location.setImageResource(R.drawable.map_white);
                imageView_home.setImageResource(R.drawable.home_white);
                imageView_favorite.setImageResource(R.drawable.heart_white);

                list_bar.set(1, 0);
                list_bar.set(2, 0);
                list_bar.set(0, 0);

            }
        });

        final ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.startAnimation(myAnim);

                finish();
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            }
        });
        final CardView relativeLayout_karzina = (CardView) findViewById(R.id.layout_karzina);
        relativeLayout_karzina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout_karzina.startAnimation(myAnim);
                Intent intent = new Intent(aksiya_recycle.this, Karzina.class);
                intent.putExtra("from", "cafe");
                startActivity(intent);


            }
        });
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        Constants.cafe.clear();
        Constants.size = 0;
        Constants.iter = true;
        recycleAdapter.setData(Constants.cafe);
        get_cafeAksiya.get_Data();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MainActivity.s4.sendEmptyMessage(1);
        finish();
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);

    }
}


