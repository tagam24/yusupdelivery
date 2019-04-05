package com.tagam24.tagam.karzina;

/**
 * Created by Sulik on 1/3/2019.
 */

import android.content.Context;
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
import com.tagam24.tagam.R;
import com.tagam24.tagam.cafes_menu.MyBounceInterpolator;
import com.tagam24.tagam.dastawka_recycle;
import com.tagam24.tagam.dil;
import com.tagam24.tagam.models.Model_Food;
import com.tagam24.tagam.network.Api;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;


public class RecycleAdapter_karzina extends RecyclerView.Adapter<RecycleAdapter_karzina.Reklama_viewholder> {
    ArrayList<Model_Food> list1;
    Context cntx;
    String table;
    Boolean b1 = false, b2 = false, b3 = false;
    int c1=0;
    public class Reklama_viewholder extends RecyclerView.ViewHolder {
        TextView name, price,count;
        ImageView star1, star2, star3, star4, star5,add,remove;
        CardView add_to_cart;
        RoundedImageView imageView;
        ViewGroup.LayoutParams ls1, ls2;
        LinearLayout l;
        RelativeLayout relativeLayout, relativeLayout1, relativeLayout2,relativeLayout_minus,relativeLayout_plus;
        LinearLayout.LayoutParams ls;

        public Reklama_viewholder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name_food_show);
            price = (TextView) itemView.findViewById(R.id.price_food_show);
       //     ratenumber = (TextView) itemView.findViewById(R.id.ratenumber_food_show);
            imageView = (RoundedImageView) itemView.findViewById(R.id.imag_food_show);
            star1 = (ImageView) itemView.findViewById(R.id.food_star_image1_show);
            star2 = (ImageView) itemView.findViewById(R.id.food_star_image2_show);
            star3 = (ImageView) itemView.findViewById(R.id.food_star_image3_show);
            star4 = (ImageView) itemView.findViewById(R.id.food_star_image4_show);
            star5 = (ImageView) itemView.findViewById(R.id.food_star_image5_show);
            add=(ImageView)itemView.findViewById(R.id.add_count);
            remove=(ImageView)itemView.findViewById(R.id.remove_count);
            count=(TextView)itemView.findViewById(R.id.count);

          //  manat = (TextView) itemView.findViewById(R.id.manat_food_show);
        //    minus = (ImageView) itemView.findViewById(R.id.minus_button_food_recycle);
        //    plus = (ImageView) itemView.findViewById(R.id.plus_button_food_recycle);
          //  count_number = (TextView) itemView.findViewById(R.id.count_number_food_recycle);
        //    time=(TextView)itemView.findViewById(R.id.time_food_recycle);
        //    relativeLayout_minus=(RelativeLayout)itemView.findViewById(R.id.layout_minus_botton_food_recycle);
        //    relativeLayout_plus=(RelativeLayout)itemView.findViewById(R.id.layout_plus_botton_food_recycle);

        }
    }

    public RecycleAdapter_karzina(ArrayList<Model_Food> items, Context ctx) {
        this.list1 = items;
        this.cntx = ctx;
    }

    @Override
    public Reklama_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_cart, parent, false);
        final Reklama_viewholder view = new Reklama_viewholder(v);
        return view;

    }

    @Override
    public void onBindViewHolder(final Reklama_viewholder holder, final int position) {
        holder.name.setText(list1.get(position).getName());

        Typeface typebold = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_Bold.ttf");
        Typeface  typelight = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_Light.ttf");
        Typeface  typeregular = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_Regular.ttf");
        Typeface typeextrabold = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_ExtraBold.ttf");

        if(position==list1.size()-1&& Constants.iter==true){
            Constants.size=list1.size();
          //  get_food.get_Data();
            //dastawka_recycle.s2.sendEmptyMessage(1);
            Log.d("geldi",""+position);

        }
        holder.name.setTypeface(typebold);
        Glide.with(cntx)
                .load("http://"+ Api.url+"tagam24/images/" + list1.get(position).getImage()).asBitmap().fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.imageView);
        holder.price.setText(list1.get(position).getPrice()+" "+ dil.manat);
        holder.price.setTypeface(typeregular);
//        holder.manat.setText("manat");
     //   holder.manat.setTypeface(typeface);
      //  holder.time.setText(list1.get(position).getTime());
      //  holder.time.setTypeface(typeface);
     /*   holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(holder.count_number.getText().toString());
                if (a > 0) {
                    a = a - 1;
                    holder.count_number.setText(a + "");

                    final Animation myAnim = AnimationUtils.loadAnimation(cntx, R.anim.bounce);
                    holder.relativeLayout_minus.startAnimation(myAnim);
                    holder.count_number.startAnimation(myAnim);

                    // Use bounce interpolator with amplitude 0.2 and frequency 20
                    MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                    myAnim.setInterpolator(interpolator);
                } else {
                    holder.count_number.setText("0");
                }
            }
        });
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(holder.count_number.getText().toString());
                    a = a + 1;
                    holder.count_number.setText(a + "");

                final Animation myAnim = AnimationUtils.loadAnimation(cntx, R.anim.bounce);
                holder.relativeLayout_plus.startAnimation(myAnim);
                holder.count_number.startAnimation(myAnim);
            }
        });
*/
//        holder.ratenumber.setText(("(" + list1.get(position).getRate_number() + ")"));
       // holder.ratenumber.setTypeface(typeface);
        holder.count.setText(""+list1.get(position).getCount());
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String price=list1.get(position).getPrice().replace(",",".");
                Constants.tt_price+=Float.parseFloat(price);
                 c1=Integer.parseInt(holder.count.getText().toString())+1;
                 list1.get(position).setCount(c1);
                 holder.count.setText(""+list1.get(position).getCount());
                final Animation myAnim = AnimationUtils.loadAnimation(cntx, R.anim.bounce);
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                holder.add.startAnimation(myAnim);
                holder.count.startAnimation(myAnim);
                Karzina.s1.sendEmptyMessage(1);
            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String price=list1.get(position).getPrice().replace(",",".");
                Constants.tt_price-=Float.parseFloat(price);
                Karzina.s1.sendEmptyMessage(1);
                if(Integer.parseInt(holder.count.getText().toString())>1){

                    c1=Integer.parseInt(holder.count.getText().toString())-1;
                    list1.get(position).setCount(c1);
                    holder.count.setText(""+list1.get(position).getCount());}else{
                    Constants.ids.remove(list1.get(position).getId());
                     list1.remove(position);
                    notifyDataSetChanged();
                }
                final Animation myAnim = AnimationUtils.loadAnimation(cntx, R.anim.bounce);
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                holder.remove.startAnimation(myAnim);
                holder.count.startAnimation(myAnim);
            }
        }
        );
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
        if (a == 0) {
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


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    void setData(ArrayList<Model_Food> food){
        list1=food;
        notifyDataSetChanged();
    }
}
