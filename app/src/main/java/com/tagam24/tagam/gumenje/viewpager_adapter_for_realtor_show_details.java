package com.tagam24.tagam.gumenje;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tagam24.tagam.ImageModel;
import com.tagam24.tagam.R;

import java.util.ArrayList;

public class viewpager_adapter_for_realtor_show_details extends PagerAdapter {

    private ArrayList<ImageModel> images;
    private LayoutInflater inflater;
    private Context context;

    public viewpager_adapter_for_realtor_show_details(Context context, ArrayList<ImageModel> images) {
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
        View myImageLayout = inflater.inflate(R.layout.card_for_viewpager, view, false);
        ImageView myImage = (ImageView) myImageLayout
                .findViewById(R.id.image_for_viewpager_for_beylekiler_show_details);
        myImage.setImageResource(images.get(position).getImage_drawable());
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

}
