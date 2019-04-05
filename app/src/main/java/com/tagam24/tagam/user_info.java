package com.tagam24.tagam;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.tagam24.tagam.Constants.Constants;
import com.tagam24.tagam.Database.Db;
import com.tagam24.tagam.cafes_menu.Cafes_menu;
import com.tagam24.tagam.network.delete_user;

public class user_info extends AppCompatActivity {
    Db db;
    dil dd;
    public static Context context;
    public static Handler s1;
    Animation myanim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        db = new Db(this);
        dd = new dil();
        dd.set_text();

        if (db.get_color().equals("orange")) setTheme(R.style.AppTheme1);
        else if (db.get_color().equals("blue")) setTheme(R.style.AppTheme2);
        else if (db.get_color().equals("green")) setTheme(R.style.AppTheme3);
        else if (db.get_color().equals("pink")) setTheme(R.style.AppTheme4);
        else setTheme(R.style.AppTheme1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        myanim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);


        TextView name = (TextView) findViewById(R.id.name);
        TextView number = (TextView) findViewById(R.id.phone);
        TextView addres = (TextView) findViewById(R.id.address);
        TextView date = (TextView) findViewById(R.id.date);
        TextView register = (TextView) findViewById(R.id.login);
        final ImageView back=(ImageView)findViewById(R.id.back);
        TextView title=(TextView)findViewById(R.id.title);

        Typeface typebold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Bold.ttf");
        Typeface typelight = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Light.ttf");
        Typeface typeregular = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Regular.ttf");
        Typeface typeextrabold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_ExtraBold.ttf");

        name.setTypeface(typeregular);
        number.setTypeface(typeregular);
        addres.setTypeface(typeregular);
        date.setTypeface(typeregular);
        register.setTypeface(typebold);
        title.setTypeface(typebold);

        name.setText(dil.maglumat_yok);
        number.setText(dil.maglumat_yok);
        addres.setText(dil.maglumat_yok);
        date.setText(dil.maglumat_yok);
        register.setText(dil.registrasiya);
        title.setText(dil.sahsy_otag);
        if(Constants.user!=null){
            name.setText(Constants.user.getName());
            number.setText(Constants.user.getPhone());
            addres.setText(Constants.user.getAddress());
            date.setText(Constants.user.getDate());
            register.setText(dil.unregister);
        }
        final MaterialRippleLayout login_card = (MaterialRippleLayout) findViewById(R.id.effect);
        login_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Constants.user==null){
                Intent intent=new Intent(user_info.this,Registration1.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);} else
                {Intent intent=new Intent(user_info.this,MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                    delete_user.get_Data();
                Constants.user=null;
                finish();}
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.startAnimation(myanim);
                Intent intent=getIntent();
                if (intent.getStringExtra("from").equals("main")){
                    MainActivity.s4.sendEmptyMessage(1);
                    finish();
                }
                if (intent.getStringExtra("from").equals("cafe")){
                    dastawka_recycle.s8.sendEmptyMessage(1);
                    finish();
                }

                finish();
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            }
        });


    }
    public void onBackPressed() {
        super.onBackPressed();
        Constants.iter=true;
        Intent intent=getIntent();
        if (intent.getStringExtra("from").equals("main")){
            MainActivity.s4.sendEmptyMessage(1);
            finish();
        }
        if (intent.getStringExtra("from").equals("cafe")){
            dastawka_recycle.s8.sendEmptyMessage(1);
            finish();
        }

        finish();
        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);

    }
}
