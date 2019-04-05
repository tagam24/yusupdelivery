package com.tagam24.tagam.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.tagam24.tagam.cafe_bar.Kafe_bar_mod;
import com.tagam24.tagam.gumenje.recept;
import com.tagam24.tagam.models.Model_Cafe;
import com.tagam24.tagam.models.Model_Food;
import com.tagam24.tagam.models.Model_footbal;
import com.tagam24.tagam.models.model_vote;

import java.util.ArrayList;


/**
 * Created by User on 29.01.2019.
 */

public class Db extends SQLiteOpenHelper {
    Context x;
    public Db(Context context) {

        super(context, "tagam24", null,7);
        this.x=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table dil(dili varhar(3))");
        sqLiteDatabase.execSQL("create table color1(color varhar(3))");
        sqLiteDatabase.execSQL("create table football_vote(id varchar(10),vote varchar(4))");
        sqLiteDatabase.execSQL("create table liked_cafe(id varchar(10),image varchar(100),name varchar(100),address varchar(100),work_start varchar(100),rating varchar(10),n_people varchar(10),logo varchar(50),dostawka_price varchar(5),category varchar(2),aksiya varchar(2))");
        sqLiteDatabase.execSQL("create table liked_food(id integer,name varchar(100),price varchar(100),rate_number integer,image varchar(200),image1 varchar(200),rating integer,cafeID varchar(10))");
        sqLiteDatabase.execSQL("create table liked_foods_parent(id varchar(10),image varchar(100),name varchar(100),address varchar(100),work_start varchar(100),rating varchar(10),logo varchar(50),dostawka_price varchar(5),category varchar(2))");
        sqLiteDatabase.execSQL("create table liked_futbol(id integer,name varchar(100),image varchar(100),image1 varchar(100),watched integer,date varchar(100),team1 integer,draw integer,team2 integer,content text,team_n1 varchar(100),draw_n varchar(100),team_n2 varchar(100),rating integer)");
        sqLiteDatabase.execSQL("create table liked_kafe_bar(id integer,name varchar(100),image varchar(100),image1 varchar(100),image2 varchar(100),image3 varchar(100),watch integer,n_people integer,rating integer,address varchar(100),work_time varchar(100),work_data varchar(100),kafe_or_restourant varchar(2),karta_image varchar(100),number varchar(30),content text)");
        sqLiteDatabase.execSQL("create table liked_receipe_duzumi(id integer,name varchar(100),image varchar(100),image1 varchar(100),image2 varchar(100),n_people integer,rating integer,watch integer,content varchar(100),text1 text ,text2 text,date varchar(100))");
        sqLiteDatabase.execSQL("create table liked_footbol_id(id varchar(10))");
        sqLiteDatabase.execSQL("create table rate_nahar(id varchar(100))");
        sqLiteDatabase.execSQL("create table rate_cafe_bar(id varchar(100))");
        sqLiteDatabase.execSQL("create table cafe_bar(id varchar(100))");
        sqLiteDatabase.execSQL("create table rate_futbol(id varchar(100))");
        sqLiteDatabase.execSQL("create table watch_futbol(id varchar(100))");
        sqLiteDatabase.execSQL("create table rate_recept(id varchar(100))");
        sqLiteDatabase.execSQL("create table watch_recept(id varchar(100))");
        sqLiteDatabase.execSQL("create table watch_cafe_bar(id varchar(100))");
        sqLiteDatabase.execSQL("create table watch_cafe_show(id varchar(100))");
        sqLiteDatabase.execSQL("create table rate_cafe_show(id varchar(100))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists " + "  dil");
        sqLiteDatabase.execSQL("Drop table if exists " + "  football_vote");
        sqLiteDatabase.execSQL("Drop table if exists " + "  color1");
        sqLiteDatabase.execSQL("Drop table if exists " + "  liked_cafe");
        sqLiteDatabase.execSQL("Drop table if exists " + "  liked_food");
        sqLiteDatabase.execSQL("Drop table if exists " + "  liked_kafe_bar");
        sqLiteDatabase.execSQL("Drop table if exists " + "  liked_futbol");
        sqLiteDatabase.execSQL("Drop table if exists " + "  liked_recept");
        sqLiteDatabase.execSQL("Drop table if exists " + "  liked_foods_parent");
        sqLiteDatabase.execSQL("Drop table if exists " + "  liked_receipe_duzumi");
        sqLiteDatabase.execSQL("Drop table if exists " + "  liked_footbol_id");
        sqLiteDatabase.execSQL("Drop table if exists " + "  liked_rate_nahar");
        sqLiteDatabase.execSQL("Drop table if exists " + "  rate_nahar");
        sqLiteDatabase.execSQL("Drop table if exists " + "  rate_cafe");
        sqLiteDatabase.execSQL("Drop table if exists " + "  rate_cafe_bar");
        sqLiteDatabase.execSQL("Drop table if exists " + "  cafe_bar");
        sqLiteDatabase.execSQL("Drop table if exists " + "  rate_futbol");;
        sqLiteDatabase.execSQL("Drop table if exists " + "  watch_futbol");
        sqLiteDatabase.execSQL("Drop table if exists " + "  rate_recept");
        sqLiteDatabase.execSQL("Drop table if exists " + "  watch_recept");
        sqLiteDatabase.execSQL("Drop table if exists " + "  watch_cafe_bar");
        sqLiteDatabase.execSQL("Drop table if exists " + "  watch_cafe_show");
        sqLiteDatabase.execSQL("Drop table if exists " + "  rate_cafe_show");
        onCreate(sqLiteDatabase);
    }//dili alyan
    public String get_dil() {
        try {


            SQLiteDatabase db = this.getWritableDatabase();
            String dil = "";
            Cursor c = db.query("dil", null, null, null, null, null, null, null);
            if (c.moveToNext()) dil = c.getString(c.getColumnIndex("dili"));
            c.close();
            return dil;
        } catch (NullPointerException ss) {
        }

        return "";
    }


    //dil saylananda
    public void inser_dil(String dil) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("dil", null, null);
        ContentValues c = new ContentValues();
        c.put("dili", dil);
        db.insert("dil", null, c);
    }

    //footbal saylanan
    public void inser_footbol_id(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("id", id);
        db.insert("liked_footbol_id", null, c);
    }

    public  void  insert_vote(String id,String vote)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "insert into football_vote values(?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransaction();
        try {
            statement.clearBindings();
            statement.bindString(1, id);
            statement.bindString(2, vote);
            statement.execute();
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

    }
    public model_vote get_footbal_vote(String id) {
        Log.d("Foooooootballlll","get gitrdiiiiiii");
        model_vote vote=new model_vote("","");
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c = db.query("football_vote", null, "id=?", new String[]{id}, null, null, null, null);
            Log.d("sizeCursor",""+c.getCount());
           if(c.moveToNext()) {
                vote=new model_vote(c.getString(c.getColumnIndex("id")),c.getString(c.getColumnIndex("vote")));}
            c.close();
            return vote;
        } catch (NullPointerException ss) {
        }
        return vote ;
    }

    public void inser_color(String color) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("color1", null, null);
        ContentValues c = new ContentValues();
        c.put("color", color);
        db.insert("color1", null, c);
    }

    //dili alyan
    public String get_color() {
        try {


            SQLiteDatabase db = this.getWritableDatabase();
            String dil = "";
            Cursor c = db.query("color1", null, null, null, null, null, null, null);
            if (c.moveToNext()) dil = c.getString(c.getColumnIndex("color"));
            c.close();
            return dil;
        } catch (NullPointerException ss) {
        }

        return "";
    }
    //insert_cafe
    public void inser_liked_cafe(String id,String image, String name, String address, String rating, String n_people, String work_start,String logo,String dostawka_price,String category,String aksiya) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "insert into liked_cafe values(?,?,?,?,?,?,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransaction();
        try {
            statement.clearBindings();
            statement.bindString(1, id);
            statement.bindString(2, image);
            statement.bindString(3, name);
            statement.bindString(4,address);
            statement.bindString(5,work_start);
            statement.bindString(6, rating);
            statement.bindString(7, n_people);
            statement.bindString(8, logo);
            statement.bindString(9, dostawka_price);
            statement.bindString(10,category);
            statement.bindString(11,aksiya);
            statement.execute();
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        Cursor c1 = db.query("liked_cafe", null, null, null, null, null, null, null);
        Log.d("sizeCursorIn",""+c1.getCount());

    }

    //get_cafe
    public ArrayList<Model_Cafe> get_liked_cafe() {
        ArrayList<Model_Cafe> cafe=new ArrayList<>();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c = db.query("liked_cafe", null, null, null, null, null, null, null);
            Log.d("sizeCursor",""+c.getCount());
            while (c.moveToNext()) {
                cafe.add(new Model_Cafe(c.getString(c.getColumnIndex("id")),c.getString(c.getColumnIndex("image")),
                        c.getString(c.getColumnIndex("name")),c.getString(c.getColumnIndex("address")),c.getString(c.getColumnIndex("rating")),c.getString(c.getColumnIndex("n_people")),
                        c.getString(c.getColumnIndex("work_start")),
                        c.getString(c.getColumnIndex("logo")),c.getString(c.getColumnIndex("dostawka_price")),c.getString(c.getColumnIndex("category"))));}
            c.close();
            Log.d("sizeDb",""+cafe.size());
            return cafe;
        } catch (NullPointerException ss) {
        }

        return cafe ;
    }

    public boolean isInCafe(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        boolean s=false;
        Cursor c=db.rawQuery("Select ID from liked_cafe where id=?",new String[]{id});
        if(c.moveToNext())s=true;
        c.close();
        return  s;

    }


    //delete cafe
    public void delete_cafe(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("liked_cafe", "id=?", new String[]{id});
    }
    //insert_food

    public void inser_liked_food(String id,String image,String image1, String name, String price, String rating, String rate_number,String cafeID) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "insert into liked_food values(?,?,?,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransaction();
        try {
            statement.clearBindings();
            statement.bindString(1, id);
            statement.bindString(2, name);
            statement.bindString(3, price);
            statement.bindString(4,rate_number);
            statement.bindString(5,image);
            statement.bindString(6,image1);
            statement.bindString(7, rating);
            statement.bindString(8, cafeID);
            statement.execute();
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
    public boolean isInFood(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        boolean s=false;
        Cursor c=db.rawQuery("Select ID from  liked_food where id=?",new String[]{id});
        if(c.moveToNext())s=true;
        c.close();
        return  s;

    }
    public boolean isInFootbol_id(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        boolean s=false;
        Cursor c=db.rawQuery("Select ID from  liked_footbol_id where id=?",new String[]{id});
        if(c.moveToNext())s=true;
        c.close();
        return  s;
    }

    //get_foods
    public ArrayList<Model_Food> get_liked_food() {
        ArrayList<Model_Food> food=new ArrayList<>();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c = db.query("liked_food", null, null, null, null, null, null, null);
            while (c.moveToNext()) {

                food.add(new Model_Food(c.getString(c.getColumnIndex("id")),c.getString(c.getColumnIndex("image")),c.getString(c.getColumnIndex("image1")),
                        c.getString(c.getColumnIndex("name")),c.getString(c.getColumnIndex("price")),c.getString(c.getColumnIndex("rating")),
                        c.getString(c.getColumnIndex("rate_number")),c.getString(c.getColumnIndex("cafeID")),"0"));}
            c.close();
            return food;
        } catch (NullPointerException ss) {
        }

        return food ;
    }
    //delete food
    public void delete_food(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("liked_food", "id=?", new String[]{id});
    }
    public void inser_parent_cafe(String id,String image, String name, String address, String rating, String work_start,String logo,String dostawka_price,String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "insert into liked_foods_parent values(?,?,?,?,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransaction();
        try {
            statement.clearBindings();
            statement.bindString(1, id);
            statement.bindString(2, image);
            statement.bindString(3, name);
            statement.bindString(4,address);
            statement.bindString(5,work_start);
            statement.bindString(6, rating);
            statement.bindString(7, logo);
            statement.bindString(8, dostawka_price);
            statement.bindString(9, category);
            statement.execute();
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

    }
    public Model_Cafe get_parent_cafe(String cafeID) {
        Model_Cafe cafe=new Model_Cafe();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c = db.query("liked_foods_parent", null, "id=?",new String[]{cafeID}, null, null, null, null);

            while (c.moveToNext()) {
                cafe=new Model_Cafe(c.getString(c.getColumnIndex("id")),c.getString(c.getColumnIndex("image")),
                        c.getString(c.getColumnIndex("name")),
                        c.getString(c.getColumnIndex("address")),
                        c.getString(c.getColumnIndex("rating")),
                        "",
                        c.getString(c.getColumnIndex("work_start")),
                        c.getString(c.getColumnIndex("logo")),c.getString(c.getColumnIndex("dostawka_price")),c.getString(c.getColumnIndex("category")));}
            c.close();

            return cafe;
        } catch (NullPointerException ss) {
        }
        return cafe ;

    }
    public void inser_kafe_bar(String id,String image,String image1,String image2,String image3,
                               String name, String address, String n_people, String s_people,
                               String rating,String watch,String work_time,String work_data,
                               String kafe_or_restourant,String karta_image,String number,String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "insert into liked_kafe_bar values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransaction();
        try {
            statement.clearBindings();
            statement.bindString(1, id);
            statement.bindString(2, name);
            statement.bindString(3, image);
            statement.bindString(4, image1);
            statement.bindString(5, image2);
            statement.bindString(6, image3);
            statement.bindString(7, watch);
            statement.bindString(8, n_people);
            statement.bindString(9, rating);
            statement.bindString(10, address);
            statement.bindString(11, work_time);
            statement.bindString(12, work_data);
            statement.bindString(13, kafe_or_restourant);
            statement.bindString(14, karta_image);
            statement.bindString(15,number);
            statement.bindString(16,content);
            statement.execute();
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }


    }
    //delete_kafe_bar
    public void delete_kafe_bar(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("liked_kafe_bar", "id=?", new String[]{id});
    }
    public boolean isInCafebar(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        boolean s=false;
        Cursor c=db.rawQuery("Select ID from liked_kafe_bar where id=?",new String[]{id});
        if(c.moveToNext())s=true;
        c.close();
        return  s;

    }
    public ArrayList<Kafe_bar_mod> get_liked_cafe_bar() {
        ArrayList<Kafe_bar_mod> cafe=new ArrayList<>();
        try {



            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c = db.query("liked_kafe_bar", null, null, null, null, null, null, null);
            while (c.moveToNext()) {
                cafe.add(new Kafe_bar_mod(c.getString(c.getColumnIndex("id")),c.getString(c.getColumnIndex("name")),
                        c.getString(c.getColumnIndex("image")),c.getString(c.getColumnIndex("image1")),c.getString(c.getColumnIndex("image2")),
                        c.getString(c.getColumnIndex("image3")),c.getString(c.getColumnIndex("content")),c.getString(c.getColumnIndex("watch")),
                        c.getString(c.getColumnIndex("n_people")),"0",c.getString(c.getColumnIndex("rating")),
                        c.getString(c.getColumnIndex("address")),c.getString(c.getColumnIndex("work_time")),c.getString(c.getColumnIndex("work_data")),
                        c.getString(c.getColumnIndex("karta_image")),c.getString(c.getColumnIndex("number"))));}
            c.close();
            return cafe;
        } catch (NullPointerException ss) {
        }

        return cafe ;
    }
    //delete_futbol
    public void delete_receipe_duzumi(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("liked_receipe_duzumi", "id=?", new String[]{id});
    }
    public void inser_receipe_duzumi(String id,String image,String name, String content, String watched, String rating, String n_people, String text1, String text2,String image2,String image1,String date) {

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "insert into liked_receipe_duzumi values(?,?,?,?,?,?,?,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);

        db.beginTransaction();
        try {
            statement.clearBindings();
            statement.bindString(1, id);
            statement.bindString(2, name);
            statement.bindString(3, image);
            statement.bindString(4, image1);
            statement.bindString(5, image2);
            statement.bindString(6, n_people);
            statement.bindString(7, rating);
            statement.bindString(8, watched);
            statement.bindString(9, content);
            statement.bindString(10, text1);
            statement.bindString(11, text2);
            statement.bindString(12, date);
            statement.execute();
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
    public boolean isInReceipeduzumi(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        boolean s=false;
        Cursor c=db.rawQuery("Select ID from liked_receipe_duzumi where id=?",new String[]{id});
        if(c.moveToNext())s=true;
        c.close();
        return  s;

    }
    public ArrayList<recept> get_receipe_duzumi() {
        ArrayList<recept> cafe=new ArrayList<>();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c = db.query("liked_receipe_duzumi", null, null, null, null, null, null, null);
            Log.d("sizeCursor",""+c.getCount());
            while (c.moveToNext()) {

                  cafe.add(new recept(c.getString(c.getColumnIndex("id")),c.getString(c.getColumnIndex("name")),
                        c.getString(c.getColumnIndex("image")),c.getString(c.getColumnIndex("image1")),c.getString(c.getColumnIndex("image2")),
                        "",c.getString(c.getColumnIndex("n_people")),c.getString(c.getColumnIndex("rating")),
                        c.getString(c.getColumnIndex("watch")),c.getString(c.getColumnIndex("content")),c.getString(c.getColumnIndex("text1")),c.getString(c.getColumnIndex("text2")),c.getString(c.getColumnIndex("date"))));
                            }
            c.close();
            Log.d("sizeDb",""+cafe.size());
            return cafe;
        } catch (NullPointerException ss) {
        }

        return cafe ;
    }
    public ArrayList<Model_footbal> get_liked_footbal() {
        ArrayList<Model_footbal> cafe=new ArrayList<>();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c = db.query("liked_futbol", null, null, null, null, null, null, null);
            Log.d("sizeCursor",""+c.getCount());
            while (c.moveToNext()) {
             /* this.id = id;
                this.image = image;
                this.image1 = image1;
                this.name = name;
                this.watched = watched;
                this.liked = liked;
                this.date = date;
                this.content = content;
                this.team1 = team1;
                this.draw = draw;
                this.team2 = team2;
                this.team_n1=team_n1;
                this.team_n2=team_n2;
            }*/
                cafe.add(new Model_footbal(c.getString(c.getColumnIndex("id")),c.getString(c.getColumnIndex("image")),c.getString(c.getColumnIndex("image1")),c.getString(c.getColumnIndex("name")),
                        c.getString(c.getColumnIndex("watched")),c.getString(c.getColumnIndex("rating")),c.getString(c.getColumnIndex("date")),c.getString(c.getColumnIndex("content")),
                        c.getString(c.getColumnIndex("team1")), c.getString(c.getColumnIndex("draw")),c.getString(c.getColumnIndex("team2")),c.getString(c.getColumnIndex("team_n1")),c.getString(c.getColumnIndex("draw_n")),c.getString(c.getColumnIndex("team_n2"))));}
            c.close();
            Log.d("sizeDb",""+cafe.size());
            return cafe;
        } catch (NullPointerException ss) {
        }

        return cafe ;
    }

    public void inser_futbol(String id, String image, String image1, String name, String watched, String date, String content, String team1, String draw, String team2,String team_n1,String draw_n,String team_n2, String rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "insert into liked_futbol values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransaction();
        try {
            statement.clearBindings();
            statement.bindString(1, id);
            statement.bindString(3, image);
            statement.bindString(4, image1);
            statement.bindString(2, name);
            statement.bindString(5, watched);
            statement.bindString(6, date);
            statement.bindString(10, content);
            statement.bindString(7, team1);
            statement.bindString(8, draw);
            statement.bindString(9, team2);
            statement.bindString(11, team_n1);
            statement.bindString(11, draw_n);
            statement.bindString(13, team_n2);
            statement.bindString(14, rating);

            statement.execute();
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
    public boolean isInFutbol(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        boolean s=false;
        Cursor c=db.rawQuery("Select ID from liked_futbol where id=?",new String[]{id});
        if(c.moveToNext())s=true;
        c.close();
        return  s;

    }

    //delete_futbol
    public void delete_futbol(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("liked_futbol", "id=?", new String[]{id});
    }
    //isInrate_nahar
    public boolean isInRate_nahar(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        boolean s=false;
        Cursor c=db.rawQuery("Select ID from "+"rate_nahar"+" where id=?",new String[]{id});
        if(c.moveToNext())s=true;
        c.close();
        return  s;

    }
    public void inser_Rate(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("id", id);
        db.insert("rate_nahar", null, c);
    }

    //isInrate_futbol
    public boolean isInRate_futbol(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        boolean s=false;
        Cursor c=db.rawQuery("Select ID from "+"rate_futbol"+" where id=?",new String[]{id});
        if(c.moveToNext())s=true;
        c.close();
        return  s;

    }
    public void inser_Rate_futbol(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("id", id);
        db.insert("rate_futbol", null, c);

    }//isInrate_watch
    public boolean isInWatch_futbol(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        boolean s=false;
        Cursor c=db.rawQuery("Select ID from "+"watch_futbol"+" where id=?",new String[]{id});
        if(c.moveToNext())s=true;
        c.close();
        return  s;

    }
    public void inser_Watch_futbol(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("id", id);
        db.insert("watch_futbol", null, c);

    }
    public boolean isInRate_recept(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        boolean s=false;
        Cursor c=db.rawQuery("Select ID from "+"rate_recept"+" where id=?",new String[]{id});
        if(c.moveToNext())s=true;
        c.close();
        return  s;

    }
    public void inser_Rate_recept(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("id", id);
        db.insert("rate_recept", null, c);

    }

    public boolean isInWatch_recept(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        boolean s=false;
        Cursor c=db.rawQuery("Select ID from "+"watch_recept"+" where id=?",new String[]{id});
        if(c.moveToNext())s=true;
        c.close();
        return  s;

    }
    public void inser_Watch_recept(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("id", id);
        db.insert("watch_recept", null, c);

    }

    public boolean isInWatch_cafe_bar(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        boolean s=false;
        Cursor c=db.rawQuery("Select ID from "+"watch_cafe_bar"+" where id=?",new String[]{id});
        if(c.moveToNext())s=true;
        c.close();
        return  s;

    }
    public void inser_Watch_cafe_bar(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("id", id);
        db.insert("watch_cafe_bar", null, c);

    }

    public boolean isInRate_cafe_bar(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        boolean s=false;
        Cursor c=db.rawQuery("Select ID from "+"rate_cafe_bar"+" where id=?",new String[]{id});
        if(c.moveToNext())s=true;
        c.close();
        return  s;

    }

    public void inser_Rate_cafe_bar(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("id", id);
        db.insert("rate_cafe_bar", null, c);

    }
    public boolean isInWatch_cafe_show(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        boolean s=false;
        Cursor c=db.rawQuery("Select ID from "+"watch_cafe_show"+" where id=?",new String[]{id});
        if(c.moveToNext())s=true;
        c.close();
        return  s;

    }

    public void inser_watch_cafe_show(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("id", id);
        db.insert("watch_cafe_show", null, c);

    }
    public boolean isInRate_cafe_show(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        boolean s=false;
        Cursor c=db.rawQuery("Select ID from "+"rate_cafe_show"+" where id=?",new String[]{id});
        if(c.moveToNext())s=true;
        c.close();
        return  s;

    }

    public void inser_rate_cafe_show(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("id", id);
        db.insert("rate_cafe_show", null, c);

    }


}


