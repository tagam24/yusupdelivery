package com.tagam24.tagam.karzina;

/**
 * Created by Sulik on 1/3/2019.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
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
import com.tagam24.tagam.Database.Db;
import com.tagam24.tagam.R;
import com.tagam24.tagam.aksiya.arzanlad_menu;
import com.tagam24.tagam.cafes_menu.Cafes_menu;
import com.tagam24.tagam.cafes_menu.MyBounceInterpolator;
import com.tagam24.tagam.dastawka_recycle;
import com.tagam24.tagam.dil;
import com.tagam24.tagam.models.Model_Food;
import com.tagam24.tagam.network.Api;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;


public class RecycleAdapter_foodKarzina extends RecyclerView.Adapter<RecycleAdapter_foodKarzina.Reklama_viewholder> {
    ArrayList<Model_Food> list1;
    Context cntx;
    String table;
    Boolean b1 = false, b2 = false, b3 = false;
    boolean t=false;
    Db db;
    dil dd;
    public class Reklama_viewholder extends RecyclerView.ViewHolder {
        TextView name, price, ratenumber, manat, count_number,time;
        ImageView star1, star2, star3, star4, star5, like;
        CardView add_to_cart;
        RoundedImageView imageView;
        ImageView add_image;

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
            star1 = (ImageView) itemView.findViewById(R.id.star1);
            star2 = (ImageView) itemView.findViewById(R.id.star2);
            star3 = (ImageView) itemView.findViewById(R.id.star3);
            star4 = (ImageView) itemView.findViewById(R.id.star4);
            star5 = (ImageView) itemView.findViewById(R.id.star5);
            add_to_cart=(CardView)itemView.findViewById(R.id.add_to_cart);
            like=(ImageView)itemView.findViewById(R.id.like);
            add_image=(ImageView)itemView.findViewById(R.id.image_add);
          //  manat = (TextView) itemView.findViewById(R.id.manat_food_show);
        //    minus = (ImageView) itemView.findViewById(R.id.minus_button_food_recycle);
        //    plus = (ImageView) itemView.findViewById(R.id.plus_button_food_recycle);
          //  count_number = (TextView) itemView.findViewById(R.id.count_number_food_recycle);
        //    time=(TextView)itemView.findViewById(R.id.time_food_recycle);
        //    relativeLayout_minus=(RelativeLayout)itemView.findViewById(R.id.layout_minus_botton_food_recycle);
        //    relativeLayout_plus=(RelativeLayout)itemView.findViewById(R.id.layout_plus_botton_food_recycle);

        }
    }

    public RecycleAdapter_foodKarzina(ArrayList<Model_Food> items, Context ctx) {
        this.list1 = items;
        this.dd=new dil();
        this.cntx = ctx;
        db=new Db(ctx);
    }

    @Override
    public Reklama_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_aksiya, parent, false);
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
            dastawka_recycle.s2.sendEmptyMessage(1);
            Log.d("geldi",""+position);

        }
        Log.d("id",list1.get(position).getId());
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = cntx.getTheme();
        theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
        @ColorInt final int color = typedValue.data;
        if(Constants.ids.contains(list1.get(position).getId())){
            holder.add_to_cart.setCardBackgroundColor(color);
            holder.add_image.setImageResource(R.drawable.plus_icon);
            holder.add_image.setColorFilter(ContextCompat.getColor(cntx, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            holder.add_to_cart.setCardBackgroundColor(cntx.getResources().getColor(R.color.white));
            holder.add_image.setImageResource(R.drawable.plus_icon);
            if (db.get_color().equals("orange"))
                holder.add_image.setColorFilter(ContextCompat.getColor(cntx, R.color.colorPrimary3), android.graphics.PorterDuff.Mode.SRC_IN);
            if (db.get_color().equals("blue"))
                holder.add_image.setColorFilter(ContextCompat.getColor(cntx, R.color.colorPrimary2), android.graphics.PorterDuff.Mode.SRC_IN);
            if (db.get_color().equals("green"))
                holder.add_image.setColorFilter(ContextCompat.getColor(cntx, R.color.colorPrimary1), android.graphics.PorterDuff.Mode.SRC_IN);
            if (db.get_color().equals("pink"))
                holder.add_image.setColorFilter(ContextCompat.getColor(cntx, R.color.colorPrimary4), android.graphics.PorterDuff.Mode.SRC_IN);
        }
        holder.name.setTypeface(typebold);
        Glide.with(cntx)
                .load("http://"+ Api.url+"tagam24/images/" + list1.get(position).getImage()).asBitmap().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.imageView);
        holder.price.setText(list1.get(position).getPrice()+" "+dil.manat);
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
      final  Model_Food m=list1.get(position);
         holder.like.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 db.inser_liked_food(m.getId(),m.getImage(),m.getImage1(),m.getName(),m.getPrice(),m.getRating(),m.getRate_number(),m.getCafeID());
                 final Animation myAnim = AnimationUtils.loadAnimation(cntx, R.anim.bounce);
                 MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                 myAnim.setInterpolator(interpolator);
                 holder.like.startAnimation(myAnim);

                 db.inser_parent_cafe(Constants.cafesel.getId(),Constants.cafesel.getImage(),Constants.cafesel.getName()
                         ,Constants.cafesel.getAddress(),Constants.cafesel.getRating(),Constants.cafesel.getWork_start(),
                         Constants.cafesel.getLogo(),Constants.cafesel.getDostawka_price(),Constants.cafesel.getCategory());
             }
         });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
       holder.add_to_cart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if(Constants.basketCafeId.equals(Constants.cafeID)||Constants.cart_list.size()==0){
                   if(!Constants.ids.contains(list1.get(position).getId())){

                       //addd
                       Constants.ids.add(list1.get(position).getId());
                       Constants.cart_list.add(list1.get(position));
                       Constants.basketCafeId=Constants.cafeID;
                       Constants.cafesel=Constants.cafe.get(Constants.positionCafe);
                       String price=list1.get(position).getPrice().replace(",",".");
                       Constants.tt_price+=Float.parseFloat(price);
                       holder.add_to_cart.setCardBackgroundColor(color);
                       holder.add_image.setImageResource(R.drawable.plus_icon);
                       holder.add_image.setColorFilter(ContextCompat.getColor(cntx, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
               final Animation myAnim = AnimationUtils.loadAnimation(cntx, R.anim.bounce);
               MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
               myAnim.setInterpolator(interpolator);
               holder.add_to_cart.startAnimation(myAnim);
                       if(Cafes_menu.s1!=null)Cafes_menu.s1.sendEmptyMessage(1);
                       if(arzanlad_menu.s5!=null) arzanlad_menu.s5.sendEmptyMessage(1);
                       Karzina.s1.sendEmptyMessage(1);} else{
                     //remove
                       Constants.tt_price-=Integer.parseInt(list1.get(position).getPrice());
                       Constants.ids.remove(list1.get(position).getId());
                       Constants.cart_list.remove(list1.get(position));
                       holder.add_to_cart.setCardBackgroundColor(cntx.getResources().getColor(R.color.white));
                       holder.add_image.setImageResource(R.drawable.plus_icon);
                       if (db.get_color().equals("orange"))
                           holder.add_image.setColorFilter(ContextCompat.getColor(cntx, R.color.colorPrimary3), android.graphics.PorterDuff.Mode.SRC_IN);
                       if (db.get_color().equals("blue"))
                           holder.add_image.setColorFilter(ContextCompat.getColor(cntx, R.color.colorPrimary2), android.graphics.PorterDuff.Mode.SRC_IN);
                       if (db.get_color().equals("green"))
                           holder.add_image.setColorFilter(ContextCompat.getColor(cntx, R.color.colorPrimary1), android.graphics.PorterDuff.Mode.SRC_IN);
                       if (db.get_color().equals("pink"))
                           holder.add_image.setColorFilter(ContextCompat.getColor(cntx, R.color.colorPrimary4), android.graphics.PorterDuff.Mode.SRC_IN);
                       final Animation myAnim = AnimationUtils.loadAnimation(cntx, R.anim.bounce);
                       MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                       myAnim.setInterpolator(interpolator);
                       holder.add_to_cart.startAnimation(myAnim);
                       if(Cafes_menu.s1!=null)Cafes_menu.s1.sendEmptyMessage(1);
                       if(arzanlad_menu.s5!=null) arzanlad_menu.s5.sendEmptyMessage(1);   Karzina.s1.sendEmptyMessage(1);
                   } } else {

                   AlertDialog.Builder builder = new AlertDialog.Builder(cntx);
                   builder.setMessage("Sebedi bosatmaly")
                           .setPositiveButton("Hawa", new DialogInterface.OnClickListener() {
                               public void onClick(DialogInterface dialog, int id) {
                               t=true;
                                   //clear and add
                                   Constants.tt_price=0;
                                   String price=list1.get(position).getPrice().replace(",",".");
                                   Constants.tt_price+=Float.parseFloat(price);
                                   Constants.ids.clear();
                                   Constants.cart_list.clear();
                                   Constants.ids.add(list1.get(position).getId());
                                   Constants.cart_list.add(list1.get(position));
                                   Constants.cafesel=Constants.cafe.get(Constants.positionCafe);
                                   Constants.cart_list.add(list1.get(position));
                                   Constants.ids.add(list1.get(position).getId());
                                   Constants.basketCafeId=Constants.cafeID;
                                   holder.add_to_cart.setCardBackgroundColor(color);
                                   holder.add_image.setImageResource(R.drawable.plus_icon);
                                   holder.add_image.setColorFilter(ContextCompat.getColor(cntx, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
                                   final Animation myAnim = AnimationUtils.loadAnimation(cntx, R.anim.bounce);
                                   MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                                   myAnim.setInterpolator(interpolator);
                                   if(Cafes_menu.s1!=null)Cafes_menu.s1.sendEmptyMessage(1);
                                   if(arzanlad_menu.s5!=null) arzanlad_menu.s5.sendEmptyMessage(1);
                               }
                           })
                           .setNegativeButton("yok", new DialogInterface.OnClickListener() {
                               public void onClick(DialogInterface dialog, int id) {
                                t=false;
                               }
                           });
                   // Create the AlertDialog object and return it
                   builder.create().show();




               }

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
