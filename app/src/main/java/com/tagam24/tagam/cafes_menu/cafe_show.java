package com.tagam24.tagam.cafes_menu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.nfc.Tag;
import android.provider.ContactsContract;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.codesgood.views.JustifiedTextView;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.tagam24.tagam.Constants.Constants;
import com.tagam24.tagam.Database.Db;
import com.tagam24.tagam.MainActivity;
import com.tagam24.tagam.R;
import com.tagam24.tagam.cafe_bar.ViewPager_Adapter;
import com.tagam24.tagam.cafe_bar.cafe_recycle;
import com.tagam24.tagam.dil;
import com.tagam24.tagam.models.model_banner;
import com.tagam24.tagam.network.Api;
import com.tagam24.tagam.network.send_rating;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;
import com.tagam24.tagam.network.send_watched;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class cafe_show extends AppCompatActivity implements RatingDialogListener {
    ImageView call, love, rating, karta, back;
    TextView address, time, number, days, rating_tx, title, title_toolbar, haladym, watch;
    JustifiedTextView main, content;
    ArrayList<String> images = new ArrayList<>();
    ViewPager viewPager;
    String id;
    String check_delivery_price;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Db db;
    Toolbar toolbar;
    public static Context ctx;
    Intent i;
    LinearLayout linearLayout;
    dil dd;
    Animation myAnim;
    SliderLayout mDemoSlider;

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
        setContentView(R.layout.activity_cafe_bar_show_detail);
        Typeface typebold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Bold.ttf");
        Typeface typelight = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Light.ttf");
        Typeface typeregular = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Regular.ttf");
        Typeface typeextrabold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_ExtraBold.ttf");

        myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        call = (ImageView) findViewById(R.id.call);
        love = (ImageView) findViewById(R.id.image_love);
        rating = (ImageView) findViewById(R.id.image_rating);
        rating_tx = (TextView) findViewById(R.id.rating);
        karta = (ImageView) findViewById(R.id.karta);
        address = (TextView) findViewById(R.id.address);
        title = (TextView) findViewById(R.id.title);
        time = (TextView) findViewById(R.id.time);
        
        title.setText(Constants.cafe_show.getName());
        
        number = (TextView) findViewById(R.id.number);
        haladym = (TextView) findViewById(R.id.haladym);
        title_toolbar = (TextView) findViewById(R.id.title_toolbar);
        days = (TextView) findViewById(R.id.days);
        back = (ImageView) findViewById(R.id.back);
        watch = (TextView) findViewById(R.id.watch);
        content = (JustifiedTextView) findViewById(R.id.content);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + i.getStringExtra("number")));
                startActivity(intent);
            }
        });
        db = new Db(this);
        ctx = this;

        
        id = i.getStringExtra("id");
        title.setText(Constants.cafe_show.getName());
        images.add(i.getStringExtra("image1"));
        images.add(i.getStringExtra("image2"));
        images.add(i.getStringExtra("image3"));
        rating_tx.setText(Constants.cafe_show.getRating());
        viewPager.setAdapter(new ViewPager_Adapter(this, images));
        content.setText(Constants.cafe_show.getContent());
        i.getStringExtra("watch");
        i.getStringExtra("n_people");
        i.getStringExtra("rating");
        address.setText(Constants.cafe_show.getAddress());
        time.setText(Constants.cafe_show.getWork_start());
        days.setText(Constants.cafe_show.getWork_days());
        i.getStringExtra("karta_image");
        number.setText(Constants.cafe_show.getNumber());
        title_toolbar.setText(Constants.cafe_show.getName());
        haladym.setText(dd.halanlarym);

        title.setTypeface(typebold);
        title_toolbar.setTypeface(typebold);
        haladym.setTypeface(typeregular);
        content.setTypeface(typeregular);
        address.setTypeface(typeregular);
        time.setTypeface(typeregular);
        days.setTypeface(typeregular);
        number.setTypeface(typeregular);
        watch.setTypeface(typeregular);



        title.setText(Constants.cafe_show.getName());
        linearLayout = (LinearLayout) findViewById(R.id.price_l);
        if (i.getStringExtra("from").equals("cafe_bar")) {
            linearLayout.setVisibility(View.GONE);
        } else {

        }
        Glide.with(getApplicationContext())
                .load("http://" + Api.url + "tagam24/images/" + i.getStringExtra("karta_image")).asBitmap().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(karta);
        clickers();

        if (!db.isInWatch_cafe_bar(i.getStringExtra("id"))) {
            db.inser_Watch_cafe_bar(i.getStringExtra("id"));
            send_watched.get_Data(i.getStringExtra("id"), "kafe_bar");
            watch.setText("" + (Integer.parseInt(i.getStringExtra("watch")) + 2));
        } else {
            watch.setText(Constants.cafe_show.getWatch());
        }

        if (!db.isInCafebar(id)) {
            love.setImageResource(R.drawable.heart_bos);
        } else
            love.setImageResource(R.drawable.heart_icon);
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

    void clickers() {
        if (db.isInRate_cafe_bar( id)) {
            rating.setImageResource(R.drawable.star_icon);
            rating.setEnabled(false);
        }

        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!db.isInCafebar(id)) {
                    db.inser_kafe_bar(id, i.getStringExtra("image"), i.getStringExtra("image1"),
                            i.getStringExtra("image2"), i.getStringExtra("image3"), i.getStringExtra("name"), i.getStringExtra("address"),
                            i.getStringExtra("n_people"), i.getStringExtra("s_people"), i.getStringExtra("rating"), i.getStringExtra("watch"),
                            i.getStringExtra("work_time"), i.getStringExtra("work_data"), Constants.cafemy, i.getStringExtra("karta_image"),
                            i.getStringExtra("number"), i.getStringExtra("content"));
                }
                love.setImageResource(R.drawable.heart_icon);
                love.startAnimation(myAnim);
            }
        });
        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rating.startAnimation(myAnim);
                showDialog();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.setAnimation(myAnim);
                finish();
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
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
                .create(cafe_show.this)
                .show();
    }

   /* void init1() {
        final HashMap<String, Integer> file_maps = new HashMap<String, Integer>();

        Log.d("bannersize", "" + Constants.banner.size());

        for (final Kafe_bar_mod name : Constants.banner) {

            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name.getName())
                    .image("http://" + Api.url + "tagam24/images/" + name.getImage())
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {
                            final HashMap<String, Integer> file_maps = new HashMap<String, Integer>();

                            Log.d("bannersize", "" + Constants.banner.size());

                            for (final Kafe_bar_mod name : Constants.banner) {

                                TextSliderView textSliderView = new TextSliderView(cafe_show.this);
                                // initialize a SliderLayout
                                textSliderView
                                        .description(name.getName())
                                        .image("http://" + Api.url + "tagam24/images/" + name.getImage())
                                        .setScaleType(BaseSliderView.ScaleType.Fit)
                                        .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                            @Override
                                            public void onSliderClick(BaseSliderView slider) {
                                                Kafe_bar_mod m1 = null;
                                                for (Kafe_bar_mod n : Constants.banner) {
                                                    if (n.getId().equals(slider.getBundle().toString()))
                                                        m1 = n;
                                                }
                                                Intent intent = new Intent(cafe_show.this, show_details_bars.class);
                                                intent.putExtra("id", m1.getId());
                                                intent.putExtra("name", m1.getName());
                                                intent.putExtra("image", m1.getImage());
                                                intent.putExtra("image1", m1.getImage1());
                                                intent.putExtra("image2", m1.getImage2());
                                                intent.putExtra("image3", m1.getImage3());
                                                intent.putExtra("content", m1.getContent());
                                                intent.putExtra("watch", m1.getWatch());
                                                intent.putExtra("n_people", m1.getN_people());
                                                intent.putExtra("s_people", m1.getS_people());
                                                intent.putExtra("rating", m1.getRating());
                                                intent.putExtra("address", m1.getAddress());
                                                intent.putExtra("work_time", m1.getWork_time());
                                                intent.putExtra("work_data", m1.getWork_data());
                                                intent.putExtra("karta_image", m1.getKarta_image());
                                                intent.putExtra("number", m1.getNumber());
                                                startActivity(intent);


                                            }
                                        });

                                //add your extra information
                                Bundle d = new Bundle();
                                d.putInt("id", Integer.parseInt(name.getId()));
                                textSliderView.bundle(d);
                                mDemoSlider.addSlider(textSliderView);
                            }

                            mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
                            mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                            mDemoSlider.setCustomAnimation(new DescriptionAnimation());
                            mDemoSlider.setDuration(6000);

                        }

                    });

            //add your extra information
            textSliderView.bundle(new Bundle());
            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(6000);

    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       /* Intent intent = new Intent(show_details_bars.this, cafe_recycle.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
        */
        // cafe_recycle.s5.sendEmptyMessage(1);

        finish();
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
    }

    @Override
    public void onPositiveButtonClicked(int position, @NotNull String s) {
        rating.setImageResource(R.drawable.star_icon);
        String ss = Integer.toString(position);
        db.inser_Rate_cafe_bar(id);
        send_rating.get_Data(id, ss, "kafe_bar");
        rating.setEnabled(false);
    }

    @Override
    public void onNegativeButtonClicked() {
    }

    @Override
    public void onNeutralButtonClicked() {
    }
}


