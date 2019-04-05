package com.tagam24.tagam.order;

/**
 * Created by Sulik on 1/3/2019.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tagam24.tagam.Constants.Constants;
import com.tagam24.tagam.MainActivity;
import com.tagam24.tagam.R;
import com.tagam24.tagam.cafes_menu.MyBounceInterpolator;
import com.tagam24.tagam.dastawka_recycle;
import com.tagam24.tagam.dil;
import com.tagam24.tagam.gumenje.recept;
import com.tagam24.tagam.karzina.Karzina;
import com.tagam24.tagam.models.Model_Food;
import com.tagam24.tagam.network.Api;
import com.tagam24.tagam.network.get_myorders;
import com.tagam24.tagam.network.get_reciept;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;


public class RecycleAdapter_myorder1 extends RecyclerView.Adapter<RecycleAdapter_myorder1.Reklama_viewholder> {
    ArrayList<Model_myorder1> list1;
    Context cntx;
    String table;
    Boolean b1 = false, b2 = false, b3 = false;
    int c1 = 0;

    public class Reklama_viewholder extends RecyclerView.ViewHolder {
        TextView name, price, date, status,place;
        LinearLayout l;
        RelativeLayout relativeLayout, relativeLayout1, relativeLayout2, relativeLayout_minus, relativeLayout_plus;
        LinearLayout.LayoutParams ls;

        public Reklama_viewholder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.title);
            price = (TextView) itemView.findViewById(R.id.price);
            date = (TextView) itemView.findViewById(R.id.date);
            status = (TextView) itemView.findViewById(R.id.status);
            place = (TextView) itemView.findViewById(R.id.place);

        }
    }

    public RecycleAdapter_myorder1(ArrayList<Model_myorder1> items, Context ctx) {
        this.list1 = items;
        this.cntx = ctx;
    }

    @Override
    public Reklama_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_sargyt, parent, false);
        final Reklama_viewholder view = new Reklama_viewholder(v);
        return view;

    }

    @Override
    public void onBindViewHolder(final Reklama_viewholder holder, final int position) {
        holder.name.setText("Sargyt №"+list1.get(position).getId());
        holder.date.setText(list1.get(position).getDate());
       switch (list1.get(position).getStatus()){
           case "0":
               holder.status.setText(dil.garashylyar);
               break;
           case "1":
               holder.status.setText(dil.kabul_edildi);
               break;
           case "2":
               holder.status.setText(dil.yolda);
               break;
           case "3":
               holder.status.setText(dil.dostawleno);
               break;
           case "4":
               holder.status.setText(dil.kabul_edilmedi);
               holder.status.setTextColor(Color.RED);
               break;
       }

        holder.price.setText(list1.get(position).getPrice());
        holder.place.setText(list1.get(position).getName());
        if(position==list1.size()-1&& Constants.iter==true){
            Constants.size=list1.size();
            get_myorders.get_Data();

            Log.d("geldi",""+position);

        }

        Typeface typebold = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_Bold.ttf");
        Typeface typelight = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_Light.ttf");
        Typeface typeregular = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_Regular.ttf");
        Typeface typeextrabold = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_ExtraBold.ttf");

        holder.status.setTypeface(typeregular);
        holder.date.setTypeface(typeregular);
        holder.price.setTypeface(typeregular);
        holder.place.setTypeface(typeregular);
        holder.name.setTypeface(typebold);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(cntx,myorder2.class);
                Constants.order_id=list1.get(position).getId();
                cntx.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public void setData(ArrayList<Model_myorder1> food){
        list1=food;
        notifyDataSetChanged();
    }
}
