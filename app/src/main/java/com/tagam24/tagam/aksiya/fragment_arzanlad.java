package com.tagam24.tagam.aksiya;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tagam24.tagam.Constants.Constants;
import com.tagam24.tagam.R;
import com.tagam24.tagam.models.Model_Food;
import com.tagam24.tagam.network.get_foodAksiya;

import java.util.ArrayList;


public class fragment_arzanlad extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    int color;
    public static ArrayList<Model_Food> list;
    Context context;
SwipeRefreshLayout swipeRefreshLayout;
    public fragment_arzanlad() {
    }
public  static Handler s1,s2;
    @SuppressLint("ValidFragment")
    public fragment_arzanlad(int color) {
        this.color = color;
    }
 RecycleAdapter_foodAksiya recycleAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, container, false);
        context = view.getContext();
        Constants.size=0;
        Constants.food.clear();
        Constants.ids.clear();
     swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swipe);
     swipeRefreshLayout.setOnRefreshListener(this);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_dostawka_food);
        recycleAdapter = new RecycleAdapter_foodAksiya(Constants.food,context);
        recyclerView.setAdapter(recycleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        list = new ArrayList<>();


        s1=new Handler(){
            @Override
            public void handleMessage(Message msg) {

                Log.d("s1Handler",""+Constants.food.size());
                swipeRefreshLayout.setRefreshing(false);
           recycleAdapter.setData(Constants.food);
            }
        };

        s2=new Handler(){
            @Override
            public void handleMessage(Message msg) {
              swipeRefreshLayout.setRefreshing(true);
            }
        };
        get_foodAksiya.get_Data();
        return view;

    }

    @Override
    public void onRefresh() {
    Constants.food.clear();
        Constants.size=0;
        Constants.iter=true;
        recycleAdapter.setData(Constants.food);
        get_foodAksiya.get_Data();
    }
}
