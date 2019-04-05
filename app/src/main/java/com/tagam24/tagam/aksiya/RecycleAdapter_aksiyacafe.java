package com.tagam24.tagam.aksiya;

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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tagam24.tagam.Constants.Constants;
import com.tagam24.tagam.Database.Db;
import com.tagam24.tagam.R;
import com.tagam24.tagam.cafes_menu.MyBounceInterpolator;
import com.tagam24.tagam.dil;
import com.tagam24.tagam.models.Model_Cafe;
import com.tagam24.tagam.network.Api;
import com.tagam24.tagam.network.get_cafeAksiya;
import com.tagam24.tagam.rate_nahar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class RecycleAdapter_aksiyacafe extends RecyclerView.Adapter<RecycleAdapter_aksiyacafe.Reklama_viewholder> {
    ArrayList<Model_Cafe> list1;
    Context cntx;
    String table;
    dil dd;
    Boolean b1 = false, b2 = false, b3 = false;
    Db db;
    public class Reklama_viewholder extends RecyclerView.ViewHolder {
        TextView name, address, worktime,aksiya,n_people;
        ImageView star1, star2, star3, star4, star5, worktime_img;
        ImageView imageView, lock, love;
        LinearLayout l1;

        ViewGroup.LayoutParams ls1, ls2;
        LinearLayout l;
        MaterialRippleLayout r1;
        RelativeLayout relativeLayout, relativeLayout1, relativeLayout2;
        LinearLayout.LayoutParams ls;

        public Reklama_viewholder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.cafe_name_show);
            n_people=(TextView)itemView.findViewById(R.id.n_people);
            address = (TextView) itemView.findViewById(R.id.cafe_addres_show);
            imageView = (ImageView) itemView.findViewById(R.id.imag_cafe_show);
            worktime_img = (ImageView) itemView.findViewById(R.id.image_clock);
            star1 = (ImageView) itemView.findViewById(R.id.cafe_star_image1_show);
            star2 = (ImageView) itemView.findViewById(R.id.cafe_star_image2_show);
            star3 = (ImageView) itemView.findViewById(R.id.cafe_star_image3_show);
            star4 = (ImageView) itemView.findViewById(R.id.cafe_star_image4_show);
            star5 = (ImageView) itemView.findViewById(R.id.cafe_star_image5_show);
            l1 = (LinearLayout) itemView.findViewById(R.id.linear);
            r1 = (MaterialRippleLayout) itemView.findViewById(R.id.ripple);
            worktime=(TextView) itemView.findViewById(R.id.time);
            love = (ImageView) itemView.findViewById(R.id.love);
            aksiya=(TextView) itemView.findViewById(R.id.aksiya_txt);

        }
    }

    public RecycleAdapter_aksiyacafe(ArrayList<Model_Cafe> items, Context ctx) {
        this.list1 = items;
        this.cntx = ctx;
        db=new Db(cntx);
    }

    @Override
    public Reklama_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_kafes2, parent, false);
        final Reklama_viewholder view = new Reklama_viewholder(v);
        return view;

    }

    @Override
    public void onBindViewHolder(final Reklama_viewholder holder, final int position) {
        holder.name.setText(list1.get(position).getName());

        Typeface typebold = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_Bold.ttf");
        Typeface typelight = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_Light.ttf");
        Typeface typeregular = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_Regular.ttf");
        Typeface typeextrabold = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_ExtraBold.ttf");
        holder.n_people.setText("("+list1.get(position).getN_people()+")");
        holder.n_people.setTypeface(typeregular);
        if (position == list1.size() - 1 && Constants.iter == true) {
            Constants.size = list1.size();
            get_cafeAksiya.get_Data();
            aksiya_recycle.s2.sendEmptyMessage(1);
            Log.d("geldi", "" + position);

        }

        final Model_Cafe m = list1.get(position);
        if (db.isInCafe(m.id)) holder.love.setImageResource(R.drawable.heart_icon);
        else
            holder.love.setImageResource(R.drawable.heart_bos);

        holder.love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation myAnim = AnimationUtils.loadAnimation(cntx, R.anim.bounce);
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                holder.love.startAnimation(myAnim);

                if (!db.isInCafe(m.getId())) {

                    db.inser_liked_cafe(m.getId(), m.getImage(), m.getName(), m.getAddress(),
                            m.getRating(), m.getN_people(), m.getWork_start(), m.getLogo(), m.getDostawka_price(),
                            m.getCategory(), Constants.aksiya);
                    holder.love.setImageResource(R.drawable.heart_icon);
                } else {
                    holder.love.setImageResource(R.drawable.heart_bos);

                    db.delete_cafe(m.getId());
                }


            }
        });
        holder.name.setTypeface(typebold);
        holder.aksiya.setText(list1.get(position).getAksiya());
        holder.aksiya.setTypeface(typeregular);
        Glide.with(cntx)
                .load("http://" + Api.url + "tagam24/images/" + list1.get(position).getImage()).asBitmap().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);
        holder.address.setText(list1.get(position).getAddress());
        holder.address.setTypeface(typeregular);
        holder.r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Constants.cafesel = list1.get(position);
                Constants.cafeID = "" + position;
                Intent i = new Intent(cntx, arzanlad_menu.class);
                i.putExtra("name",list1.get(position).getName());
                rate_nahar rr=new rate_nahar();
                rr.setId(list1.get(position).getId(),"cafe");
                cntx.startActivity(i);

            }
        });
        String[] time = new String[2];
        if (!list1.get(position).getWork_start().equals("24/7")) {
            time = null;
            time = new String[2];
            time = list1.get(position).getWork_start().split("-");
            Date d = new Date();
            final SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm");

            Calendar calendar2 = Calendar.getInstance();
            final String currentime = formatter2.format(calendar2.getTime());
            time[0] = '0' + time[0];

            if (currentime.compareTo(time[0]) > 0 && currentime.compareTo(time[1]) < 0) {
                Constants.open = "0";
                holder.worktime.setText(list1.get(position).getWork_start());
                holder.worktime.setTextColor(Color.DKGRAY);
                holder.worktime_img.setImageResource(R.drawable.clock_icon);
                holder.worktime_img.setColorFilter(ContextCompat.getColor(cntx, R.color.darkerGray), android.graphics.PorterDuff.Mode.SRC_IN);
            } else {
                Constants.open = "1";
                holder.worktime.setText(dd.yapyk);
                holder.worktime.setTextColor(Color.RED);
                holder.worktime_img.setImageResource(R.drawable.lock2);
                holder.worktime_img.setColorFilter(ContextCompat.getColor(cntx, R.color.red), android.graphics.PorterDuff.Mode.SRC_IN);
            }
            holder.worktime.setTypeface(typeregular);
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
    public int getItemCount() {
        return list1.size();
    }

    public void setData(ArrayList<Model_Cafe> cafe) {
        list1 = cafe;
        notifyDataSetChanged();
    }


}
