package com.tagam24.tagam.gumenje;

/**
 * Created by Sulik on 1/3/2019.
 */

import android.content.Context;
import android.content.Intent;
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
import com.makeramen.roundedimageview.RoundedImageView;
import com.tagam24.tagam.Constants.Constants;
import com.tagam24.tagam.Database.Db;
import com.tagam24.tagam.R;
import com.tagam24.tagam.cafes_menu.MyBounceInterpolator;
import com.tagam24.tagam.network.Api;
import com.tagam24.tagam.network.get_reciept;

import java.util.ArrayList;


public class RecycleAdapter_reciept extends RecyclerView.Adapter<RecycleAdapter_reciept.Reklama_viewholder> {
    ArrayList<recept> list1;
    Context cntx;
    String table;
    Boolean b1 = false, b2 = false, b3 = false;
    boolean t=false;
    Db db;
    public class Reklama_viewholder extends RecyclerView.ViewHolder {
        TextView name, price, ratenumber, manat, count_number,time;
        ImageView star1, star2, star3, star4, star5, like;
        CardView add_to_cart;
        RoundedImageView imageView;

        ViewGroup.LayoutParams ls1, ls2;
        LinearLayout l;
        RelativeLayout relativeLayout, relativeLayout1, relativeLayout2,relativeLayout_minus,relativeLayout_plus;
        LinearLayout.LayoutParams ls;

        public Reklama_viewholder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.content);
       //     ratenumber = (TextView) itemView.findViewById(R.id.ratenumber_food_show);
            imageView = (RoundedImageView) itemView.findViewById(R.id.image);
            star1 = (ImageView) itemView.findViewById(R.id.star1);
            star2 = (ImageView) itemView.findViewById(R.id.star2);
            star3 = (ImageView) itemView.findViewById(R.id.star3);
            star4 = (ImageView) itemView.findViewById(R.id.star4);
            star5 = (ImageView) itemView.findViewById(R.id.star5);

            like=(ImageView)itemView.findViewById(R.id.like);

        }
    }

    public RecycleAdapter_reciept(ArrayList<recept> items, Context ctx) {
        this.list1 = items;
        this.cntx = ctx;
        db=new Db(ctx);
    }

    @Override
    public Reklama_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_receipt, parent, false);
        final Reklama_viewholder view = new Reklama_viewholder(v);
        return view;

    }

    @Override
    public void onBindViewHolder(final Reklama_viewholder holder, final int position) {
        holder.name.setText(list1.get(position).getName());

        Typeface typebold = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_Bold.ttf");
        Typeface  typelight = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_Light.ttf");
        Typeface typeregular = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_Regular.ttf");
        Typeface typeextrabold = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_ExtraBold.ttf");

        if(position==list1.size()-1&& Constants.iter==true){
            Constants.size=list1.size();
            get_reciept.get_Data();

            Log.d("geldi",""+position);

        }

        Log.d("id",list1.get(position).getId());
        holder.name.setTypeface(typebold);
        Glide.with(cntx)
                .load("http://"+ Api.url+"tagam24/images/" + list1.get(position).getImage()).asBitmap().fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.imageView);
        holder.price.setText(list1.get(position).getContent());
        holder.price.setTypeface(typeregular);

        Integer a;
        a = Integer.parseInt(list1.get(position).getRating());
        if (a == 5) {
            holder.star1.setImageResource(R.drawable.star_icon);
            holder.star2.setImageResource(R.drawable.star_icon);
            holder.star3.setImageResource(R.drawable.star_icon);
            holder.star4.setImageResource(R.drawable.star_icon);
            holder.star5.setImageResource(R.drawable.star_icon);
        }
        if (a == 4) {
            holder.star5.setImageResource(R.drawable.star_white);
            holder.star4.setImageResource(R.drawable.star_icon);
            holder.star3.setImageResource(R.drawable.star_icon);
            holder.star2.setImageResource(R.drawable.star_icon);
            holder.star1.setImageResource(R.drawable.star_icon);
        }
        if (a == 3) {
            holder.star5.setImageResource(R.drawable.star_white);
            holder.star4.setImageResource(R.drawable.star_white);
            holder.star3.setImageResource(R.drawable.star_icon);
            holder.star2.setImageResource(R.drawable.star_icon);
            holder.star1.setImageResource(R.drawable.star_icon);
        }
        if (a == 2) {
            holder.star1.setImageResource(R.drawable.star_icon);
            holder.star2.setImageResource(R.drawable.star_icon);
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
        if (a == 0) {
            holder.star1.setImageResource(R.drawable.star_white);
            holder.star2.setImageResource(R.drawable.star_white);
            holder.star3.setImageResource(R.drawable.star_white);
            holder.star4.setImageResource(R.drawable.star_white);
            holder.star5.setImageResource(R.drawable.star_white);
        }
        final recept m=list1.get(position);
        if(!db.isInReceipeduzumi(m.getId()))holder.like.setImageResource(R.drawable.heart_bos); else holder.like.setImageResource(R.drawable.heart_icon);
         holder.like.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 //db.inser_liked_food(m.getId(),m.getImage(),m.getName(),m.getPrice(),m.getRating(),m.getRate_number(),m.getCafeID());
                 final Animation myAnim = AnimationUtils.loadAnimation(cntx, R.anim.bounce);
                 MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                 myAnim.setInterpolator(interpolator);
                 holder.like.startAnimation(myAnim);
                 if(!db.isInReceipeduzumi(m.getId())){db.inser_receipe_duzumi(m.getId(),m.getImage(),m.getName(),m.getContent(),m.getWatch(),m.getRating(),
                         m.getN_people(),m.getText1(),m.getText1(),m.getImage2(),m.getImage1(),m.getDate());
                 holder.like.setImageResource(R.drawable.heart_icon);}
                 else{db.delete_receipe_duzumi(m.getId()); holder.like.setImageResource(R.drawable.heart_bos);}

            //     if(!db.isInReceipeduzumi(m.getId())){db.inser_receipe_duzumi(m.getId(),m.getImage(),m.getName(),m.getContent(),m.getWatch(),m.getRating(),
             //    m.getN_people(),m.getText1(),m.getText1(),m.getImage2(),m.getImage1());}
             //   else{db.delete_receipe_duzumi(m.getId());}


             }
         });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(cntx, list1.get(position).getRating()+" "+list1.get(position).getName(), Toast.LENGTH_LONG).show();
                Intent i=new Intent(cntx,recept_show.class);
                i.putExtra("from","recept");
                i.putExtra("id",m.getId());
                i.putExtra("image1",m.getImage1());
                i.putExtra("image",m.getImage1());
                i.putExtra("name",m.getName());
                i.putExtra("content",m.getContent());
                i.putExtra("watch",m.getWatch());
                i.putExtra("count_rating",m.getRating());
                i.putExtra("n_people",m.getN_people());
                i.putExtra("text1",m.getText1());
                i.putExtra("text2",m.getTex2());
                i.putExtra("image2",m.getImage2());
                i.putExtra("rating",m.getRating());
                i.putExtra("date",m.getDate());
                cntx.startActivity(i);

            }
        });





    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public void setData(ArrayList<recept> food){
        list1=food;
        notifyDataSetChanged();
    }
}
