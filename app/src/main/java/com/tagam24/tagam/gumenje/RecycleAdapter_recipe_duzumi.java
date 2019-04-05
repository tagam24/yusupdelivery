package com.tagam24.tagam.gumenje;

/**
 * Created by Sulik on 1/3/2019.
 */

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.tagam24.tagam.R;


public class RecycleAdapter_recipe_duzumi extends RecyclerView.Adapter<RecycleAdapter_recipe_duzumi.Reklama_viewholder> {
    String[] list1;
    Context cntx;
    String table;
    Boolean b1 = false, b2 = false, b3 = false;

    public class Reklama_viewholder extends RecyclerView.ViewHolder {
        TextView name, price, place,time;
        RoundedImageView imageView;
        ViewGroup.LayoutParams ls1, ls2;
        LinearLayout l;
        RelativeLayout relativeLayout, relativeLayout1, relativeLayout2,relativeLayout_minus,relativeLayout_plus;
        LinearLayout.LayoutParams ls;

        public Reklama_viewholder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.text_card_duzumi);


        }
    }

    public RecycleAdapter_recipe_duzumi(String [] items, Context ctx) {
        this.list1 = items;
        this.cntx = ctx;
    }

    @Override
    public Reklama_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_recipe_ingredient, parent, false);
        final Reklama_viewholder view = new Reklama_viewholder(v);
        return view;

    }

    @Override
    public void onBindViewHolder(final Reklama_viewholder holder, final int position) {
        Typeface typebold = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_Bold.ttf");
        Typeface typelight = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_Light.ttf");
        Typeface typeregular = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_Regular.ttf");
        Typeface typeextrabold = Typeface.createFromAsset(cntx.getAssets(), "fonts/OpenSans_ExtraBold.ttf");
        holder.name.setText(list1[position]);
        holder.name.setTypeface(typeregular);







        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return list1.length;
    }


}
