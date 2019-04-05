package com.tagam24.tagam.cafe_bar;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tagam24.tagam.R;
import com.tagam24.tagam.network.Api;

import java.util.ArrayList;


public class ViewPager_Adapter_adds extends PagerAdapter {

    private ArrayList<String> images;
    private LayoutInflater inflater;
    private Context context;

    public ViewPager_Adapter_adds(Context context, ArrayList<String> images) {
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(context);
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View myImageLayout = inflater.inflate(R.layout.card_for_viewadds, view, false);
        ImageView myImage = (ImageView) myImageLayout
                .findViewById(R.id.image_for_viewpager_for_beylekiler_show_details);
        Log.d("image","http://"+ Api.url+"tagam24/images/" + images.get(position));
        Glide.with(context)
                .load("http://"+ Api.url+"tagam24/images/" + images.get(position)).asBitmap().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(myImage);
        view.addView(myImageLayout);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
    void notif(ArrayList<String> b){
         images=b;
        notifyDataSetChanged();
    }
}
