package com.tagam24.tagam.Constants;


import com.tagam24.tagam.cafe_bar.Kafe_bar_mod;
import com.tagam24.tagam.gumenje.recept;
import com.tagam24.tagam.models.Model_Cafe;
import com.tagam24.tagam.models.Model_Cafe_show;
import com.tagam24.tagam.models.Model_Food;
import com.tagam24.tagam.models.Model_footbal;
import com.tagam24.tagam.models.model_banner;
import com.tagam24.tagam.models.model_user;
import com.tagam24.tagam.order.Model_myorder1;
import com.tagam24.tagam.order.Model_myorder2;

import java.util.ArrayList;


/**
 * Created by User on 25.02.2019.
 */

public class Constants {
    public  static  boolean iter=true;
    public  static  String  dil="", open="",order_id="",name,location="",categoryCafe="",categoryFood="0",cafeID="",basketCafeId="",cafeName="",srcCafe="",aksiya="",cafemy="0";
    public  static Model_Cafe cafesel=new Model_Cafe("","","","","","","","","","");
    public  static String number="",address="";
    public  static  float tt_price=0;
    public  static  int size=0,positionCafe=0,df_rate=0;
    public  static ArrayList<String> ids=new ArrayList<>();
    public  static ArrayList<String> liked_ids=new ArrayList<>();
    public  static ArrayList<Kafe_bar_mod> banner=new ArrayList<>();
    public  static ArrayList<String> mainImages=new ArrayList<>();
    public  static ArrayList<Model_Cafe> cafe=new ArrayList<>();
    public  static ArrayList<Model_Cafe> cafe_like=new ArrayList<>();
    public  static Model_Cafe_show cafe_show;
    public  static ArrayList<Kafe_bar_mod> cafe_bar=new ArrayList<>();
    public  static ArrayList<Kafe_bar_mod> cafe_bar_like=new ArrayList<>();
    public  static ArrayList<Model_Food> food=new ArrayList<>();
    public  static ArrayList<Model_Food> foodlike=new ArrayList<>();
    public  static ArrayList<Model_Food> cart_food=new ArrayList<>();
    public static  ArrayList<Model_Food> cart_list=new ArrayList<>();
    public static model_user user;
    public static  ArrayList<Model_footbal> footbal=new ArrayList<>();
    public static  ArrayList<Model_footbal> footbal_like=new ArrayList<>();
    public static  ArrayList<recept> recepts=new ArrayList<>();
    public static  ArrayList<recept> recepts_like=new ArrayList<>();
    public static ArrayList<String> idSC=new ArrayList<>();
    public static ArrayList<String> idfd=new ArrayList<>();
    public static ArrayList<Model_myorder1> orderid=new ArrayList<>();
    public static ArrayList<Model_myorder2> myorder=new ArrayList<>();



}
