package com.tagam24.tagam;
/*
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myapplication.R;

public class Registration extends AppCompatActivity {
          EditText name,phone,address;
    ImageView send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name=(EditText)findViewById(R.id.name);
        phone=(EditText)findViewById(R.id.phone);
        address=(EditText)findViewById(R.id.place);
        send=(ImageView)findViewById(R.id.send_btn);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile = phone.getText().toString().trim();

                if(mobile.isEmpty() || mobile.length() <7){
                    phone.setError("Enter a valid mobile");
                    phone.requestFocus();
                    return;
                }

                Intent intent = new Intent(Registration.this, Verifier.class);
                intent.putExtra("mobile", mobile);
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("address", address.getText().toString());
                startActivity(intent);
            }
        });
            }

    }

*/