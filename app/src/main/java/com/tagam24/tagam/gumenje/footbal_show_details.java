package com.tagam24.tagam.gumenje;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.caverock.androidsvg.IntegerParser;
import com.codesgood.views.JustifiedTextView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;
import com.tagam24.tagam.Database.Db;
import com.tagam24.tagam.R;
import com.tagam24.tagam.cafe_bar.ViewPager_Adapter;
import com.tagam24.tagam.dil;
import com.tagam24.tagam.liked_gumenje.like_activity_gumenje;
import com.tagam24.tagam.models.model_vote;
import com.tagam24.tagam.network.send_like;
import com.tagam24.tagam.network.send_rating;
import com.tagam24.tagam.network.send_team;
import com.tagam24.tagam.network.send_watched;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class footbal_show_details extends AppCompatActivity implements RatingDialogListener {
    TextView name, date, watch, rating, n_people, haladym,
            team1_name, draw_name, team2_name, team1_counter, draw_counter, team2_counter, title_toolbar;
    ViewPager viewPager;
    JustifiedTextView content;
    RoundedImageView banner;
    LinearLayout l1, l2, l3;
    ImageView team1, team2, team3, back;
    ImageView image;
    ArrayList<String> images = new ArrayList<>();
    Db db;
    Typeface typebold, typeregular, typelight, typeextrabold;
    AppBarLayout appBarLayout;
    Toolbar toolbar;
    Boolean voted = false;
    Integer count;
    String x, t, w;
    String myArray[] = new String[1000];
    String id, find = "0";
    Double coundraw_counter;
    Animation myAnim;
    ImageView rate, love;
    Intent i;
    Context ctx;
    Integer xx = 0;
    dil dd;

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
        setContentView(R.layout.footbal_show_details);
        db = new Db(this);
        i = getIntent();
        typebold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Bold.ttf");
        typelight = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Light.ttf");
        typeregular = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Regular.ttf");
        typeextrabold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_ExtraBold.ttf");
        myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        name = (TextView) findViewById(R.id.name);
        date = (TextView) findViewById(R.id.date);
        watch = (TextView) findViewById(R.id.watch);
        rating = (TextView) findViewById(R.id.rating);
        title_toolbar = (TextView) findViewById(R.id.title_toolbar);
        back = (ImageView) findViewById(R.id.back);
        haladym = (TextView) findViewById(R.id.haladym);
        team1_name = (TextView) findViewById(R.id.team1_name);
        draw_name = (TextView) findViewById(R.id.draw_name);
        team2_name = (TextView) findViewById(R.id.team2_name);
        team1_counter = (TextView) findViewById(R.id.team1_counter);
        draw_counter = (TextView) findViewById(R.id.draw_counter);
        team2_counter = (TextView) findViewById(R.id.team2_counter);
        content = (JustifiedTextView) findViewById(R.id.content);
        banner = (RoundedImageView) findViewById(R.id.banner);
        //watch
        if (!db.isInWatch_futbol(i.getStringExtra("id"))) {
            db.inser_Watch_futbol(i.getStringExtra("id"));
            send_watched.get_Data(i.getStringExtra("id"), "futbol");
            watch.setText("" + (Integer.parseInt(i.getStringExtra("watch")) + 2));
        } else {
            watch.setText("" + (Integer.parseInt(i.getStringExtra("watch"))));
        }
//       viewPager.setVisibility(View.INVISIBLE);
        l1 = (LinearLayout) findViewById(R.id.l1);
        l2 = (LinearLayout) findViewById(R.id.l2);
        l3 = (LinearLayout) findViewById(R.id.l3);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

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


        rate = (ImageView) findViewById(R.id.rate);
        love = (ImageView) findViewById(R.id.love);

        images.add(i.getStringExtra("image1"));
        images.add(i.getStringExtra("image2"));
        images.add(i.getStringExtra("image3"));
        viewPager.setAdapter(new ViewPager_Adapter(this, images));
        name.setText(i.getStringExtra("name"));
        title_toolbar.setText(i.getStringExtra("name"));
        date.setText(i.getStringExtra("date"));

        x = i.getStringExtra("like");
        content.setText(i.getStringExtra("content"));
        team1_counter.setText(i.getStringExtra("team1"));
        team2_counter.setText(i.getStringExtra("team2"));
        draw_counter.setText(i.getStringExtra("draw"));
        id = i.getStringExtra("id");
        myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        name.setTypeface(typebold);
        date.setTypeface(typeregular);
        watch.setTypeface(typeregular);
        rating.setTypeface(typeregular);
        team1_counter.setTypeface(typeregular);
        team2_counter.setTypeface(typeregular);
        draw_counter.setTypeface(typeregular);
        haladym.setTypeface(typeregular);
        haladym.setText(dd.halanlarym);
        title_toolbar.setTypeface(typebold);
        rating.setText(x);
        team1_name.setText(i.getStringExtra("team_n1"));
        team2_name.setText(i.getStringExtra("team_n2"));
        team1_name.setTypeface(typeregular);
        team2_name.setTypeface(typeregular);
        draw_name.setTypeface(typeregular);
        draw_name.setText(i.getStringExtra("draw_n"));
        if (db.isInFutbol(id)) {
            love.setImageResource(R.drawable.heart_icon);
        } else {
            love.setImageResource(R.drawable.heart_bos);
        }
        if (db.isInRate_futbol(i.getStringExtra("id"))) {
            rate.setImageResource(R.drawable.finger_icon);
            rate.setEnabled(false);
        }
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.inser_Rate_futbol(i.getStringExtra("id"));
                int c = Integer.parseInt(x) + 1;
                x = Integer.toString(c);
                rating.setText(x);
                send_like.get_Data(i.getStringExtra("id"), "futbol");
                rate.setImageResource(R.drawable.finger_icon);
                rate.startAnimation(myAnim);
                rate.setEnabled(false);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=getIntent();
                if(i.getStringExtra("from").equals("football")) {
                    Intent intent = new Intent(footbal_show_details.this, gumenje_activity.class);
                    intent.putExtra("tab", "1");
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                }
                if(i.getStringExtra("from").equals("football_liked")) {
                    Intent intent = new Intent(footbal_show_details.this, like_activity_gumenje.class);
                    intent.putExtra("tab", "1");
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                }

            }
        });
        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                love.startAnimation(myAnim);
                if (!db.isInFutbol(id)) {
                    love.setImageResource(R.drawable.heart_icon);
                    db.inser_futbol(id, i.getStringExtra("image"), i.getStringExtra("image1"), i.getStringExtra("name"),
                            i.getStringExtra("watch"), i.getStringExtra("like"), i.getStringExtra("date"), i.getStringExtra("content"),
                            i.getStringExtra("team1"), i.getStringExtra("draw"), i.getStringExtra("team2"), i.getStringExtra("team_n1"), i.getStringExtra("draw_n"), i.getStringExtra("team_n2"));
                } else {
                    love.setImageResource(R.drawable.heart_bos);
                    db.delete_futbol(id);
                }
            }
        });


       /*  if (!db.get_footbal_vote().isEmpty()) {
            ArrayList<model_vote> id_vote = db.get_footbal_vote();
            for (int j = 0; j < id_vote.size(); j++) {
                if (id.equals(id_vote.get(j).getId())) {
                    find = id_vote.get(j).getVote();
                }
            }
        }
        */

        team1 = (ImageView) findViewById(R.id.team1_rate);
        team2 = (ImageView) findViewById(R.id.draw_rate);
        team3 = (ImageView) findViewById(R.id.team2_rate);


        model_vote s = db.get_footbal_vote(i.getStringExtra("id"));
        if (s.getVote().equals("1")) {
            team1.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.red), android.graphics.PorterDuff.Mode.SRC_IN);
        } else if (s.getVote().equals("2")) {
            team2.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.red), android.graphics.PorterDuff.Mode.SRC_IN);
        } else if (s.getVote().equals("3")) {
            team3.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.red), android.graphics.PorterDuff.Mode.SRC_IN);
        }


        int a = Integer.parseInt(team1_counter.getText().toString());
        team1_counter.setText(Integer.toString(a));

        count = Integer.parseInt(team1_counter.getText().toString()) + Integer.parseInt(draw_counter.getText().toString()) + Integer.parseInt(team2_counter.getText().toString());
        coundraw_counter = (double) count / (a);


        double persentage2 = 100 / coundraw_counter;
        int persentage = (int) persentage2;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)
                l1.getLayoutParams();
        params.weight = persentage;
        l1.setLayoutParams(params);


        int b = Integer.parseInt(draw_counter.getText().toString());
        if (b > 0) {
            coundraw_counter = (double) count / b;
            persentage2 = 100 / coundraw_counter;
            persentage = (int) persentage2;
            LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams)
                    l2.getLayoutParams();
            params2.weight = persentage;
            l2.setLayoutParams(params2);
        }

        int c = Integer.parseInt(team2_counter.getText().toString());
        if (c > 0) {
            coundraw_counter = (double) count / c;
            persentage2 = 100 / coundraw_counter;
            persentage = (int) persentage2;
            LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams)
                    l3.getLayoutParams();
            params3.weight = persentage;
            l3.setLayoutParams(params3);
        }
        if (s.getVote().isEmpty()) {
            if (voted == false) {
                team1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int a = Integer.parseInt(team1_counter.getText().toString());
                        team1_counter.setText(Integer.toString(a + 1));
                        team1.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.red), android.graphics.PorterDuff.Mode.SRC_IN);
                        team1.setEnabled(false);
                        team2.setEnabled(false);
                        team3.setEnabled(false);
                        voted = true;
                        db.insert_vote(i.getStringExtra("id"), "1");
                        count = Integer.parseInt(team1_counter.getText().toString()) + Integer.parseInt(draw_counter.getText().toString()) + Integer.parseInt(team2_counter.getText().toString());
                        coundraw_counter = (double) count / (a + 1);
                        send_team.get_Data(i.getStringExtra("id"), "-1", "futbol");

                        double persentage2 = 100 / coundraw_counter;
                        int persentage = (int) persentage2;
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)
                                l1.getLayoutParams();
                        params.weight = persentage;
                        l1.setLayoutParams(params);


                        int b = Integer.parseInt(draw_counter.getText().toString());
                        if (b > 0) {
                            coundraw_counter = (double) count / b;
                            persentage2 = 100 / coundraw_counter;
                            persentage = (int) persentage2;
                            LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams)
                                    l2.getLayoutParams();
                            params2.weight = persentage;
                            l2.setLayoutParams(params2);
                        }


                        int c = Integer.parseInt(team2_counter.getText().toString());
                        if (c > 0) {
                            coundraw_counter = (double) count / c;
                            persentage2 = 100 / coundraw_counter;
                            persentage = (int) persentage2;
                            LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams)
                                    l3.getLayoutParams();
                            params3.weight = persentage;
                            l3.setLayoutParams(params3);
                        }
                    }
                });
            }
        }
        if (s.getVote().isEmpty()) {
            if (voted == false) {
                team2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int a = Integer.parseInt(draw_counter.getText().toString());
                        draw_counter.setText(Integer.toString(a + 1));
                        team2.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.red), android.graphics.PorterDuff.Mode.SRC_IN);
                        voted = true;
                        team1.setEnabled(false);
                        team2.setEnabled(false);
                        team3.setEnabled(false);
                        db.insert_vote(i.getStringExtra("id"), "2");
                        send_team.get_Data(i.getStringExtra("id"), "0", "futbol");
                        count = Integer.parseInt(team1_counter.getText().toString()) + Integer.parseInt(draw_counter.getText().toString()) + Integer.parseInt(team2_counter.getText().toString());
                        coundraw_counter = (double) count / (a + 1);
                        double persentage2 = 100 / coundraw_counter;
                        int persentage = (int) persentage2;
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)
                                l2.getLayoutParams();
                        params.weight = persentage;
                        l2.setLayoutParams(params);

                        int b = Integer.parseInt(team1_counter.getText().toString());
                        if (b > 0) {
                            count = Integer.parseInt(team1_counter.getText().toString()) + Integer.parseInt(draw_counter.getText().toString()) + Integer.parseInt(team2_counter.getText().toString());
                            coundraw_counter = (double) count / b;
                            persentage2 = 100 / coundraw_counter;
                            persentage = (int) persentage2;
                            LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams)
                                    l1.getLayoutParams();
                            params2.weight = persentage;
                            l1.setLayoutParams(params2);
                        }
                        int c = Integer.parseInt(team2_counter.getText().toString());
                        if (c > 0) {
                            count = Integer.parseInt(team1_counter.getText().toString()) + Integer.parseInt(draw_counter.getText().toString()) + Integer.parseInt(team2_counter.getText().toString());

                            coundraw_counter = (double) count / c;
                            persentage2 = 100 / coundraw_counter;
                            persentage = (int) persentage2;
                            LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams)
                                    l3.getLayoutParams();
                            params3.weight = persentage;
                            l3.setLayoutParams(params3);
                        }
                    }
                });
            }
        }
        if (s.getVote().isEmpty()) {
            if (voted == false) {
                team3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int a = Integer.parseInt(team2_counter.getText().toString());
                        team2_counter.setText(Integer.toString(a + 1));
                        voted = true;
                        db.insert_vote(i.getStringExtra("id"), "3");
                        team3.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.red), android.graphics.PorterDuff.Mode.SRC_IN);
                        team1.setEnabled(false);
                        team2.setEnabled(false);
                        team3.setEnabled(false);
                        send_team.get_Data(i.getStringExtra("id"), "1", "futbol");
                        count = Integer.parseInt(team1_counter.getText().toString()) + Integer.parseInt(draw_counter.getText().toString()) + Integer.parseInt(team2_counter.getText().toString());
                        coundraw_counter = (double) count / (a + 1);
                        double persentage2 = 100 / coundraw_counter;
                        int persentage = (int) persentage2;
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)
                                l3.getLayoutParams();
                        params.weight = persentage;
                        l3.setLayoutParams(params);


                        int b = Integer.parseInt(draw_counter.getText().toString());
                        if (b > 0) {
                            count = Integer.parseInt(team1_counter.getText().toString()) + Integer.parseInt(draw_counter.getText().toString()) + Integer.parseInt(team2_counter.getText().toString());
                            coundraw_counter = (double) count / b;
                            persentage2 = 100 / coundraw_counter;
                            persentage = (int) persentage2;
                            LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams)
                                    l2.getLayoutParams();
                            params2.weight = persentage;
                            l2.setLayoutParams(params2);
                        }

                        int c = Integer.parseInt(team1_counter.getText().toString());
                        if (c > 0) {
                            count = Integer.parseInt(team1_counter.getText().toString()) + Integer.parseInt(draw_counter.getText().toString()) + Integer.parseInt(team2_counter.getText().toString());
                            coundraw_counter = (double) count / c;
                            persentage2 = 100 / coundraw_counter;
                            persentage = (int) persentage2;
                            LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams)
                                    l1.getLayoutParams();
                            params3.weight = persentage;
                            l1.setLayoutParams(params3);
                        }

                    }
                });
            }
        }

      /*  if(!db.isInFutbol(m.getId())) {
            holder.love.setImageResource(R.drawable.heart_icon);
            db.inser_futbol(m.getId(),m.getImage(),m.getImage1(),m.getName(),
                    m.getWatched(),m.getliked(),m.getDate(),m.getContent(),m.getTeam1(),m.getDraw(),m.getTeam2()
            );
        }else{db.delete_futbol(m.getId());     holder.love.setImageResource(R.drawable.heart_bos);}*/

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
                .create(footbal_show_details.this)
                .show();
    }

    @Override
    public void onPositiveButtonClicked(int i, @NotNull String s) {
        send_rating.get_Data(id, String.valueOf(i), "futbol");
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
        if(i.getStringExtra("from").equals("football")) {
            Intent intent = new Intent(footbal_show_details.this, gumenje_activity.class);
            intent.putExtra("tab", "1");
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
        }
        if(i.getStringExtra("from").equals("football_liked")) {
            Intent intent = new Intent(footbal_show_details.this, like_activity_gumenje.class);
            intent.putExtra("tab", "1");
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
        }*/
        finish();

    }
}

