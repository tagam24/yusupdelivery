package com.tagam24.tagam;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.dd.ShadowLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.tagam24.tagam.Constants.Constants;
import com.tagam24.tagam.Database.Db;
import com.tagam24.tagam.aksiya.aksiya_recycle;
import com.tagam24.tagam.cafe_bar.Kafe_bar_mod;
import com.tagam24.tagam.cafe_bar.cafe_recycle;
import com.tagam24.tagam.cafe_bar.show_details_bars;
import com.tagam24.tagam.gumenje.gumenje_activity;
import com.tagam24.tagam.karzina.Karzina;
import com.tagam24.tagam.like_activity.like_activity;
import com.tagam24.tagam.models.Model_Cafe;
import com.tagam24.tagam.models.model_banner;
import com.tagam24.tagam.network.Api;
import com.tagam24.tagam.network.get_banner;
import com.tagam24.tagam.network.get_main;
import com.tagam24.tagam.network.get_user;
import com.tagam24.tagam.order.myorder1;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private static final Integer[] XMEN = {R.drawable.check_icon};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();
    Handler create;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    public static Context ctx;
    ArrayList<Integer> list_bar;
    Animation myAnim;
    Typeface typebold, typeextrabold, typeregular, typelight;
    ImageView dostawka, aksiya, kafe, toy, gumenje;
    ImageView imageView_home, imageView_favorite, imageView_location, imageView_user, imageView_karzina;
    private ArrayList<ImageModel> imageModelArrayList;
    TextView count;
    Db db;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    SliderLayout mDemoSlider;
    public static Handler s1, s2, s3, s4,s5;
    dil dd;
    //  ViewPager_Adapter v;

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
        setContentView(R.layout.navigation_draw);
        FirebaseApp.initializeApp(this);
        FirebaseMessaging.getInstance().subscribeToTopic("test");
        FirebaseInstanceId.getInstance().getToken();
        if (FirebaseInstanceId.getInstance().getToken() == null) {
        } else get_user.get_Data();
        typebold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Bold.ttf");
        typelight = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Light.ttf");
        typeregular = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Regular.ttf");
        typeextrabold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_ExtraBold.ttf");
        //SDK VERSION


        //   Constants.banner.add(new model_banner("1.jpg","asd","asd","asd","asd"));
        //     Constants.banner.add(new model_banner("1.jpg","asd","asd","asd","asd"));
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        list_bar = new ArrayList<>();
        list_bar.add(0);
        list_bar.add(0);
        list_bar.add(0);
        list_bar.add(0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);



        gui();
        listener();
        imageView_home.setImageResource(R.drawable.home_icon);
s2=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        count.setText("" + Constants.cart_list.size());
    }
};
        s1 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                init1();
            }
        };
        s3 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                setMainImages();
            }
        };
        if (Constants.banner.size() == 0) get_banner.get_Data();
        else init1();
        if (Constants.mainImages.size() == 0) get_main.get_Data();
        else setMainImages();

        count.setText("" + Constants.cart_list.size());
        s4 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                imageView_home.setImageResource(R.drawable.home_icon);
                imageView_home.setEnabled(false);
                imageView_user.setImageResource(R.drawable.user_white);
                imageView_favorite.setImageResource(R.drawable.heart_white);
                imageView_location.setImageResource(R.drawable.map_white);

            }
        };
        s5=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                finish();

            }
        };
    }

    private ArrayList<ImageModel> populateList() {

        ArrayList<ImageModel> list = new ArrayList<>();

        for (int i = 0; i < XMEN.length; i++) {
            ImageModel imageModel = new ImageModel();
            imageModel.setImage_drawable(XMEN[i]);
            list.add(imageModel);
        }

        return list;
    }


    public void listener() {
        MaterialRippleLayout effect1 = (MaterialRippleLayout) findViewById(R.id.effect1);
        effect1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, dastawka_recycle.class);
                Constants.aksiya = "";
                startActivity(intent);
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);

            }


        });
        MaterialRippleLayout effect2 = (MaterialRippleLayout) findViewById(R.id.effect2);
        effect2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, aksiya_recycle.class);
                startActivity(intent);
                Constants.aksiya = "1";
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);

            }


        });
        MaterialRippleLayout effect3 = (MaterialRippleLayout) findViewById(R.id.effect3);
        effect3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, cafe_recycle.class);
                Constants.cafemy = "0";
                startActivity(intent);
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);

            }


        });

        MaterialRippleLayout effect4 = (MaterialRippleLayout) findViewById(R.id.effect4);
        effect4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, cafe_recycle.class);
                Constants.cafemy = "1";
                startActivity(intent);
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);

            }


        });
        MaterialRippleLayout effect5 = (MaterialRippleLayout) findViewById(R.id.effect5);
        effect5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, gumenje_activity.class);
                intent.putExtra("tab", "0");
                startActivity(intent);
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);

            }


        });


        final CardView relativeLayout_karzina = (CardView) findViewById(R.id.layout_karzina1);

        relativeLayout_karzina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout_karzina.startAnimation(myAnim);
                Intent intent = new Intent(MainActivity.this, Karzina.class);
                intent.putExtra("from", "main");
                startActivity(intent);

                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);

            }
        });


        RelativeLayout imageView_color = (RelativeLayout) findViewById(R.id.color_circle);
        imageView_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout imageView_color = (RelativeLayout) findViewById(R.id.color_circle);
                imageView_color.startAnimation(myAnim);
                final Dialog d = new Dialog(MainActivity.this);
                d.requestWindowFeature(Window.FEATURE_NO_TITLE);
                d.setContentView(R.layout.color_dialog_card);
                TextView textView = d.findViewById(R.id.text_color);
                textView.setText(dd.tema);
                textView.setTypeface(typeregular);
                RelativeLayout cardview1 = d.findViewById(R.id.color1);
                RelativeLayout cardview2 = d.findViewById(R.id.color2);
                RelativeLayout cardview3 = d.findViewById(R.id.color3);
                RelativeLayout cardview4 = d.findViewById(R.id.color4);
                TextView yza = d.findViewById(R.id.text_yza_color);
                yza.setTypeface(typelight);
                yza.setText(dd.yza);

                ImageView image1 = d.findViewById(R.id.check1);
                ImageView image2 = d.findViewById(R.id.check2);
                ImageView image3 = d.findViewById(R.id.check3);
                ImageView image4 = d.findViewById(R.id.check4);
                d.show();

                if (db.get_color().equals("")) {
                    image1.setVisibility(View.VISIBLE);
                    db.inser_color("orange");

                } else if (db.get_color().equals("green")) image3.setVisibility(View.VISIBLE);
                else if (db.get_color().equals("orange")) image1.setVisibility(View.VISIBLE);
                else if (db.get_color().equals("blue")) image2.setVisibility(View.VISIBLE);
                else if (db.get_color().equals("pink")) image4.setVisibility(View.VISIBLE);

                cardview1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setTheme(R.style.AppTheme1);
                        db.inser_color("orange");
                        d.dismiss();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                    }
                });
                cardview2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setTheme(R.style.AppTheme2);
                        db.inser_color("blue");
                        d.dismiss();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                    }
                });
                cardview3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setTheme(R.style.AppTheme3);
                        db.inser_color("green");
                        d.dismiss();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                    }
                });
                cardview4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setTheme(R.style.AppTheme4);
                        db.inser_color("pink");
                        d.dismiss();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                    }
                });
                yza.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        d.dismiss();
                    }
                });
            }
        });

        TextView imageView_flag = (TextView) findViewById(R.id.flag);
        imageView_flag.setText(dd.tm_ru);
        imageView_flag.setTypeface(typeregular);
        imageView_flag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView imageView_flag = (TextView) findViewById(R.id.flag);
                imageView_flag.startAnimation(myAnim);
                final Dialog d = new Dialog(MainActivity.this);
                d.requestWindowFeature(Window.FEATURE_NO_TITLE);
                d.setContentView(R.layout.flag_dialog_card);
                TextView text_tirle = (TextView) d.findViewById(R.id.text_flag);
                TextView text_yza = (TextView) d.findViewById(R.id.text_yza);
                TextView tm_dili = (TextView) d.findViewById(R.id.tm_dili);
                TextView rus_dili = (TextView) d.findViewById(R.id.rus_dili);
                text_tirle.setText(dd.dili_sayla);
                text_yza.setText(dd.yza);
                tm_dili.setText(dd.tm_dili);
                rus_dili.setText(dd.ru_dili);
                d.show();
                text_yza.setTypeface(typelight);
                text_tirle.setTypeface(typeregular);
                rus_dili.setTypeface(typeregular);
                tm_dili.setTypeface(typeregular);
                LinearLayout linearLayout_tm = (LinearLayout) d.findViewById(R.id.layout_tm);
                LinearLayout linearLayout_ru = (LinearLayout) d.findViewById(R.id.layout_rm);

                if (db.get_dil().equals("ru")) {
                    linearLayout_ru.setBackgroundColor(getResources().getColor(R.color.gray));
                } else {
                    linearLayout_tm.setBackgroundColor(getResources().getColor(R.color.gray));
                }
                linearLayout_tm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.inser_dil("tm");
                        d.dismiss();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                    }
                });

                linearLayout_ru.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.inser_dil("ru");
                        d.dismiss();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                    }
                });
                text_yza.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        d.dismiss();
                    }
                });

            }
        });

        imageView_home.setImageResource(R.drawable.home_icon);
        imageView_home.setEnabled(false);


        imageView_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView_favorite.startAnimation(myAnim);
                if (list_bar.get(1) == 0) {
                    imageView_favorite.setImageResource(R.drawable.heart_icon);
                    list_bar.set(1, 1);


                }
                Intent i = new Intent(MainActivity.this, like_activity.class);
                i.putExtra("from", "main");
                startActivity(i);
                finish();

                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);

                imageView_location.setImageResource(R.drawable.map_white);
                imageView_home.setImageResource(R.drawable.home_white);
                imageView_user.setImageResource(R.drawable.user_white);

                list_bar.set(0, 0);
                list_bar.set(2, 0);
                list_bar.set(3, 0);


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
                imageView_home.setImageResource(R.drawable.home_white);
                imageView_favorite.setImageResource(R.drawable.heart_white);
                imageView_user.setImageResource(R.drawable.user_white);
                Intent i = new Intent(MainActivity.this, myorder1.class);
                i.putExtra("from","main");
                startActivity(i);
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
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
                    Intent i = new Intent(MainActivity.this, user_info.class);
                    i.putExtra("from","main");
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                }
                imageView_location.setImageResource(R.drawable.map_white);
                imageView_favorite.setImageResource(R.drawable.heart_white);
                imageView_home.setImageResource(R.drawable.home_white);

                list_bar.set(1, 0);
                list_bar.set(2, 0);
                list_bar.set(0, 0);

            }
        });
    }

    public void gui() {
        dostawka = (ImageView) findViewById(R.id.dostawka);
        aksiya = (ImageView) findViewById(R.id.aksiya_layout_main_window);
        kafe = (ImageView) findViewById(R.id.kafe);
        toy = (ImageView) findViewById(R.id.toy_hyzmatlar);
        count = (TextView) findViewById(R.id.counter_basket1);
        gumenje = (ImageView) findViewById(R.id.gumenje_hyzmatlar);
        imageView_location = (ImageView) findViewById(R.id.location_button_main_window);
        imageView_favorite = (ImageView) findViewById(R.id.favorite_button_main_window);
        imageView_user = (ImageView) findViewById(R.id.user_button_main_window);
        imageView_home = (ImageView) findViewById(R.id.home_button_main_window);
        imageView_karzina = (ImageView) findViewById(R.id.karzina_button_main_window);


        myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);


        TextView textView_title = (TextView) findViewById(R.id.title_app);
        textView_title.setTypeface(typeextrabold);

        TextView textView_dostawka = (TextView) findViewById(R.id.delivery_name_layout_main_window);
        textView_dostawka.setText(dil.tagam_eltip_bermek);
        textView_dostawka.setTypeface(typebold);

        TextView textView_aksiya = (TextView) findViewById(R.id.aksiya_name_layout_main_window);
        textView_aksiya.setTypeface(typebold);
        textView_aksiya.setText(dil.Arzanladyshlar);

        TextView textView_cafe = (TextView) findViewById(R.id.cafe_name_layout_main_window);
        textView_cafe.setTypeface(typebold);
        textView_cafe.setText(dil.Kafe_Bar);

        TextView textView_toy = (TextView) findViewById(R.id.toy_name_layout_main_window);
        textView_toy.setTypeface(typebold);
        textView_toy.setText(dil.Toy_Hymzatlar);

        TextView textView_dostawka2 = (TextView) findViewById(R.id.delivery_content_layout_main_window);
        textView_dostawka2.setTypeface(typelight);
        textView_dostawka2.setText(dil.Eltip_Bermek_Hyzmaty1);

        TextView textView_aksiya2 = (TextView) findViewById(R.id.aksiya_content_layout_main_window);
        textView_aksiya2.setTypeface(typelight);
        textView_aksiya2.setText(dil.Arzanladyshlar1);

        TextView textView_cafe2 = (TextView) findViewById(R.id.cafe_content_layout_main_window);
        textView_cafe2.setTypeface(typelight);
        textView_cafe2.setText(dil.Kafe_Bar1);

        TextView textView_toy2 = (TextView) findViewById(R.id.toy_content_layout_main_window);
        textView_toy2.setTypeface(typelight);
        textView_toy2.setText(dil.Toy_Hymzatlar1);

        TextView gumenje = (TextView) findViewById(R.id.gumenje_name_layout_main_window);
        gumenje.setTypeface(typebold);
        gumenje.setText(dil.guymenje1);

        TextView gumenje2 = (TextView) findViewById(R.id.gumenje_content_layout_main_window);
        gumenje2.setTypeface(typelight);
        gumenje2.setText(dil.guymenje2);


    }

    void setMainImages() {
        Glide.with(this)
                .load("http://" + Api.url + "tagam24/images/" + Constants.mainImages.get(0)).asBitmap().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.dostawka)
                .into(dostawka);
        Glide.with(this)
                .load("http://" + Api.url + "tagam24/images/" + Constants.mainImages.get(1)).asBitmap().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ksiy)
                .into(aksiya);
        Glide.with(this)
                .load("http://" + Api.url + "tagam24/images/" + Constants.mainImages.get(2)).asBitmap().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.cafe_bar)
                .into(kafe);
        Glide.with(this)
                .load("http://" + Api.url + "tagam24/images/" + Constants.mainImages.get(3)).asBitmap().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.toy_mekany)
                .into(toy);
        Glide.with(this)
                .load("http://" + Api.url + "tagam24/images/" + Constants.mainImages.get(4)).asBitmap().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.gumenje)
                .into(gumenje);
    }

    void init1() {
        final HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        Log.d("bannersize", "" + Constants.banner.size());
        TextSliderView textSliderView = new TextSliderView(this);
        // initialize a SliderLayout
        textSliderView
                .description("")
                .image(R.drawable.banner1)
                .setScaleType(BaseSliderView.ScaleType.Fit);
        mDemoSlider.addSlider(textSliderView);
        textSliderView = new TextSliderView(this);
        textSliderView
                .description("")
                .image(R.drawable.banner2)
                .setScaleType(BaseSliderView.ScaleType.Fit);
        mDemoSlider.addSlider(textSliderView);
        for (final Kafe_bar_mod name : Constants.banner) {

       textSliderView = new TextSliderView(this);
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
                          /*      if(n.getId().equals(slider.getBundle().toString()))
                                    m1=n;}
                            Intent intent=new Intent(MainActivity.this, show_details_bars.class);
                            intent.putExtra("id",m1.getId());
                            intent.putExtra("name",m1.getName());
                            intent.putExtra("image",m1.getImage());
                            intent.putExtra("image1",m1.getImage1());
                            intent.putExtra("image2",m1.getImage2());
                            intent.putExtra("image3",m1.getImage3());
                            intent.putExtra("content",m1.getContent());
                            intent.putExtra("watch",m1.getWatch());
                            intent.putExtra("n_people",m1.getN_people());
                            intent.putExtra("s_people",m1.getS_people());
                            intent.putExtra("rating",m1.getRating());
                            intent.putExtra("address",m1.getAddress());
                            intent.putExtra("work_time",m1.getWork_time());
                            intent.putExtra("work_data",m1.getWork_data());
                            intent.putExtra("karta_image",m1.getKarta_image());
                            intent.putExtra("number",m1.getNumber());
                            startActivity(intent);
**/
                            }
                        }
                    });

            //add your extra information
            // Bundle d=new Bundle();
            // d.putInt("id",Integer.parseInt(name.getId()));
            // textSliderView.bundle(d);
            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(6000);

    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();

    }
}




