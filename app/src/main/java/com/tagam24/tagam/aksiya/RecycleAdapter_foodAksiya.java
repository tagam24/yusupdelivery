package com.tagam24.tagam.aksiya;

/**
 * Created by Sulik on 1/3/2019.
 */

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
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
import com.tagam24.tagam.Constants.Constants;
import com.tagam24.tagam.Database.Db;
import com.tagam24.tagam.R;
import com.tagam24.tagam.cafes_menu.Cafes_menu;
import com.tagam24.tagam.cafes_menu.MyBounceInterpolator;
import com.tagam24.tagam.dil;
import com.tagam24.tagam.models.Model_Food;
import com.tagam24.tagam.network.Api;
import com.tagam24.tagam.network.get_foodAksiya;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tagam24.tagam.rate_nahar;

import java.util.ArrayList;
import java.util.Arrays;


public class RecycleAdapter_foodAksiya extends RecyclerView.Adapter<RecycleAdapter_foodAksiya.Reklama_viewholder> {
    ArrayList<Model_Food> list1;
    Context cntx;
    Db db;
    dil dd;
    String table;
    Boolean b1 = false, b2 = false, b3 = false;
    boolean t = false;

    public class Reklama_viewholder extends RecyclerView.ViewHolder {
        TextView name, price, ratenumber, manat, count_number, time,aksiya;
        ImageView star1, star2, star3, star4, star5, like;
        CardView add_to_cart;
        RoundedImageView imageView;
        ImageView add_image;
        ViewGroup.LayoutParams ls1, ls2;
        LinearLayout l,l_rate;
        RelativeLayout relativeLayout, relativeLayout1, relativeLayout2, relativeLayout_minus, relativeLayout_plus;
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
            add_to_cart = (CardView) itemView.findViewById(R.id.add_to_cart);
            like = (ImageView) itemView.findViewById(R.id.like);
            l_rate=(LinearLayout)itemView.findViewById(R.id.l_rate);
            add_image = (ImageView) itemView.findViewById(R.id.image_add);
            aksiya=(TextView)itemView.findViewById(R.id.aksiya_txt);
            //  manat = (TextView) itemView.findViewById(R.id.manat_food_show);
            //    minus = (ImageView) itemView.findViewById(R.id.minus_button_food_recycle);
            //    plus = (ImageView) itemView.findViewById(R.id.plus_button_food_recycle);
            //  count_number = (TextView) itemView.findViewById(R.id.count_number_food_recycle);
            //    time=(TextView)itemView.findViewById(R.id.time_food_recycle);
            //    relativeLayout_minus=(RelativeLayout)itemView.findViewById(R.id.layout_minus_botton_food_recycle);
            //    relativeLayout_plus=(RelativeLayout)itemView.findViewById(R.id.layout_plus_botton_food_recycle);

        }
    }

    public RecycleAdapter_foodAksiya(ArrayList<Model_Food> items, Context ctx) {
        this.list1 = items;
        this.cntx = ctx;
        dd=new dil();
        db = new Db(cntx);
    }

    @Override
    public Reklama_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_aksiya2, parent, false);
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


        if (position == list1.size() - 1 && Constants.iter == true) {
            Constants.size = list1.size();
            get_foodAksiya.get_Data();
            arzanlad_menu.s2.sendEmptyMessage(1);
            Log.d("geldi", "" + position);

        }
        holder.aksiya.setText(list1.get(position).getAksiya());
        Log.d("id", list1.get(position).getId());
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = cntx.getTheme();
        theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
        @ColorInt final int color = typedValue.data;
        if (Constants.ids.contains(list1.get(position).getId())) {
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
            Glide.with(cntx)
                    .load("http://" + Api.url + "tagam24/images/" + list1.get(position).getImage()).asBitmap().fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(holder.imageView);
            holder.price.setText(list1.get(position).getPrice()+" "+dd.manat);
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
            if (Constants.open.equals("1")) {
                holder.add_to_cart.setEnabled(false);
                holder.add_image.setImageResource(R.drawable.lock2);
                holder.add_image.setColorFilter(ContextCompat.getColor(cntx, R.color.red), android.graphics.PorterDuff.Mode.SRC_IN);
            }else {
                holder.add_to_cart.setEnabled(true);
                holder.add_image.setImageResource(R.drawable.plus_icon);
            }
            final Model_Food m = list1.get(position);
            if (db.isInFood(m.getId())) {
                holder.like.setImageResource(R.drawable.heart_icon);
            } else {
                holder.like.setImageResource(R.drawable.heart_bos);
            }

            holder.like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (!db.isInFood(m.getId())) {
                        db.inser_liked_food(m.getId(), m.getImage(),m.getImage1(), m.getName(), m.getPrice(), m.getRating(), m.getRate_number(), m.getCafeID());
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
                if (db.isInRate_nahar(list1.get(position).getId())){
                holder.l_rate.setEnabled(false);
                Toast.makeText(cntx,dil.bahalandy,Toast.LENGTH_LONG).show();}

                holder.l_rate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rate_nahar rr=new rate_nahar();
                    rr.setId(list1.get(position).getId());
                   arzanlad_menu.s4.sendEmptyMessage(1);

                }

            });
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Dialog d = new Dialog(cntx);
                    d.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    d.setContentView(R.layout.show_image_card);
                    ImageView imageView_clear = (ImageView) d.findViewById(R.id.clear);


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
                            arzanlad_menu.s5.sendEmptyMessage(1);
                        } else {
                            //remove
                            String price = list1.get(position).getPrice().replace(",", ".");
                            Constants.tt_price -= Float.parseFloat(price);
                            Constants.ids.remove(list1.get(position).getId());
                            Constants.cart_list.remove(list1.get(position));
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
                           arzanlad_menu.s5.sendEmptyMessage(1);
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
                                        arzanlad_menu.s5.sendEmptyMessage(1);
                                    }
                                })
                                .setNegativeButton("yok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        t = false;
                                    }
                                });
                        // Create the AlertDialog object and return it
                        builder.create().show();


                    }      }
            });
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
