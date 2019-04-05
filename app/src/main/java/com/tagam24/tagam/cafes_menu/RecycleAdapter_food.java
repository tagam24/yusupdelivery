package com.tagam24.tagam.cafes_menu;

/**
 * Created by Sulik on 1/3/2019.
 */

import android.accessibilityservice.AccessibilityButtonController;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Rating;
import android.nfc.Tag;
import android.support.annotation.ColorInt;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;
import com.tagam24.tagam.Constants.Constants;
import com.tagam24.tagam.Database.Db;
import com.tagam24.tagam.R;
import com.tagam24.tagam.dil;
import com.tagam24.tagam.models.Model_Food;
import com.tagam24.tagam.network.Api;
import com.tagam24.tagam.network.get_food;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tagam24.tagam.rate_nahar;

import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Arrays;


public class RecycleAdapter_food extends RecyclerView.Adapter<RecycleAdapter_food.Reklama_viewholder> {
    ArrayList<Model_Food> list1;
    Context cntx;
    String table;
    Boolean b1 = false, b2 = false, b3 = false;
    boolean t = false;
    Tag TAG;
    String idd;
    public  Db db;
    public String id = null;


    public class Reklama_viewholder extends RecyclerView.ViewHolder {
        TextView name, price, ratenumber, manat, count_number, time;
        ImageView star1, star2, star3, star4, star5, like;
        CardView add_to_cart;
        RoundedImageView imageView;
        ImageView add_image;

        ViewGroup.LayoutParams ls1, ls2;
        LinearLayout l, l_rate;
        RelativeLayout relativeLayout, relativeLayout1, relativeLayout2, relativeLayout_minus, relativeLayout_plus;
        LinearLayout.LayoutParams ls;
        TextView rating;

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
            add_to_cart = (CardView) itemView.findViewById(R.id.add_to_cart);
            like = (ImageView) itemView.findViewById(R.id.like);
            add_image = (ImageView) itemView.findViewById(R.id.image_add);
            l_rate = (LinearLayout) itemView.findViewById(R.id.layout_rate);

        }
    }

    public RecycleAdapter_food(ArrayList<Model_Food> items, Context ctx) {
        this.list1 = items;
        this.cntx = ctx;
        db = new Db(ctx);

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
        Typeface typelight = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_Light.ttf");
        Typeface typeregular = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_Regular.ttf");
        Typeface typeextrabold = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_ExtraBold.ttf");
        if (position == list1.size() - 1 && Constants.iter == true) {
            Constants.size = list1.size();
            get_food.get_Data();
            if (Cafes_menu.categories.length >= 1 && Cafes_menu.categories[0].equals(Constants.categoryFood))
                fragment_dostawka1.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 2 && Cafes_menu.categories[1].equals(Constants.categoryFood))
                fragment_dostawka2.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 3 && Cafes_menu.categories[2].equals(Constants.categoryFood))

                fragment_dostawka3.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 4 && Cafes_menu.categories[3].equals(Constants.categoryFood))
                fragment_dostawka4.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 5 && Cafes_menu.categories[4].equals(Constants.categoryFood))
                fragment_dostawka5.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 6 && Cafes_menu.categories[5].equals(Constants.categoryFood))
                fragment_dostawka6.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 7 && Cafes_menu.categories[6].equals(Constants.categoryFood))
                fragment_dostawka7.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 8 && Cafes_menu.categories[7].equals(Constants.categoryFood))
                fragment_dostawka8.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 9 && Cafes_menu.categories[8].equals(Constants.categoryFood))
                fragment_dostawka9.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 10 && Cafes_menu.categories[9].equals(Constants.categoryFood))
                fragment_dostawka10.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 11 && Cafes_menu.categories[10].equals(Constants.categoryFood))
                fragment_dostawka11.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 12 && Cafes_menu.categories[11].equals(Constants.categoryFood))
                fragment_dostawka12.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 13 && Cafes_menu.categories[12].equals(Constants.categoryFood))
                fragment_dostawka13.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 14 && Cafes_menu.categories[13].equals(Constants.categoryFood))
                fragment_dostawka14.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 15 && Cafes_menu.categories[14].equals(Constants.categoryFood))
                fragment_dostawka15.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 16 && Cafes_menu.categories[15].equals(Constants.categoryFood))
                fragment_dostawka16.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 17 && Cafes_menu.categories[16].equals(Constants.categoryFood))
                fragment_dostawka17.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 18 && Cafes_menu.categories[17].equals(Constants.categoryFood))
                fragment_dostawka18.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 19 && Cafes_menu.categories[18].equals(Constants.categoryFood))
                fragment_dostawka19.s1.sendEmptyMessage(1);
            else if (Cafes_menu.categories.length >= 20 && Cafes_menu.categories[19].equals(Constants.categoryFood))
                fragment_dostawka20.s1.sendEmptyMessage(1);

            Log.d("geldi", "" + position);

        }
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = cntx.getTheme();
        theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
        @ColorInt final int color = typedValue.data;
        if (Constants.ids.contains(list1.get(position).getId())) {

            holder.add_to_cart.setCardBackgroundColor(color);
            holder.add_image.setImageResource(R.drawable.plus_icon);
            holder.add_image.setColorFilter(ContextCompat.getColor(cntx, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            holder.add_to_cart.setCardBackgroundColor(Color.WHITE);
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
                .load("http://" + Api.url + "tagam24/images/" + list1.get(position).getImage()).asBitmap().fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.imageView);
        holder.price.setText(list1.get(position).getPrice() + " " + dil.manat);
        holder.price.setTypeface(typeregular);

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
        if (Constants.open.equals("1")) {
            holder.add_to_cart.setEnabled(false);
            holder.add_image.setImageResource(R.drawable.lock2);
            holder.add_image.setColorFilter(ContextCompat.getColor(cntx, R.color.red), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            holder.add_to_cart.setEnabled(true);
            holder.add_image.setImageResource(R.drawable.plus_icon);
        }
        final Model_Food m = list1.get(position);
        if (db.isInFood(m.getId())) {
            holder.like.setImageResource(R.drawable.heart_icon);
        } else {
            holder.like.setImageResource(R.drawable.heart_bos);
        }


        holder.l_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!db.isInRate_nahar(list1.get(position).getId())){


                   rate_nahar rr=new rate_nahar();
                    rr.setId(list1.get(position).getId());
                    Cafes_menu.s2.sendEmptyMessage(1);} else
                Toast.makeText(cntx,dil.bahalandy,Toast.LENGTH_LONG).show();

                }

        });



        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!db.isInFood(m.getId())) {
                    db.inser_liked_food(m.getId(), m.getImage(), m.getImage1(), m.getName(), m.getPrice(), m.getRating(), m.getRate_number(), m.getCafeID());
                    final Animation myAnim = AnimationUtils.loadAnimation(cntx, R.anim.bounce);
                    MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                    myAnim.setInterpolator(interpolator);
                    holder.like.startAnimation(myAnim);
                    holder.like.setImageResource(R.drawable.heart_icon);

                    db.inser_parent_cafe(Constants.cafesel.getId(), Constants.cafesel.getImage(), Constants.cafesel.getName()
                            , Constants.cafesel.getAddress(), Constants.cafesel.getRating(), Constants.cafesel.getWork_start(),
                            Constants.cafesel.getLogo(), Constants.cafesel.getDostawka_price(), Constants.cafesel.getCategory());
                } else {
                    final Animation myAnim = AnimationUtils.loadAnimation(cntx, R.anim.bounce);
                    MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                    myAnim.setInterpolator(interpolator);
                    holder.like.startAnimation(myAnim);
                    holder.like.setImageResource(R.drawable.heart_bos);
                    db.delete_food(m.getId());
                }

            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog d = new Dialog(cntx);
                d.requestWindowFeature(Window.FEATURE_NO_TITLE);
                d.setContentView(R.layout.show_image_card);
                ImageView imageView_clear = (ImageView) d.findViewById(R.id.clear);
                ImageView main = (ImageView) d.findViewById(R.id.main_image);
                d.show();
                imageView_clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        d.dismiss();
                    }
                });
                ImageView imageView_main = (ImageView) d.findViewById(R.id.main_image);
                Glide.with(cntx)
                        .load("http://" + Api.url + "tagam24/images/" + list1.get(position).getImage1()).asBitmap().centerCrop().fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(imageView_main);
            }

        });

        holder.add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Constants.basketCafeId.equals(Constants.cafeID) || Constants.cart_list.size() == 0) {
                    if (!Constants.ids.contains(list1.get(position).getId())) {

                        //addd
                        Constants.ids.add(list1.get(position).getId());
                        Constants.cart_list.add(list1.get(position));
                        Constants.basketCafeId = Constants.cafeID;
                        Constants.cafesel = Constants.cafe.get(Constants.positionCafe);
                        String price = list1.get(position).getPrice().replace(",", ".");
                        Constants.tt_price += Float.parseFloat(price);
                        holder.add_to_cart.setCardBackgroundColor(color);
                        holder.add_image.setImageResource(R.drawable.plus_icon);
                        holder.add_image.setColorFilter(ContextCompat.getColor(cntx, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
                        final Animation myAnim = AnimationUtils.loadAnimation(cntx, R.anim.bounce);
                        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                        myAnim.setInterpolator(interpolator);
                        holder.add_to_cart.startAnimation(myAnim);
                        Cafes_menu.s1.sendEmptyMessage(1);
                    } else {
                        //remove
                        String price = list1.get(position).getPrice().replace(",", ".");
                        Constants.tt_price -= Float.parseFloat(price);
                        Constants.ids.remove(list1.get(position).getId());
                        for(Model_Food m:Constants.cart_list){
                            if(m.getId().equals(list1.get(position).getId()))
                            {Constants.cart_list.remove(m);
                            break;}
                        }

                        holder.add_to_cart.setCardBackgroundColor(Color.WHITE);
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
                        Cafes_menu.s1.sendEmptyMessage(1);
                    }
                } else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(cntx);
                    builder.setMessage("Sebedi bosatmaly")
                            .setPositiveButton("Hawa", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    t = true;
                                    //clear and add
                                    Constants.tt_price = 0;
                                    String price = list1.get(position).getPrice().replace(",", ".");
                                    Constants.tt_price += Float.parseFloat(price);
                                    Constants.ids.clear();
                                    Constants.cart_list.clear();
                                    Constants.cafesel = Constants.cafe.get(Constants.positionCafe);
                                    Constants.cart_list.add(list1.get(position));
                                    Constants.ids.add(list1.get(position).getId());
                                    Constants.basketCafeId = Constants.cafeID;
                                    //holder.add_to_cart.setCardBackgroundColor(cntx.getResources().getColor(R.attr.colorPrimary));
                                    holder.add_to_cart.setCardBackgroundColor(color);
                                    holder.add_image.setImageResource(R.drawable.plus_icon);
                                    holder.add_image.setColorFilter(ContextCompat.getColor(cntx, R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);
                                    final Animation myAnim = AnimationUtils.loadAnimation(cntx, R.anim.bounce);
                                    MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                                    myAnim.setInterpolator(interpolator);
                                    Cafes_menu.s1.sendEmptyMessage(1);
                                }
                            })
                            .setNegativeButton("yok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    t = false;
                                }
                            });
                    // Create the AlertDialog object and return it
                    builder.create().show();


                }

            }
        });


    }

    class MyActivity implements RatingDialogListener {

        @Override
        public void onPositiveButtonClicked(int rate, String comment) {
            Toast.makeText(cntx,rate+" ",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNegativeButtonClicked() {
            Toast.makeText(cntx," asd",Toast.LENGTH_LONG).show();

        }

        @Override
        public void onNeutralButtonClicked() {

        }
    }
    @Override
    public int getItemCount() {
        return list1.size();
    }

    public void setData(ArrayList<Model_Food> food) {
        list1 = food;
        notifyDataSetChanged();
    }




}
