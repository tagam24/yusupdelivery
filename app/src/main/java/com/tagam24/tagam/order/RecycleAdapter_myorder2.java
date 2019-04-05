package com.tagam24.tagam.order;

/**
 * Created by Sulik on 1/3/2019.
 */

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tagam24.tagam.Constants.Constants;
import com.tagam24.tagam.R;
import com.tagam24.tagam.dil;
import com.tagam24.tagam.network.Api;
import com.tagam24.tagam.network.get_myorders;
import com.tagam24.tagam.network.get_myorders_item;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;


public class RecycleAdapter_myorder2 extends RecyclerView.Adapter<RecycleAdapter_myorder2.Reklama_viewholder> {
    ArrayList<Model_myorder2> list1;
    Context cntx;
    dil dd;
    String table;
    Boolean b1 = false, b2 = false, b3 = false;
    int c1 = 0;

    public class Reklama_viewholder extends RecyclerView.ViewHolder {
        TextView name, price,number, date, status, place;
        LinearLayout l;
        RelativeLayout relativeLayout, relativeLayout1, relativeLayout2, relativeLayout_minus, relativeLayout_plus;
        LinearLayout.LayoutParams ls;
        ImageView star1, star2, star3, star4, star5;
        RoundedImageView imageView;

        public Reklama_viewholder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name_food_show);
            price = (TextView) itemView.findViewById(R.id.price_food_show);
            number=(TextView)itemView.findViewById(R.id.number);
            imageView = (RoundedImageView) itemView.findViewById(R.id.image);
        }
    }
    public RecycleAdapter_myorder2(ArrayList<Model_myorder2> items, Context ctx) {
        this.list1 = items;
        this.cntx = ctx;
        this.dd=new dil();
    }

    @Override
    public Reklama_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_myorder2, parent, false);
        final Reklama_viewholder view = new Reklama_viewholder(v);
        return view;

    }

    @Override
    public void onBindViewHolder(final Reklama_viewholder holder, final int position) {
        Typeface typebold = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_Bold.ttf");
        Typeface typelight = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_Light.ttf");
        Typeface typeregular = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_Regular.ttf");
        Typeface typeextrabold = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_ExtraBold.ttf");

        holder.name.setText(list1.get(position).getName());
        holder.name.setTypeface(typebold);
        holder.price.setText(list1.get(position).getPrice()+" "+dd.manat);
        holder.price.setTypeface(typeregular);
        holder.number.setText(list1.get(position).getCount()+" "+dil.shtuk);
        holder.number.setTypeface(typeregular);
        if(position==list1.size()-1&& Constants.iter==true){
            Constants.size=list1.size();
            get_myorders_item.get_Data();
            Log.d("geldi",""+position);
           }

        Glide.with(cntx)
                .load("http://"+ Api.url+"tagam24/images/" + list1.get(position).getImage()).asBitmap().fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.imageView);




    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public void setData(ArrayList<Model_myorder2> food){
        list1=food;
        notifyDataSetChanged();
    }
}
