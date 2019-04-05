package com.tagam24.tagam.like_activity;

/**
 * Created by Sulik on 1/3/2019.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
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

import com.balysv.materialripple.MaterialRippleLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tagam24.tagam.Constants.Constants;
import com.tagam24.tagam.Database.Db;
import com.tagam24.tagam.R;
import com.tagam24.tagam.cafes_menu.Cafes_menu;
import com.tagam24.tagam.dil;
import com.tagam24.tagam.models.Model_Cafe;
import com.tagam24.tagam.network.Api;
import com.tagam24.tagam.rate_nahar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class RecycleAdapter_cafeliked extends RecyclerView.Adapter<RecycleAdapter_cafeliked.Reklama_viewholder> {
    ArrayList<Model_Cafe> list1;
    Context cntx;
    String table;
    dil dd;
    Boolean b1 = false, b2 = false, b3 = false;
    Db db;

    public class Reklama_viewholder extends RecyclerView.ViewHolder {
        TextView name, address, worktime;
        ImageView star1, star2, star3, star4, star5;
        ImageView imageView, lock, love, work_Image;
        LinearLayout l1;
        ViewGroup.LayoutParams ls1, ls2;
        LinearLayout l;
        MaterialRippleLayout r1;
        RelativeLayout relativeLayout, relativeLayout1, relativeLayout2;
        LinearLayout.LayoutParams ls;

        public Reklama_viewholder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.cafe_name_show);
            address = (TextView) itemView.findViewById(R.id.cafe_addres_show);
            worktime = (TextView) itemView.findViewById(R.id.time);
            work_Image = (ImageView) itemView.findViewById(R.id.worktime_img);
            imageView = (ImageView) itemView.findViewById(R.id.imag_cafe_show);
            star1 = (ImageView) itemView.findViewById(R.id.cafe_star_image1_show);
            star2 = (ImageView) itemView.findViewById(R.id.cafe_star_image2_show);
            star3 = (ImageView) itemView.findViewById(R.id.cafe_star_image3_show);
            star4 = (ImageView) itemView.findViewById(R.id.cafe_star_image4_show);
            star5 = (ImageView) itemView.findViewById(R.id.cafe_star_image5_show);
            l1 = (LinearLayout) itemView.findViewById(R.id.linear);
            r1 = (MaterialRippleLayout) itemView.findViewById(R.id.ripple);

            love = (ImageView) itemView.findViewById(R.id.love);
        }
    }

    public RecycleAdapter_cafeliked(ArrayList<Model_Cafe> items, Context ctx) {
        this.list1 = items;
        this.cntx = ctx;
        this.dd = new dil();
        this.db = new Db(cntx);
    }

    @Override
    public Reklama_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_kafes, parent, false);
        final Reklama_viewholder view = new Reklama_viewholder(v);
        return view;

    }

    @Override
    public void onBindViewHolder(final Reklama_viewholder holder, final int position) {
        holder.name.setText(list1.get(position).getName());
        final Model_Cafe m = list1.get(position);
        holder.love.setImageResource(R.drawable.heart_icon);
        holder.love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.delete_cafe(list1.get(position).getId());
                list1.remove(position);
                notifyDataSetChanged();
            }
        });
        String time2 = m.getWork_start();
        java.util.Date d = new java.util.Date();
        java.text.SimpleDateFormat ff = new java.text.SimpleDateFormat("HH.mm");
        ff.format(d);
        Typeface typebold = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_Bold.ttf");
        Typeface typelight = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_Light.ttf");
        Typeface typeregular = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_Regular.ttf");
        Typeface typeextrabold = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_ExtraBold.ttf");
        if (position == list1.size() - 1 && Constants.iter == true) {
            ///   Constants.size=list1.size();
            /// get_cafe.get_Data();
            // dostawka_recycle.s2.sendEmptyMessage(1);
            Log.d("geldi", "" + position);

        }
        holder.name.setTypeface(typebold);
        Glide.with(cntx)
                .load("http://" + Api.url + "tagam24/images/" + list1.get(position).getImage()).asBitmap().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.imageView);
        holder.address.setText(list1.get(position).getAddress());
        holder.address.setTypeface(typeregular);
        holder.worktime.setTypeface(typeregular);
        holder.r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.cafesel = list1.get(position);
                Constants.cafeID = "" + position;
                Intent i = new Intent(cntx, Cafes_menu.class);
                rate_nahar rr=new rate_nahar();
                rr.setId(list1.get(position).getId(),"cafe");
                cntx.startActivity(i);
                //dostawka_recycle.s3.sendEmptyMessage(1);
            }
        });
        String[] time = new String[2];
        if (!list1.get(position).getWork_start().equals("24/7")) {
            time = null;
            time = new String[2];
            time = list1.get(position).getWork_start().split("-");
            Date date = new Date();
            final SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm");

            Calendar calendar2 = Calendar.getInstance();
            final String currentime = formatter2.format(calendar2.getTime());
            time[0] = '0' + time[0];

            if (currentime.compareTo(time[0]) > 0 && currentime.compareTo(time[1]) < 0) {
                Constants.open = "0";
                holder.worktime.setText(list1.get(position).getWork_start());
                holder.worktime.setTextColor(Color.DKGRAY);
                holder.work_Image.setImageResource(R.drawable.clock_icon);
                holder.work_Image.setColorFilter(ContextCompat.getColor(cntx, R.color.darkerGray), android.graphics.PorterDuff.Mode.SRC_IN);
            } else {
                Constants.open = "1";
                holder.worktime.setText(dd.yapyk);
                holder.worktime.setTextColor(Color.RED);
                holder.work_Image.setImageResource(R.drawable.lock2);
                holder.work_Image.setColorFilter(ContextCompat.getColor(cntx, R.color.red), android.graphics.PorterDuff.Mode.SRC_IN);
            }

            //  holder.worktime.setText(time);
            //  holder.worktime.setTypeface(typeface1);
            Integer a;
            a = Integer.parseInt(list1.get(position).getRating());
            if (a == 0) {
                holder.star1.setImageResource(R.drawable.star_white);
                holder.star2.setImageResource(R.drawable.star_white);
                holder.star3.setImageResource(R.drawable.star_white);
                holder.star4.setImageResource(R.drawable.star_white);
                holder.star5.setImageResource(R.drawable.star_white);
            }
            if (a == 1) {
                holder.star1.setImageResource(R.drawable.star_icon);
                holder.star2.setImageResource(R.drawable.star_white);
                holder.star3.setImageResource(R.drawable.star_white);
                holder.star4.setImageResource(R.drawable.star_white);
                holder.star5.setImageResource(R.drawable.star_white);
            }
            if (a == 2) {
                holder.star1.setImageResource(R.drawable.star_icon);
                holder.star2.setImageResource(R.drawable.star_icon);
                holder.star3.setImageResource(R.drawable.star_white);
                holder.star4.setImageResource(R.drawable.star_white);
                holder.star5.setImageResource(R.drawable.star_white);
            }
            if (a == 3) {
                holder.star1.setImageResource(R.drawable.star_icon);
                holder.star2.setImageResource(R.drawable.star_icon);
                holder.star3.setImageResource(R.drawable.star_icon);
                holder.star4.setImageResource(R.drawable.star_white);
                holder.star5.setImageResource(R.drawable.star_white);
            }
            if (a == 4) {
                holder.star1.setImageResource(R.drawable.star_icon);
                holder.star2.setImageResource(R.drawable.star_icon);
                holder.star3.setImageResource(R.drawable.star_icon);
                holder.star4.setImageResource(R.drawable.star_icon);
                holder.star5.setImageResource(R.drawable.star_white);
            }
            if (a == 5) {
                holder.star1.setImageResource(R.drawable.star_icon);
                holder.star2.setImageResource(R.drawable.star_icon);
                holder.star3.setImageResource(R.drawable.star_icon);
                holder.star4.setImageResource(R.drawable.star_icon);
                holder.star5.setImageResource(R.drawable.star_icon);
            }


        }
    }
        @Override
        public int getItemCount () {
            return list1.size();
        }

        public void setData (ArrayList < Model_Cafe > cafe) {
            list1 = cafe;
            notifyDataSetChanged();
        }


    }
