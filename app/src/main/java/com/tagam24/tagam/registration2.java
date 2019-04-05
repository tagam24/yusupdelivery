package com.tagam24.tagam;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.iid.FirebaseInstanceId;
import com.tagam24.tagam.Constants.Constants;
import com.tagam24.tagam.Database.Db;
import com.tagam24.tagam.network.registration;
import com.tagam24.tagam.order.myorder1;

import java.util.concurrent.TimeUnit;

public class registration2 extends AppCompatActivity {
    Db db;
    dil dd;
    TextView name, title, info;
    EditText code;
    CardView send;
    ImageView back;
    Animation myanim;
    private FirebaseAuth mAuth;
    String mobile="",name1="",address="",token="";
    private String mVerificationId;
    Handler handler = new Handler();
    String from;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new Db(this);
        dd = new dil();
        dd.set_text();

        if (db.get_color().equals("orange")) setTheme(R.style.AppTheme1);
        else if (db.get_color().equals("blue")) setTheme(R.style.AppTheme2);
        else if (db.get_color().equals("green")) setTheme(R.style.AppTheme3);
        else if (db.get_color().equals("pink")) setTheme(R.style.AppTheme4);
        else setTheme(R.style.AppTheme1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration2);
        mAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        mobile = intent.getStringExtra("mobile");
        name1 = intent.getStringExtra("name");
        address = intent.getStringExtra("address");
        from=intent.getStringExtra("from");
         token= FirebaseInstanceId.getInstance().getToken();
        name = (TextView) findViewById(R.id.register);
        code = (EditText) findViewById(R.id.code);
        send = (CardView) findViewById(R.id.send);
        title = (TextView) findViewById(R.id.title);
        info = (TextView) findViewById(R.id.info);
        back = (ImageView) findViewById(R.id.back);

        myanim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);

        ;
        Typeface typebold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Bold.ttf");
        Typeface typelight = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Light.ttf");
        Typeface typeregular = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_Regular.ttf");
        Typeface typeextrabold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans_ExtraBold.ttf");

        name.setText(dil.belgini_tassykla);
        name.setTypeface(typebold);
        code.setHint(dil.kody_girizin);
        code.setTypeface(typeregular);
        info.setTypeface(typeregular);
        title.setText(dil.sahsy_otag);
        title.setTypeface(typebold);
        info.setText(dil.tassyklama_kody);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                back.startAnimation(myanim);
                finish();
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            }
        });
        sendVerificationCode(mobile);
        send.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        String code1 = code.getText().toString();
                                        if (code1.isEmpty() || code1.length() < 6) {
                                            code.setError("Enter valid code");
                                            code.requestFocus();
                                            return;
                                        }
                                        //verifying the code entered manually
                                        verifyVerificationCode(code1);
                                    }
                                }
        );

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                registration.get_Data(registration2.this,name1,address,mobile,FirebaseInstanceId.getInstance().getToken());
               if(!from.equals("cart")){ Intent intent = new Intent(registration2.this, MainActivity.class);
                startActivity(intent);} else {
                Intent intent = new Intent(registration2.this, send_karzina.class);
                   intent.putExtra("mobile", mobile);
                   intent.putExtra("name", name1);
                   intent.putExtra("address", address);
                startActivity(intent);}
                Registration1.s1.sendEmptyMessage(1);
                finish();
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
            }
        }, 60000); // 4 seconds
    }
    private void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+993" + mobile,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }
    //the callback to detect the verification status
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            //Getting the code sent by SMS
            String code1 = phoneAuthCredential.getSmsCode();

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code1 != null) {
                code.setText(code1);
                //verifying the code
                verifyVerificationCode(code1);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            Log.d("Code",s);
            //storing the verification id that is sent to the user
            mVerificationId = s;
        }
    };
    private void verifyVerificationCode(String code) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);

        //signing the user
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(registration2.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {Intent intent;
                            //verification successful we will start the profile activity
                            registration.get_Data(registration2.this,name1,address,mobile,FirebaseInstanceId.getInstance().getToken());
                            if(!from.equals("cart")){  intent = new Intent(registration2.this, MainActivity.class);
                                startActivity(intent);} else {
                                 intent = new Intent(registration2.this, send_karzina.class);
                                intent.putExtra("mobile", mobile);
                                intent.putExtra("name", name1);
                                intent.putExtra("address", address);
                                startActivity(intent);}

                            overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            Registration1.s1.sendEmptyMessage(1);
                            finish();
                        } else {

                            //verification unsuccessful.. display an error message

                            String message = "Somthing is wrong, we will fix it soon...";

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered...";
                            }


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


