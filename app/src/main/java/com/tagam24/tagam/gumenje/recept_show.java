package com.tagam24.tagam.gumenje;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.codesgood.views.JustifiedTextView;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;
import com.tagam24.tagam.Database.Db;
import com.tagam24.tagam.ImageModel;
import com.tagam24.tagam.R;
import com.tagam24.tagam.cafe_bar.ViewPager_Adapter;
import com.tagam24.tagam.cafe_bar.show_details_bars;
import com.tagam24.tagam.dil;
import com.tagam24.tagam.liked_gumenje.like_activity_gumenje;
import com.tagam24.tagam.network.send_like;
import com.tagam24.tagam.network.send_rating;
import com.tagam24.tagam.network.send_watched;
import com.viewpagerindicator.CirclePageIndicator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;


public class recept_show extends AppCompatActivity implements RatingDialogListener {
    Menu menu;
    MenuItem item;
    private static final Integer[] XMEN = new Integer[3];
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();
    Handler create;
    String id;
    private static ViewPager mPager, mPager2;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<String> imageModelArrayList;
    CardView relativeLayout;
    public static ArrayList<Model_receipe_duzimi> list;
    public static ArrayList<Model_receipe_duzimi> list2;
    TextView name, date, watch, count_rating, n_people, haladym, duzumi, tayyarlanyshy, title;
    TextView content;
    RecyclerView duzumi_rs, tayarlanyshy_rs;
    CardView banner;
    RecycleAdapter_recipe_duzumi AdapterDz;
    RecycleAdapter_recipe_tayarlansy AdapterTr;
    ImageView like, love, back;
    Animation myAnim;
    Db db;
    Intent i;
    Context ctx;
    dil dd;
    String t,w;
    String myArray[] = new String[1000];
    Integer xx=0;

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
        setContentView(R.layout.recipe_show);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        imageModelArrayList = new ArrayList<>();

        mPager = (ViewPager) findViewById(R.id.viewpager);
        // mPager2 = (ViewPager) findViewById(R.id.viewpager_bottom);

        init();

        relativeLayout = (CardView) findViewById(R.id.cardview);

        MaterialRippleLayout rippleView = (MaterialRippleLayout) findViewById(R.id.more);

        rippleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
            }
        });

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse);
        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
        viewpager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        gui();


        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            private int state;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {

                    toolbar.setVisibility(View.GONE);
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {

                    toolbar.setVisibility(View.VISIBLE);
                } else {
                    toolbar.setVisibility(View.GONE);

                }
            }
        });

    }

    void gui() {
        i = getIntent();
        myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        title = (TextView) findViewById(R.id.title_toolbar);
        name = (TextView) findViewById(R.id.name);
        date = (TextView) findViewById(R.id.date);
        watch = (TextView) findViewById(R.id.watch);
        count_rating = (TextView) findViewById(R.id.count_rating);
        n_people = (TextView) findViewById(R.id.n_people);
        haladym = (TextView) findViewById(R.id.haladym);
        duzumi = (TextView) findViewById(R.id.duzumi);
        tayyarlanyshy = (TextView) findViewById(R.id.tayyarlanyshy);
        content = (TextView) findViewById(R.id.content);
        duzumi_rs = (RecyclerView) findViewById(R.id.recycle_recipe_duzimi);
        tayarlanyshy_rs = (RecyclerView) findViewById(R.id.recycle_recipe_tayarlansy);
        like = (ImageView) findViewById(R.id.like);
        back = (ImageView) findViewById(R.id.back);
        banner=(CardView)findViewById(R.id.banner);
        banner.setVisibility(View.INVISIBLE);


        if (db.isInRate_recept(i.getStringExtra("id"))) {
            like.setImageResource(R.drawable.star_icon);
            like.setEnabled(false);
        }

        if (!db.isInWatch_recept(i.getStringExtra("id"))) {
            db.inser_Watch_recept(i.getStringExtra("id"));
            send_watched.get_Data(i.getStringExtra("id"), "recept");
            watch.setText("" + (Integer.parseInt(i.getStringExtra("watch")) + 2));
        } else {
            watch.setText("" + (Integer.parseInt(i.getStringExtra("watch"))));
        }

        Typeface typebold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Bold.ttf");
        Typeface typelight = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Light.ttf");
        Typeface typeregular = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Regular.ttf");
        Typeface typeextrabold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_ExtraBold.ttf");

        title.setText(i.getStringExtra("name"));
        name.setText(i.getStringExtra("name"));
        date.setText(i.getStringExtra("date"));

        //watch


        count_rating.setText(i.getStringExtra("count_rating"));
        n_people.setText(i.getStringExtra("n_people"));
        haladym.setText(dd.halanlarym);
        duzumi.setText(dd.resepti);
        tayyarlanyshy.setText(dd.tayyarlansy);
        content.setText(i.getStringExtra("content"));
        id = i.getStringExtra("id");
        love = (ImageView) findViewById(R.id.love);
        imageModelArrayList.add(i.getStringExtra("image"));
        imageModelArrayList.add(i.getStringExtra("image1"));
        imageModelArrayList.add(i.getStringExtra("image2"));
        mPager.setAdapter(new ViewPager_Adapter(this, imageModelArrayList));

        title.setTypeface(typebold);
        name.setTypeface(typebold);
        date.setTypeface(typeregular);
        duzumi.setTypeface(typeregular);
        tayyarlanyshy.setTypeface(typeregular);
        content.setTypeface(typeregular);
        haladym.setTypeface(typeregular);
        watch.setTypeface(typeregular);

        String s = i.getStringExtra("text2");
        String[] duzumi = new String[1000];
        duzumi = i.getStringExtra("text1").split("-");
        String[] tayyarlanyshy = new String[1000];
        tayyarlanyshy = s.split("-");
        RecycleAdapter_recipe_duzumi duzumi_adapter = new RecycleAdapter_recipe_duzumi(duzumi, this);
        duzumi_rs.setAdapter(duzumi_adapter);
        duzumi_rs.setLayoutManager(new LinearLayoutManager(this));
        RecycleAdapter_recipe_tayarlansy tayarlansy = new RecycleAdapter_recipe_tayarlansy(tayyarlanyshy, this);
        tayarlanyshy_rs.setAdapter(tayarlansy);
        tayarlanyshy_rs.setLayoutManager(new LinearLayoutManager(this));



        if (db.isInReceipeduzumi(id)) {
            love.setImageResource(R.drawable.heart_icon);
        } else love.setImageResource(R.drawable.heart_bos);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(recept_show.this, gumenje_activity.class);
                intent.putExtra("tab","0");
                startActivity(intent);
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                finish();
                ;

            }
        });
        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                love.startAnimation(myAnim);
                if (!db.isInReceipeduzumi(id)) {
                    love.setImageResource(R.drawable.heart_icon);
                    db.inser_receipe_duzumi(id, i.getStringExtra("image"), i.getStringExtra("name"),
                            i.getStringExtra("content"), i.getStringExtra("watch"), i.getStringExtra("rating"), i.getStringExtra("n_people"),
                            i.getStringExtra("text1"), i.getStringExtra("text2"), i.getStringExtra("image2"), i.getStringExtra("image1"), i.getStringExtra("date"));
                } else {
                    love.setImageResource(R.drawable.heart_bos);
                    db.delete_receipe_duzumi(id);
                }
            }
        });
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                like.startAnimation(myAnim);
                showDialog();
            }
        });

    }
    private void showDialog() {
        new AppRatingDialog.Builder()
                .setPositiveButtonText("Tassykla")
                .setNegativeButtonText("Yza")
                .setNoteDescriptions(Arrays.asList("Erbet", "Kanagatlanarly", "Gowy", "Örän gowy", "Ajaýyp !!!"))
                .setDefaultRating(2)
                .setTitle("Kafeny Bahalandyryň")
                .setDescription("Mynasyp baha bermegiňizi Haýyş etýäris")
                .setStarColor(R.color.colorPrimary1)
                .setNoteDescriptionTextColor(R.color.black2)
                .setTitleTextColor(R.color.black2)
                .setCommentInputEnabled(false)
                .setDescriptionTextColor(R.color.black2)
                .setWindowAnimation(R.style.MyDialogFadeAnimation)
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .create(this)
                .show();
    }




    void came() {


    }

    private void init() {


        // Auto start of viewpager
       /* final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager2.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);*/

        // Pager listener over indicator


    }

    public void restartApp() {
        Intent intent = new Intent(getApplicationContext(), recept_show.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onPositiveButtonClicked(int i, @NotNull String s) {
       Intent intent1=new Intent();
       like.setImageResource(R.drawable.star_icon);
       Integer a=Integer.parseInt(n_people.getText().toString());
       a=a+1;
       n_people.setText(""+a);
       like.setEnabled(false);
       String ss=Integer.toString(i);
       db.inser_Rate_recept(id);
       send_rating.get_Data(id, "5" , "recept");
    }

    @Override
    public void onNegativeButtonClicked() {

    }

    @Override
    public void onNeutralButtonClicked() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=getIntent();/*
        if(i.getStringExtra("from").equals("recept")) {
            Intent intent = new Intent(recept_show.this, gumenje_activity.class);
            intent.putExtra("tab", "0");
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
        }
        if(i.getStringExtra("from").equals("recept_liked")) {
            Intent intent = new Intent(recept_show.this, like_activity_gumenje.class);
            intent.putExtra("tab", "0");
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
        }*/
        finish();

    }


}
