package com.tagam24.tagam;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.tagam24.tagam.Database.Db;
import com.tagam24.tagam.order.myorder1;

public class zakaz_text extends AppCompatActivity {
    Context ctx;
    Db db;
    dil dd;
    Handler handler = new Handler();

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

        Typeface typebold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Bold.ttf");
        Typeface typelight = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Light.ttf");
        Typeface typeregular = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Regular.ttf");
        Typeface typeextrabold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_ExtraBold.ttf");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zakaz_text);

        TextView textView = (TextView) findViewById(R.id.text_main);
        textView.setText(dd.txt_status_order);
        textView.setTypeface(typebold);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(zakaz_text.this, myorder1.class);
                intent.putExtra("from","cart");
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            }
        }, 3000); // 4 seconds
    }

}

