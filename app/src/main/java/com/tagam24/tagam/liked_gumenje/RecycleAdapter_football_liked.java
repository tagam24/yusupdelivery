package com.tagam24.tagam.liked_gumenje;

/**
 * Created by Sulik on 1/3/2019.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.codesgood.views.JustifiedTextView;
import com.tagam24.tagam.Database.Db;
import com.tagam24.tagam.R;
import com.tagam24.tagam.gumenje.footbal_show_details;
import com.tagam24.tagam.models.Model_footbal;
import com.tagam24.tagam.network.Api;

import java.util.ArrayList;


public class RecycleAdapter_football_liked extends RecyclerView.Adapter<RecycleAdapter_football_liked.Reklama_viewholder> {
    ArrayList<Model_footbal> list1;
    Context cntx;
    String table;
    Boolean b1 = false, b2 = false, b3 = false;
    Db db;
    public class Reklama_viewholder extends RecyclerView.ViewHolder {
        TextView name, time,like,watch;
        JustifiedTextView contet;
        ImageView imageView;
        ViewGroup.LayoutParams ls1, ls2;
        LinearLayout l;
        MaterialRippleLayout materialRippleLayout;
        RelativeLayout relativeLayout, relativeLayout1, relativeLayout2, relativeLayout_minus, relativeLayout_plus;
        LinearLayout.LayoutParams ls;
        LinearLayout linearLayout;


        public Reklama_viewholder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            contet = (JustifiedTextView) itemView.findViewById(R.id.content);
            watch = (TextView) itemView.findViewById(R.id.watch);
            like = (TextView) itemView.findViewById(R.id.like);
            time = (TextView) itemView.findViewById(R.id.time);

            imageView = (ImageView) itemView.findViewById(R.id.image);

            materialRippleLayout=(MaterialRippleLayout)itemView.findViewById(R.id.effect);


        }
    }

    public RecycleAdapter_football_liked(ArrayList<Model_footbal> items, Context ctx) {
        this.list1 = items;
        this.cntx = ctx;
        db=new Db(cntx);
    }

    @Override
    public Reklama_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_footbal, parent, false);
        final Reklama_viewholder view = new Reklama_viewholder(v);
        return view;


    }

    @Override
    public void onBindViewHolder(final Reklama_viewholder holder, final int position) {
        holder.name.setText(list1.get(position).getName());
        holder.contet.setText(list1.get(position).getContent());
        holder.watch.setText(list1.get(position).getWatched());
        holder.like.setText(list1.get(position).getliked());
        holder.time.setText(list1.get(position).getDate());
        final Model_footbal m=list1.get(position);
        Glide.with(cntx)
                .load("http://"+ Api.url+"tagam24/images/" + list1.get(position).getImage()).asBitmap().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.imageView);

        holder.materialRippleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(cntx,footbal_show_details.class);
                intent.putExtra("from","football_liked");
                intent.putExtra("id",m.getId());
                intent.putExtra("name",m.getName());
                intent.putExtra("image1",m.getImage1());
                intent.putExtra("image",m.getImage());
                intent.putExtra("content",m.getContent());
                intent.putExtra("watch",m.getWatched());
                intent.putExtra("like",m.getliked());
                intent.putExtra("date",m.getDate());
                intent.putExtra("team1",m.getTeam1());
                intent.putExtra("draw",m.getDraw());
                intent.putExtra("team2",m.getTeam2());
                intent.putExtra("team_n1",m.getTeam_n1());
                intent.putExtra("team_n2",m.getTeam_n2());
                cntx.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public void setData(ArrayList<Model_footbal> cafe){
        list1=cafe;
        notifyDataSetChanged();
    }
}
