package com.tagam24.tagam;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;
import com.tagam24.tagam.Constants.Constants;
import com.tagam24.tagam.Database.Db;
import com.tagam24.tagam.cafes_menu.Cafes_menu;
import com.tagam24.tagam.karzina.Karzina;
import com.tagam24.tagam.like_activity.cafe_liked;
import com.tagam24.tagam.like_activity.like_activity;
import com.tagam24.tagam.liked_gumenje.nahar_liked;
import com.tagam24.tagam.models.Model_Cafe;
import com.tagam24.tagam.network.Api;
import com.tagam24.tagam.network.get_cafe;
import com.tagam24.tagam.models.Model_Cafe;
import com.tagam24.tagam.network.send_rating;
import com.tagam24.tagam.order.myorder1;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

import static com.tagam24.tagam.Constants.Constants.location;

public class dastawka_recycle extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, RatingDialogListener {
    static public String data;
    public static ArrayList<Model_Cafe> list;
    public static ArrayList<Integer> num;
    public static Handler s1, s2, s3, s4, s5, s6,s7,s8;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView count;
    Animation myAnim;
    ArrayList<Integer> list_bar, list_cat;
    RecycleAdapter_cafe recycleAdapter;
    EditText search;
    Db db;
    Typeface typebold, typeextrabold, typeregular, typelight;
    dil dd;
    ImageView cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10, cat11, cat12, cat13, cat14;
    View view;
    Context ctx;
    Boolean check = false, b1 = false;

    ImageView imageView_home, imageView_favorite, imageView_location, imageView_user, imageView_karzina, clear, back;

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
        setContentView(R.layout.activity_dastawka_recycle);


        typebold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Bold.ttf");
        typelight = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Light.ttf");
        typeregular = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Regular.ttf");
        typeextrabold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_ExtraBold.ttf");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(this);
        search = (EditText) findViewById(R.id.serach);

        search.setHint(dil.Gozleg);
        search.setTypeface(typeregular);
        clear = (ImageView) findViewById(R.id.clear);
        clear.setVisibility(View.INVISIBLE);
        search.setCursorVisible(false);
        search.setMaxLines(1);
        Constants.cafe.clear();
        Constants.idSC.clear();
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
        list_cat = new ArrayList<>();
        EditText editText = (EditText) findViewById(R.id.serach);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/font5.ttf");
        editText.setTypeface(typeface);

        list_bar.add(0);
        list_bar.add(0);
        list_bar.add(0);
        list_bar.add(0);
        list_cat.add(0);
        list_cat.add(0);
        list_cat.add(0);
        list_cat.add(0);
        list_cat.add(0);
        list_cat.add(0);
        list_cat.add(0);
        list_cat.add(0);
        list_cat.add(0);
        list_cat.add(0);
        list_cat.add(0);
        list_cat.add(0);
        list_cat.add(0);
        list_cat.add(0);


        recycleAdapter = new RecycleAdapter_cafe(Constants.cafe, this);
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

        get_cafe.get_Data();
        s4 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                count.setText("" + Constants.cart_list.size());
            }
        };
        s5 = new Handler() {
            @Override
            public void handleMessage(Message msg) {

            }
        };
        s6=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                favorite();
            }
        };
        s7=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                loc();
            }
        };
        s8=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                user();
            }
        };


        count.setText("" + Constants.cart_list.size());


        Glide.with(this)
                .load(R.drawable.fastfood).asBitmap().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(cat1);
        Glide.with(this)
                .load(R.drawable.kombo).asBitmap().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(cat2);
        Glide.with(this)
                .load(R.drawable.doner).asBitmap().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(cat3);
        Glide.with(this)
                .load(R.drawable.pizza).asBitmap().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(cat4);
        Glide.with(this)
                .load(R.drawable.sup).asBitmap().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(cat5);
        Glide.with(this)
                .load(R.drawable.gyzgyn_nahar).asBitmap().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(cat6);
        Glide.with(this)
                .load(R.drawable.hamyrly_nahar).asBitmap().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(cat7);
        Glide.with(this)
                .load(R.drawable.garnir).asBitmap().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(cat8);
        Glide.with(this)
                .load(R.drawable.shashlyk).asBitmap().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(cat9);
        Glide.with(this)
                .load(R.drawable.balyk).asBitmap().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(cat10);
        Glide.with(this)
                .load(R.drawable.sushi).asBitmap().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(cat11);
        Glide.with(this)
                .load(R.drawable.salat).asBitmap().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(cat12);
        Glide.with(this)
                .load(R.drawable.tort).asBitmap().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(cat13);
        Glide.with(this)
                .load(R.drawable.drink).asBitmap().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(cat14);
        Glide.with(this)
                .load(R.drawable.cart_icon).asBitmap().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView_karzina);


    }



    void gui() {
        imageView_location = (ImageView) findViewById(R.id.location);
        imageView_favorite = (ImageView) findViewById(R.id.heart);
        imageView_user = (ImageView) findViewById(R.id.user);
        imageView_home = (ImageView) findViewById(R.id.home);
        imageView_karzina = (ImageView) findViewById(R.id.karzina);
    }
    void favorite() {imageView_favorite.setImageResource(R.drawable.heart_white);
    }

    void loc() {
    imageView_location.setImageResource(R.drawable.map_white);
    }
    void user(){
    imageView_user.setImageResource(R.drawable.user_white);
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
                Constants.idSC.clear();
                Constants.srcCafe = charSequence.toString();
                get_cafe.get_Data();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.startAnimation(myAnim);

                finish();
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setCursorVisible(true);
                clear.setVisibility(View.VISIBLE);
                check = false;
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
                Intent i = new Intent(dastawka_recycle.this, MainActivity.class);
                startActivity(i);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);

                list_bar.set(1, 0);
                list_bar.set(2, 0);
                list_bar.set(3, 0);
            }
        });

        imageView_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dastawka_recycle.this, myorder1.class);
                i.putExtra("from","cafe");
                startActivity(i);

                imageView_location.startAnimation(myAnim);
                if (list_bar.get(2) == 0) {
                    imageView_location.setImageResource(R.drawable.map_icon);
                    list_bar.set(2, 1);
                }
                imageView_home.setImageResource(R.drawable.home_white);
                imageView_favorite.setImageResource(R.drawable.heart_white);
                imageView_user.setImageResource(R.drawable.user_white);
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                list_bar.set(1, 0);
                list_bar.set(0, 0);
                list_bar.set(3, 0);
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
                Intent intent = new Intent(dastawka_recycle.this, like_activity.class);
                intent.putExtra("from", "cafe");
                startActivity(intent);

                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            }

        });

        imageView_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dastawka_recycle.this, myorder1.class);
                i.putExtra("from","cafe");
                startActivity(i);
                imageView_location.startAnimation(myAnim);
                if (list_bar.get(2) == 0) {
                    imageView_location.setImageResource(R.drawable.map_icon);
                    list_bar.set(2, 1);
                }
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
                    Intent i = new Intent(dastawka_recycle.this, user_info.class);
                    i.putExtra("from","cafe");
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
        imageView_karzina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView_karzina.startAnimation(myAnim);
                Intent i = new Intent(dastawka_recycle.this, Karzina.class);

                startActivity(i);

                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            }
        });

        cat1 = (ImageView) findViewById(R.id.categ1);
        cat2 = (ImageView) findViewById(R.id.cat2);
        cat3 = (ImageView) findViewById(R.id.cat3);
        cat4 = (ImageView) findViewById(R.id.cat4);
        cat5 = (ImageView) findViewById(R.id.cat5);
        cat6 = (ImageView) findViewById(R.id.cat6);
        cat7 = (ImageView) findViewById(R.id.cat7);
        cat8 = (ImageView) findViewById(R.id.cat8);
        cat9 = (ImageView) findViewById(R.id.cat9);
        cat10 = (ImageView) findViewById(R.id.cat10);
        cat11 = (ImageView) findViewById(R.id.cat11);
        cat12 = (ImageView) findViewById(R.id.cat12);
        cat13 = (ImageView) findViewById(R.id.cat13);
        cat14 = (ImageView) findViewById(R.id.cat14);

        TextView t_cat1 = (TextView) findViewById(R.id.t_cat1);
        TextView t_cat2 = (TextView) findViewById(R.id.t_cat2);
        TextView t_cat3 = (TextView) findViewById(R.id.t_cat3);
        TextView t_cat4 = (TextView) findViewById(R.id.t_cat4);
        TextView t_cat5 = (TextView) findViewById(R.id.t_cat5);
        TextView t_cat6 = (TextView) findViewById(R.id.t_cat6);
        TextView t_cat7 = (TextView) findViewById(R.id.t_cat7);
        TextView t_cat8 = (TextView) findViewById(R.id.t_cat8);
        TextView t_cat9 = (TextView) findViewById(R.id.t_cat9);
        TextView t_cat10 = (TextView) findViewById(R.id.t_cat10);
        TextView t_cat11 = (TextView) findViewById(R.id.t_cat11);
        TextView t_cat12 = (TextView) findViewById(R.id.t_cat12);
        TextView t_cat13 = (TextView) findViewById(R.id.t_cat13);
        TextView t_cat14 = (TextView) findViewById(R.id.t_cat14);

        t_cat1.setText(dil.Tiz_tagam);
        t_cat2.setText(dil.Kombo);
        t_cat3.setText(dil.Doner);
        t_cat4.setText(dil.Pizza);
        t_cat5.setText(dil.Corba);
        t_cat6.setText(dil.Hamyrly_nahar);
        t_cat7.setText(dil.Garnir);
        t_cat8.setText(dil.Mangal);
        t_cat9.setText(dil.Balyk);
        t_cat10.setText(dil.Sushi);
        t_cat11.setText(dil.Salat);
        t_cat12.setText(dil.Ichgi);
        t_cat13.setText(dil.Gyzgyn_nahar);
        t_cat14.setText(dil.Desert);


        t_cat1.setTypeface(typebold);
        t_cat2.setTypeface(typebold);
        t_cat3.setTypeface(typebold);
        t_cat4.setTypeface(typebold);
        t_cat5.setTypeface(typebold);
        t_cat6.setTypeface(typebold);
        t_cat7.setTypeface(typebold);
        t_cat8.setTypeface(typebold);
        t_cat9.setTypeface(typebold);
        t_cat10.setTypeface(typebold);
        t_cat11.setTypeface(typebold);
        t_cat12.setTypeface(typebold);
        t_cat13.setTypeface(typebold);
        t_cat14.setTypeface(typebold);

        b1 = false;
        cat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list_cat.get(0) == 0) {
                    cat1.setAlpha(100);
                    list_cat.set(0, 1);
                    Constants.categoryCafe = "Tiz tagam-";
                    Constants.size = 0;
                    s2.sendEmptyMessage(1);
                    Constants.idSC.clear();
                    Constants.cafe.clear();
                    recycleAdapter.setData(Constants.cafe);
                    get_cafe.get_Data();
                } else {
                    cat1.setAlpha(255);
                    list_cat.set(0, 0);
                    s2.sendEmptyMessage(1);
                    Constants.size = 0;
                    Constants.categoryCafe = "";
                    Constants.idSC.clear();
                    Constants.cafe.clear();
                    recycleAdapter.setData(Constants.cafe);
                    get_cafe.get_Data();
                }

                list_cat.set(1, 0);
                list_cat.set(2, 0);
                list_cat.set(3, 0);
                list_cat.set(4, 0);
                list_cat.set(5, 0);
                list_cat.set(6, 0);
                list_cat.set(7, 0);
                list_cat.set(8, 0);
                list_cat.set(9, 0);
                list_cat.set(10, 0);
                list_cat.set(11, 0);
                list_cat.set(12, 0);
                list_cat.set(13, 0);

                cat14.setImageAlpha(255);
                cat2.setImageAlpha(255);
                cat3.setImageAlpha(255);
                cat4.setImageAlpha(255);
                cat5.setImageAlpha(255);
                cat6.setImageAlpha(255);
                cat7.setImageAlpha(255);
                cat8.setImageAlpha(255);
                cat9.setImageAlpha(255);
                cat10.setImageAlpha(255);
                cat11.setImageAlpha(255);
                cat12.setImageAlpha(255);
                cat13.setImageAlpha(255);
            }
        });

        cat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list_cat.get(1) == 0) {
                    cat2.setAlpha(100);
                    list_cat.set(1, 1);
                    Constants.categoryCafe = "Kombo-";
                    Constants.size = 0;
                    s2.sendEmptyMessage(1);
                    Constants.idSC.clear();
                    Constants.cafe.clear();
                    recycleAdapter.setData(Constants.cafe);
                    get_cafe.get_Data();
                } else {
                    cat2.setAlpha(255);
                    list_cat.set(1, 0);
                    s2.sendEmptyMessage(1);
                    Constants.size = 0;
                    Constants.categoryCafe = "";
                    Constants.idSC.clear();
                    Constants.cafe.clear();
                    recycleAdapter.setData(Constants.cafe);
                    get_cafe.get_Data();
                }

                list_cat.set(0, 0);
                list_cat.set(2, 0);
                list_cat.set(3, 0);
                list_cat.set(4, 0);
                list_cat.set(5, 0);
                list_cat.set(6, 0);
                list_cat.set(7, 0);
                list_cat.set(8, 0);
                list_cat.set(9, 0);
                list_cat.set(10, 0);
                list_cat.set(11, 0);
                list_cat.set(12, 0);
                list_cat.set(13, 0);

                cat14.setImageAlpha(255);
                cat1.setImageAlpha(255);
                cat3.setImageAlpha(255);
                cat4.setImageAlpha(255);
                cat5.setImageAlpha(255);
                cat6.setImageAlpha(255);
                cat7.setImageAlpha(255);
                cat8.setImageAlpha(255);
                cat9.setImageAlpha(255);
                cat10.setImageAlpha(255);
                cat11.setImageAlpha(255);
                cat12.setImageAlpha(255);
                cat13.setImageAlpha(255);
            }
        });

        cat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list_cat.get(2) == 0) {
                    cat3.setAlpha(100);
                    list_cat.set(2, 1);
                    Constants.categoryCafe = "Doner-";
                    Constants.size = 0;
                    s2.sendEmptyMessage(1);
                    Constants.idSC.clear();
                    Constants.cafe.clear();
                    recycleAdapter.setData(Constants.cafe);
                    get_cafe.get_Data();
                } else {
                    cat3.setAlpha(255);
                    list_cat.set(2, 0);
                    s2.sendEmptyMessage(1);
                    Constants.size = 0;
                    Constants.categoryCafe = "";
                    Constants.idSC.clear();
                    Constants.cafe.clear();
                    recycleAdapter.setData(Constants.cafe);
                    get_cafe.get_Data();
                }

                list_cat.set(0, 0);
                list_cat.set(1, 0);
                list_cat.set(3, 0);
                list_cat.set(4, 0);
                list_cat.set(5, 0);
                list_cat.set(6, 0);
                list_cat.set(7, 0);
                list_cat.set(8, 0);
                list_cat.set(9, 0);
                list_cat.set(10, 0);
                list_cat.set(11, 0);
                list_cat.set(12, 0);
                list_cat.set(13, 0);

                cat14.setImageAlpha(255);
                cat1.setImageAlpha(255);
                cat2.setImageAlpha(255);
                cat4.setImageAlpha(255);
                cat5.setImageAlpha(255);
                cat6.setImageAlpha(255);
                cat7.setImageAlpha(255);
                cat8.setImageAlpha(255);
                cat9.setImageAlpha(255);
                cat10.setImageAlpha(255);
                cat11.setImageAlpha(255);
                cat12.setImageAlpha(255);
                cat13.setImageAlpha(255);
            }
        });
        cat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list_cat.get(3) == 0) {
                    cat4.setAlpha(100);
                    list_cat.set(3, 1);
                    Constants.categoryCafe = "Pizza-";
                    Constants.size = 0;
                    s2.sendEmptyMessage(1);
                    Constants.idSC.clear();
                    Constants.cafe.clear();
                    recycleAdapter.setData(Constants.cafe);
                    get_cafe.get_Data();
                } else {
                    cat4.setAlpha(255);
                    list_cat.set(3, 0);
                    s2.sendEmptyMessage(1);
                    Constants.size = 0;
                    Constants.categoryCafe = "";
                    Constants.idSC.clear();
                    Constants.cafe.clear();
                    recycleAdapter.setData(Constants.cafe);
                    get_cafe.get_Data();
                }

                list_cat.set(0, 0);
                list_cat.set(2, 0);
                list_cat.set(1, 0);
                list_cat.set(4, 0);
                list_cat.set(5, 0);
                list_cat.set(6, 0);
                list_cat.set(7, 0);
                list_cat.set(8, 0);
                list_cat.set(9, 0);
                list_cat.set(10, 0);
                list_cat.set(11, 0);
                list_cat.set(12, 0);
                list_cat.set(13, 0);

                cat14.setImageAlpha(255);
                cat1.setImageAlpha(255);
                cat2.setImageAlpha(255);
                cat3.setImageAlpha(255);
                cat5.setImageAlpha(255);
                cat6.setImageAlpha(255);
                cat7.setImageAlpha(255);
                cat8.setImageAlpha(255);
                cat9.setImageAlpha(255);
                cat10.setImageAlpha(255);
                cat11.setImageAlpha(255);
                cat12.setImageAlpha(255);
                cat13.setImageAlpha(255);
            }
        });
        cat5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list_cat.get(4) == 0) {
                    cat5.setAlpha(100);
                    list_cat.set(4, 1);
                    Constants.categoryCafe = "Çorba-";
                    Constants.size = 0;
                    s2.sendEmptyMessage(1);
                    Constants.idSC.clear();
                    Constants.cafe.clear();
                    recycleAdapter.setData(Constants.cafe);
                    get_cafe.get_Data();
                } else {
                    cat5.setAlpha(255);
                    list_cat.set(4, 0);
                    s2.sendEmptyMessage(1);
                    Constants.size = 0;
                    Constants.categoryCafe = "";
                    Constants.idSC.clear();
                    Constants.cafe.clear();
                    recycleAdapter.setData(Constants.cafe);
                    get_cafe.get_Data();
                }
                list_cat.set(0, 0);
                list_cat.set(2, 0);
                list_cat.set(3, 0);
                list_cat.set(1, 0);
                list_cat.set(5, 0);
                list_cat.set(6, 0);
                list_cat.set(7, 0);
                list_cat.set(8, 0);
                list_cat.set(9, 0);
                list_cat.set(10, 0);
                list_cat.set(11, 0);
                list_cat.set(12, 0);
                list_cat.set(13, 0);

                cat14.setImageAlpha(255);
                cat1.setImageAlpha(255);
                cat3.setImageAlpha(255);
                cat4.setImageAlpha(255);
                cat2.setImageAlpha(255);
                cat6.setImageAlpha(255);
                cat7.setImageAlpha(255);
                cat8.setImageAlpha(255);
                cat9.setImageAlpha(255);
                cat10.setImageAlpha(255);
                cat11.setImageAlpha(255);
                cat12.setImageAlpha(255);
                cat13.setImageAlpha(255);
            }
        });
        cat6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list_cat.get(5) == 0) {
                    cat6.setAlpha(100);
                    list_cat.set(5, 1);
                    Constants.categoryCafe = "Gyzgyn nahar-";
                    Constants.size = 0;
                    s2.sendEmptyMessage(1);
                    Constants.idSC.clear();
                    Constants.cafe.clear();
                    recycleAdapter.setData(Constants.cafe);
                    get_cafe.get_Data();
                } else {
                    cat6.setAlpha(255);
                    list_cat.set(5, 0);
                    s2.sendEmptyMessage(1);
                    Constants.size = 0;
                    Constants.categoryCafe = "";
                    Constants.idSC.clear();
                    Constants.cafe.clear();
                    recycleAdapter.setData(Constants.cafe);
                    get_cafe.get_Data();
                }

                list_cat.set(0, 0);
                list_cat.set(2, 0);
                list_cat.set(3, 0);
                list_cat.set(4, 0);
                list_cat.set(1, 0);
                list_cat.set(6, 0);
                list_cat.set(7, 0);
                list_cat.set(8, 0);
                list_cat.set(9, 0);
                list_cat.set(10, 0);
                list_cat.set(11, 0);
                list_cat.set(12, 0);
                list_cat.set(13, 0);

                cat14.setImageAlpha(255);
                cat1.setImageAlpha(255);
                cat3.setImageAlpha(255);
                cat4.setImageAlpha(255);
                cat5.setImageAlpha(255);
                cat2.setImageAlpha(255);
                cat7.setImageAlpha(255);
                cat8.setImageAlpha(255);
                cat9.setImageAlpha(255);
                cat10.setImageAlpha(255);
                cat11.setImageAlpha(255);
                cat12.setImageAlpha(255);
                cat13.setImageAlpha(255);
            }
        });
        cat7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list_cat.get(6) == 0) {
                    cat7.setAlpha(100);
                    list_cat.set(6, 1);
                    Constants.categoryCafe = "Hamyrly nahar-";
                    Constants.size = 0;
                    s2.sendEmptyMessage(1);
                    Constants.idSC.clear();
                    Constants.cafe.clear();
                    recycleAdapter.setData(Constants.cafe);
                    get_cafe.get_Data();
                } else {
                    cat7.setAlpha(255);
                    list_cat.set(6, 0);
                    s2.sendEmptyMessage(1);
                    Constants.size = 0;
                    Constants.categoryCafe = "";
                    Constants.idSC.clear();
                    Constants.cafe.clear();
                    recycleAdapter.setData(Constants.cafe);
                    get_cafe.get_Data();
                }

                list_cat.set(0, 0);
                list_cat.set(2, 0);
                list_cat.set(3, 0);
                list_cat.set(4, 0);
                list_cat.set(5, 0);
                list_cat.set(1, 0);
                list_cat.set(7, 0);
                list_cat.set(8, 0);
                list_cat.set(9, 0);
                list_cat.set(10, 0);
                list_cat.set(11, 0);
                list_cat.set(12, 0);
                list_cat.set(13, 0);

                cat14.setImageAlpha(255);
                cat1.setImageAlpha(255);
                cat3.setImageAlpha(255);
                cat4.setImageAlpha(255);
                cat5.setImageAlpha(255);
                cat6.setImageAlpha(255);
                cat2.setImageAlpha(255);
                cat8.setImageAlpha(255);
                cat9.setImageAlpha(255);
                cat10.setImageAlpha(255);
                cat11.setImageAlpha(255);
                cat12.setImageAlpha(255);
                cat13.setImageAlpha(255);
            }
        });
        cat8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list_cat.get(7) == 0) {
                    cat8.setAlpha(100);
                    list_cat.set(7, 1);
                    Constants.categoryCafe = "Garnir-";
                    Constants.size = 0;
                    s2.sendEmptyMessage(1);
                    Constants.idSC.clear();
                    Constants.cafe.clear();
                    recycleAdapter.setData(Constants.cafe);
                    get_cafe.get_Data();
                } else {
                    cat8.setAlpha(255);
                    list_cat.set(7, 0);
                    s2.sendEmptyMessage(1);
                    Constants.size = 0;
                    Constants.categoryCafe = "";
                    Constants.idSC.clear();
                    Constants.cafe.clear();
                    recycleAdapter.setData(Constants.cafe);
                    get_cafe.get_Data();
                }

                list_cat.set(0, 0);
                list_cat.set(2, 0);
                list_cat.set(3, 0);
                list_cat.set(4, 0);
                list_cat.set(5, 0);
                list_cat.set(6, 0);
                list_cat.set(1, 0);
                list_cat.set(8, 0);
                list_cat.set(9, 0);
                list_cat.set(10, 0);
                list_cat.set(11, 0);
                list_cat.set(12, 0);
                list_cat.set(13, 0);

                cat14.setImageAlpha(255);
                cat1.setImageAlpha(255);
                cat3.setImageAlpha(255);
                cat4.setImageAlpha(255);
                cat5.setImageAlpha(255);
                cat6.setImageAlpha(255);
                cat7.setImageAlpha(255);
                cat2.setImageAlpha(255);
                cat9.setImageAlpha(255);
                cat10.setImageAlpha(255);
                cat11.setImageAlpha(255);
                cat12.setImageAlpha(255);
                cat13.setImageAlpha(255);
            }
        });
        cat9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list_cat.get(8) == 0) {
                    cat9.setAlpha(100);
                    list_cat.set(8, 1);
                    Constants.categoryCafe = "Mangal-";
                    Constants.size = 0;
                    s2.sendEmptyMessage(1);
                    Constants.idSC.clear();
                    Constants.cafe.clear();
                    recycleAdapter.setData(Constants.cafe);
                    get_cafe.get_Data();
                } else {
                    cat9.setAlpha(255);
                    list_cat.set(8, 0);
                    s2.sendEmptyMessage(1);
                    Constants.size = 0;
                    Constants.categoryCafe = "";
                    Constants.idSC.clear();
                    Constants.cafe.clear();
                    recycleAdapter.setData(Constants.cafe);
                    get_cafe.get_Data();
                }

                list_cat.set(0, 0);
                list_cat.set(2, 0);
                list_cat.set(3, 0);
                list_cat.set(4, 0);
                list_cat.set(5, 0);
                list_cat.set(6, 0);
                list_cat.set(7, 0);
                list_cat.set(1, 0);
                list_cat.set(9, 0);
                list_cat.set(10, 0);
                list_cat.set(11, 0);
                list_cat.set(12, 0);
                list_cat.set(13, 0);

                cat14.setImageAlpha(255);
                cat1.setImageAlpha(255);
                cat3.setImageAlpha(255);
                cat4.setImageAlpha(255);
                cat5.setImageAlpha(255);
                cat6.setImageAlpha(255);
                cat7.setImageAlpha(255);
                cat8.setImageAlpha(255);
                cat2.setImageAlpha(255);
                cat10.setImageAlpha(255);
                cat11.setImageAlpha(255);
                cat12.setImageAlpha(255);
                cat13.setImageAlpha(255);
            }
        });
        cat10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list_cat.get(9) == 0) {
                    cat10.setAlpha(100);
                    list_cat.set(9, 1);
                    Constants.categoryCafe = "Balyk-";
                    Constants.size = 0;
                    s2.sendEmptyMessage(1);
                    Constants.idSC.clear();
                    Constants.cafe.clear();
                    recycleAdapter.setData(Constants.cafe);
                    get_cafe.get_Data();
                } else {
                    cat10.setAlpha(255);
                    list_cat.set(9, 0);
                    s2.sendEmptyMessage(1);
                    Constants.size = 0;
                    Constants.categoryCafe = "";
                    Constants.idSC.clear();
                    Constants.cafe.clear();
                    recycleAdapter.setData(Constants.cafe);
                    get_cafe.get_Data();
                }

                list_cat.set(0, 0);
                list_cat.set(2, 0);
                list_cat.set(3, 0);
                list_cat.set(4, 0);
                list_cat.set(5, 0);
                list_cat.set(6, 0);
                list_cat.set(7, 0);
                list_cat.set(8, 0);
                list_cat.set(1, 0);
                list_cat.set(10, 0);
                list_cat.set(11, 0);
                list_cat.set(12, 0);
                list_cat.set(13, 0);

                cat14.setImageAlpha(255);
                cat1.setImageAlpha(255);
                cat3.setImageAlpha(255);
                cat4.setImageAlpha(255);
                cat5.setImageAlpha(255);
                cat6.setImageAlpha(255);
                cat7.setImageAlpha(255);
                cat8.setImageAlpha(255);
                cat9.setImageAlpha(255);
                cat2.setImageAlpha(255);
                cat11.setImageAlpha(255);
                cat12.setImageAlpha(255);
                cat13.setImageAlpha(255);
            }
        });
        cat11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list_cat.get(10) == 0) {
                    cat11.setAlpha(100);
                    list_cat.set(10, 1);
                    Constants.categoryCafe = "Suşi-";
                    Constants.size = 0;
                    s2.sendEmptyMessage(1);
                    Constants.idSC.clear();
                    Constants.cafe.clear();
                    recycleAdapter.setData(Constants.cafe);
                    get_cafe.get_Data();
                } else {
                    cat11.setAlpha(255);
                    list_cat.set(10, 0);
                    s2.sendEmptyMessage(1);
                    Constants.size = 0;
                    Constants.categoryCafe = "";
                    Constants.idSC.clear();
                    Constants.cafe.clear();
                    recycleAdapter.setData(Constants.cafe);
                    get_cafe.get_Data();
                }

                list_cat.set(0, 0);
                list_cat.set(2, 0);
                list_cat.set(3, 0);
                list_cat.set(4, 0);
                list_cat.set(5, 0);
                list_cat.set(6, 0);
                list_cat.set(7, 0);
                list_cat.set(8, 0);
                list_cat.set(9, 0);
                list_cat.set(1, 0);
                list_cat.set(11, 0);
                list_cat.set(12, 0);
                list_cat.set(13, 0);

                cat14.setImageAlpha(255);
                cat1.setImageAlpha(255);
                cat3.setImageAlpha(255);
                cat4.setImageAlpha(255);
                cat5.setImageAlpha(255);
                cat6.setImageAlpha(255);
                cat7.setImageAlpha(255);
                cat8.setImageAlpha(255);
                cat9.setImageAlpha(255);
                cat10.setImageAlpha(255);
                cat2.setImageAlpha(255);
                cat12.setImageAlpha(255);
                cat13.setImageAlpha(255);
            }
        });
        cat12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list_cat.get(11) == 0) {
                    cat12.setAlpha(100);
                    list_cat.set(11, 1);
                    Constants.categoryCafe = "Salat-";
                    Constants.size = 0;
                    s2.sendEmptyMessage(1);
                    Constants.idSC.clear();
                    Constants.cafe.clear();
                    recycleAdapter.setData(Constants.cafe);
                    get_cafe.get_Data();
                } else {
                    cat12.setAlpha(255);
                    list_cat.set(11, 0);
                    s2.sendEmptyMessage(1);
                    Constants.size = 0;
                    Constants.categoryCafe = "";
                    Constants.idSC.clear();
                    Constants.cafe.clear();
                    recycleAdapter.setData(Constants.cafe);
                    get_cafe.get_Data();
                }
                list_cat.set(0, 0);
                list_cat.set(2, 0);
                list_cat.set(3, 0);
                list_cat.set(4, 0);
                list_cat.set(5, 0);
                list_cat.set(6, 0);
                list_cat.set(7, 0);
                list_cat.set(8, 0);
                list_cat.set(9, 0);
                list_cat.set(10, 0);
                list_cat.set(1, 0);
                list_cat.set(12, 0);
                list_cat.set(13, 0);

                cat14.setImageAlpha(255);
                cat1.setImageAlpha(255);
                cat3.setImageAlpha(255);
                cat4.setImageAlpha(255);
                cat5.setImageAlpha(255);
                cat6.setImageAlpha(255);
                cat7.setImageAlpha(255);
                cat8.setImageAlpha(255);
                cat9.setImageAlpha(255);
                cat10.setImageAlpha(255);
                cat11.setImageAlpha(255);
                cat2.setImageAlpha(255);
                cat13.setImageAlpha(255);
            }
        });
        cat13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list_cat.get(12) == 0) {
                    cat13.setAlpha(100);
                    list_cat.set(12, 1);
                    Constants.categoryCafe = "Desert-";
                    Constants.size = 0;
                    s2.sendEmptyMessage(1);
                    Constants.idSC.clear();
                    Constants.cafe.clear();
                    recycleAdapter.setData(Constants.cafe);
                    get_cafe.get_Data();
                } else {
                    cat13.setAlpha(255);
                    list_cat.set(12, 0);
                    s2.sendEmptyMessage(1);
                    Constants.size = 0;
                    Constants.categoryCafe = "";
                    Constants.idSC.clear();
                    Constants.cafe.clear();
                    recycleAdapter.setData(Constants.cafe);
                    get_cafe.get_Data();
                }
                list_cat.set(0, 0);
                list_cat.set(2, 0);
                list_cat.set(3, 0);
                list_cat.set(4, 0);
                list_cat.set(5, 0);
                list_cat.set(6, 0);
                list_cat.set(7, 0);
                list_cat.set(8, 0);
                list_cat.set(9, 0);
                list_cat.set(10, 0);
                list_cat.set(11, 0);
                list_cat.set(1, 0);
                list_cat.set(13, 0);

                cat14.setImageAlpha(255);
                cat1.setImageAlpha(255);
                cat3.setImageAlpha(255);
                cat4.setImageAlpha(255);
                cat5.setImageAlpha(255);
                cat6.setImageAlpha(255);
                cat7.setImageAlpha(255);
                cat8.setImageAlpha(255);
                cat9.setImageAlpha(255);
                cat10.setImageAlpha(255);
                cat11.setImageAlpha(255);
                cat12.setImageAlpha(255);
                cat2.setImageAlpha(255);
            }
        });
        cat14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list_cat.get(13) == 0) {
                    cat14.setAlpha(100);
                    list_cat.set(13, 1);
                    Constants.categoryCafe = "Içgi-";
                    Constants.size = 0;
                    s2.sendEmptyMessage(1);
                    Constants.idSC.clear();
                    Constants.cafe.clear();
                    recycleAdapter.setData(Constants.cafe);
                    get_cafe.get_Data();
                } else {
                    cat14.setAlpha(255);
                    list_cat.set(13, 0);
                    s2.sendEmptyMessage(1);
                    Constants.size = 0;
                    Constants.categoryCafe = "";
                    Constants.idSC.clear();
                    Constants.cafe.clear();
                    recycleAdapter.setData(Constants.cafe);
                    get_cafe.get_Data();
                }

                list_cat.set(0, 0);
                list_cat.set(2, 0);
                list_cat.set(3, 0);
                list_cat.set(4, 0);
                list_cat.set(5, 0);
                list_cat.set(6, 0);
                list_cat.set(7, 0);
                list_cat.set(8, 0);
                list_cat.set(9, 0);
                list_cat.set(10, 0);
                list_cat.set(11, 0);
                list_cat.set(1, 0);
                list_cat.set(12, 0);

                cat13.setImageAlpha(255);
                cat1.setImageAlpha(255);
                cat3.setImageAlpha(255);
                cat4.setImageAlpha(255);
                cat5.setImageAlpha(255);
                cat6.setImageAlpha(255);
                cat7.setImageAlpha(255);
                cat8.setImageAlpha(255);
                cat9.setImageAlpha(255);
                cat10.setImageAlpha(255);
                cat11.setImageAlpha(255);
                cat12.setImageAlpha(255);
                cat2.setImageAlpha(255);
            }
        });

        



               /* ImageView imageView = (ImageView) findViewById(R.id.menu_item_recycle_dostawka);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(dostawka_recycle.this, Cafes_menu.class);
                        startActivity(intent);
                    }
                });*/
             /*   final RelativeLayout relativeLayout_karzina=(RelativeLayout)findViewById(R.id.layout_karzina1);
                relativeLayout_karzina.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        relativeLayout_karzina.startAnimation(myAnim);
                        Intent intent=new Intent(dastawka_recycle.this,Karzina.class);
                        intent.putExtra("from","cafe");
                        startActivity(intent);


                    }
                });*/
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        Constants.cafe.clear();
        Constants.size = 0;
        Constants.iter = true;
        Constants.idSC.clear();
        recycleAdapter.setData(Constants.cafe);
        get_cafe.get_Data();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        MainActivity.s3.sendEmptyMessage(1);
        MainActivity.s2.sendEmptyMessage(1);
        finish();
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);

    }

    public void send_rate() {

        AppRatingDialog.Builder appRatingDialog = new AppRatingDialog.Builder();

        // if(!db.isInRate_nahar(Constants.rate_id))
        appRatingDialog.setPositiveButtonText("Tassykla");
        appRatingDialog.setNegativeButtonText("Yza");
        appRatingDialog.setNoteDescriptions(Arrays.asList("Erbet", "Kanagatlanarly", "Gowy", "Örän gowy", "Ajaýyp !!!"));
        appRatingDialog.setDefaultRating(Constants.df_rate);
        appRatingDialog.setTitle("Tagamy Bahalandyryň");
        appRatingDialog.setDescription("Mynasyp baha bermegiňizi Haýyş etýäris");
        appRatingDialog.setStarColor(R.color.colorPrimary1);
        appRatingDialog.setNoteDescriptionTextColor(R.color.black2);
        appRatingDialog.setTitleTextColor(R.color.black2);
        appRatingDialog.setCommentInputEnabled(false);
        appRatingDialog.setDescriptionTextColor(R.color.black2);
        appRatingDialog.setWindowAnimation(R.style.MyDialogFadeAnimation);
        appRatingDialog.setCancelable(false);
        appRatingDialog.setCanceledOnTouchOutside(false);
        appRatingDialog.create((FragmentActivity) this).show(); // only if listener is implemented by fragment.show();
    }

    @Override
    public void onPositiveButtonClicked(int i, @NotNull String s) {
        Toast.makeText(this, "" + i, Toast.LENGTH_LONG).show();
        // db.inser_Rate(Constants.rate_id);
        // send_rating.get_Data(Constants.rate_id, ""+i , "cafe");
    }

    @Override
    public void onNegativeButtonClicked() {

    }

    @Override
    public void onNeutralButtonClicked() {

    }
}