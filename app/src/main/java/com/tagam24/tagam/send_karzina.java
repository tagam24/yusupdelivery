package com.tagam24.tagam;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.tagam24.tagam.Constants.Constants;
import com.tagam24.tagam.Database.Db;
import com.tagam24.tagam.cafes_menu.Cafes_menu;
import com.tagam24.tagam.karzina.Karzina;
import com.tagam24.tagam.models.model_user;
import com.tagam24.tagam.network.get_user;
import com.tagam24.tagam.network.send_orders;

public class send_karzina extends AppCompatActivity {
    TextView name, address, number, name2, addres2, number2, name3, addres3, number3, title, zakaz_new, zakaz_old;
    EditText name1, address1, number1;
    MaterialRippleLayout s1, s2;
    Db db;
    dil dd;
    Context ctx;
    String sa = "0";
    public static Handler sender;

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
        setContentView(R.layout.send_karzina);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_new);
        Typeface typebold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Bold.ttf");
        Typeface typelight = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Light.ttf");
        Typeface typeregular = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Regular.ttf");
        Typeface typeextrabold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_ExtraBold.ttf");

        final LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.layout_old);
        title = (TextView) findViewById(R.id.title);
        title.setText(dd.zakazy_tassykla);
        zakaz_new = (TextView) findViewById(R.id.zakaz_new);
        zakaz_old = (TextView) findViewById(R.id.zakaz_old);
        name = (TextView) findViewById(R.id.old_name);
        name2 = (TextView) findViewById(R.id.old_name2);
        name3 = (TextView) findViewById(R.id.new_name);
        address = (TextView) findViewById(R.id.old_address);
        addres2 = (TextView) findViewById(R.id.old_address2);
        addres3 = (TextView) findViewById(R.id.new_addres);
        number = (TextView) findViewById(R.id.old_phone);
        number2 = (TextView) findViewById(R.id.old_phone2);
        number3 = (TextView) findViewById(R.id.new_number);
        if(Constants.user==null){
            Intent intent=getIntent();
            name2.setText(intent.getStringExtra("name"));
            addres2.setText( intent.getStringExtra("address"));
            number2.setText( intent.getStringExtra("mobile"));
            Constants.user=new model_user(name2.getText().toString(),addres2.getText().toString(),number2.getText().toString(),"");
        } else {
        name2.setText(Constants.user.getName());
        addres2.setText(Constants.user.getAddress());
        number2.setText(Constants.user.getPhone());}
        name1 = (EditText) findViewById(R.id.new_name2);
        address1 = (EditText) findViewById(R.id.new_addres2);
        number1 = (EditText) findViewById(R.id.new_number2);
        s1 = (MaterialRippleLayout) findViewById(R.id.effect1);
        s2 = (MaterialRippleLayout) findViewById(R.id.effect2);

        name.setText(dd.adynyz + ":");
        name3.setText(dd.adynyz + ":");
        addres3.setText(dd.salgynyz + ":");
        address.setText(dd.salgynyz + ":");
        number.setText(dd.belginiz + ": +993");
        number3.setText(dd.belginiz + ": +993");

        title.setTypeface(typebold);
        name.setTypeface(typebold);
        name3.setTypeface(typebold);
        addres3.setTypeface(typebold);
        address.setTypeface(typebold);
        number.setTypeface(typebold);
        number3.setTypeface(typebold);

        zakaz_old.setTypeface(typebold);
        zakaz_old.setText(dil.zakaz_kona);

        zakaz_new.setTypeface(typebold);
        zakaz_new.setText(dil.zakaz_taza);

        name2.setTypeface(typeregular);
        number2.setTypeface(typeregular);
        addres2.setTypeface(typeregular);
        name1.setTypeface(typeregular);
        number1.setTypeface(typeregular);
        address1.setTypeface(typeregular);

        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.number = Constants.user.getPhone();
                Constants.address = Constants.user.getAddress();
                Constants.name = Constants.user.getName();
                Constants.cafeName = Constants.cafesel.getName();
                dastawka_recycle.s3.sendEmptyMessage(1);
                Cafes_menu.s3.sendEmptyMessage(1);
                Karzina.s4.sendEmptyMessage(1);

                send_orders.get_Data();

                Intent intent = new Intent(send_karzina.this, zakaz_text.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("from","cart");
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            }
        });
        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sa.equals("0")) {
                    sa = "1";
                    linearLayout.setVisibility(View.VISIBLE);
                    linearLayout3.setVisibility(View.GONE);
                } else {
                    Constants.number = number1.getText().toString();
                    Constants.address = address1.getText().toString();
                    Constants.name = name1.getText().toString();
                    Constants.cafeName = Constants.cafesel.getName();

                    dastawka_recycle.s3.sendEmptyMessage(1);
                    Cafes_menu.s3.sendEmptyMessage(1);
                    Karzina.s4.sendEmptyMessage(1);
                    send_orders.get_Data();

                    Intent intent = new Intent(send_karzina.this, zakaz_text.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("from","cart");
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                }
            }
        });
        linearLayout.setVisibility(View.GONE);

        sender = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Constants.cart_list.clear();

            }
        };
    }
}
