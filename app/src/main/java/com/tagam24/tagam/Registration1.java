package com.tagam24.tagam;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tagam24.tagam.Constants.Constants;
import com.tagam24.tagam.Database.Db;


public class Registration1 extends AppCompatActivity {
    EditText name, phone, address;
    TextView info, register, beforenumber,title;
    ImageView send,back;
    Db db;
    Typeface typebold,typelight,typeregular,typeextrabold;
    dil dd;
    Animation myanim;
    String from;
    public  static Handler s1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new Db(this);
        dd=new dil();
        dd.set_text();

        if (db.get_color().equals("orange")) setTheme(R.style.AppTheme1);
        else if (db.get_color().equals("blue")) setTheme(R.style.AppTheme2);
        else if (db.get_color().equals("green")) setTheme(R.style.AppTheme3);
        else if (db.get_color().equals("pink")) setTheme(R.style.AppTheme4);
        else setTheme(R.style.AppTheme1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration1);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.place);
        send = findViewById(R.id.send_btn);
        info = findViewById(R.id.info);
        register = findViewById(R.id.register);
        beforenumber = findViewById(R.id.beforephone);
        back=(ImageView)findViewById(R.id.back);
        title=(TextView)findViewById(R.id.title);
        myanim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);

        typebold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Bold.ttf");
        typelight = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Light.ttf");
        typeregular = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Regular.ttf");
        typeextrabold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_ExtraBold.ttf");

s1=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        finish();
    }
};
        name.setHint(dil.adynyzy_girizin);
        name.setTypeface(typeregular);
        phone.setHint(dil.telefon_belginizi_girizini);
        phone.setTypeface(typeregular);
        address.setHint(dil.salgynyzy_girizin);
        address.setTypeface(typeregular);
        info.setTypeface(typeregular);
        register.setText(dil.registrasiya);
        register.setTypeface(typebold);
        beforenumber.setTypeface(typeregular);
        title.setText(dil.sahsy_otag);
        title.setTypeface(typebold);
        info.setText(dil.welcome);
        info.setTypeface(typelight);
 Intent i=getIntent();
 from=i.getStringExtra("from");
 if(from==null)from="";
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.startAnimation(myanim);
                finish();
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile = phone.getText().toString().trim();
                String addres = address.getText().toString();

                if (mobile.isEmpty() || mobile.length() < 8 || mobile.charAt(0) != '6') {
                    phone.setError(dil.belgini_dogry_girizin);
                    phone.requestFocus();
                    return;
                }
                else if (addres.isEmpty()) {
                    address.setError(dil.salgynyzy_girizin);
                    address.requestFocus();
                    return;
                }
                else
                    {
                        Intent intent = new Intent(Registration1.this, registration2.class);
                        send.startAnimation(myanim);
                        intent.putExtra("mobile", mobile);
                        intent.putExtra("name", name.getText().toString());
                        intent.putExtra("address", address.getText().toString());
                        intent.putExtra("from",from);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                    }




            }
        });
    }
    public void onBackPressed() {
        super.onBackPressed();
        Constants.iter=true;

/*
        Intent i=new Intent(this,dastawka_recycle.class);
        startActivity(i);
    */
        finish();}
}

