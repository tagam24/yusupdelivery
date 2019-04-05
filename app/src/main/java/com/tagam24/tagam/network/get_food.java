package com.tagam24.tagam.network;


import android.util.Log;

import com.tagam24.tagam.Constants.Constants;
import com.tagam24.tagam.cafes_menu.Cafes_menu;
import com.tagam24.tagam.cafes_menu.fragment_dostawka1;
import com.tagam24.tagam.cafes_menu.fragment_dostawka10;
import com.tagam24.tagam.cafes_menu.fragment_dostawka11;
import com.tagam24.tagam.cafes_menu.fragment_dostawka12;
import com.tagam24.tagam.cafes_menu.fragment_dostawka13;
import com.tagam24.tagam.cafes_menu.fragment_dostawka14;
import com.tagam24.tagam.cafes_menu.fragment_dostawka15;
import com.tagam24.tagam.cafes_menu.fragment_dostawka16;
import com.tagam24.tagam.cafes_menu.fragment_dostawka17;
import com.tagam24.tagam.cafes_menu.fragment_dostawka18;
import com.tagam24.tagam.cafes_menu.fragment_dostawka19;
import com.tagam24.tagam.cafes_menu.fragment_dostawka2;
import com.tagam24.tagam.cafes_menu.fragment_dostawka20;
import com.tagam24.tagam.cafes_menu.fragment_dostawka3;
import com.tagam24.tagam.cafes_menu.fragment_dostawka4;
import com.tagam24.tagam.cafes_menu.fragment_dostawka5;
import com.tagam24.tagam.cafes_menu.fragment_dostawka6;
import com.tagam24.tagam.cafes_menu.fragment_dostawka7;
import com.tagam24.tagam.cafes_menu.fragment_dostawka8;
import com.tagam24.tagam.cafes_menu.fragment_dostawka9;
import com.tagam24.tagam.models.Model_Food;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;


/**
 * Created by User on 27.06.2018.
 */

public class get_food {
   public  static String k;
    public  static int con=0;
    static String l;
    public static   Thread get_data=new Thread();
    public static void  get_Data(){
          Log.d("gelenok","ok");
        get_data=new Thread(new Runnable() {
            @Override
            public void run() {
                try{

                    URL url=new URL("http://"+Api.url+"tagam24/get_food.php");
                    Log.d("url",""+url);
                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setRequestMethod("POST");
                    conn.connect();
                    OutputStream outputStream = conn.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("category", "UTF-8") + "=" + URLEncoder.encode(Constants.categoryFood, "UTF-8")+
                            "&"+URLEncoder.encode("size", "UTF-8") + "=" + URLEncoder.encode(""+ Constants.size, "UTF-8")
                    +"&"+URLEncoder.encode("cafeID", "UTF-8") + "=" + URLEncoder.encode(""+Constants.cafesel.getId(), "UTF-8")
                            +"&"+URLEncoder.encode("aksiya", "UTF-8") + "=" + URLEncoder.encode(""+Constants.aksiya, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream in = conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    StringBuilder sb = new StringBuilder();
                    String row = "";
                    Log.d("post",post_data);
                    while ((row = br.readLine()) != null) {
                        sb.append(row + "\n");}
                    br.close();
                    in.close();
                    conn.disconnect();
                    if(sb.toString().length()<20)Constants.iter=false;
                    Log.d("post",""+Constants.iter);
                    if (!sb.toString().equals(""))GetJson(sb.toString());//idlar gelyar tazelemeli

                    Thread.currentThread().interrupt();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }) ;
       if(con==0) get_data.start();
    }

    public static void GetJson(final String s){
        Thread json=new Thread(new Runnable() {
            @Override
            public void run() {
                try{//bashda data basadan hemme informasiyasyny alyas
                    JSONObject jsonObject=new JSONObject(s);
                    JSONArray jsonArray=jsonObject.getJSONArray("result");
                    for(int i=0; i<jsonArray.length();i++) {
                        JSONObject data = jsonArray.getJSONObject(i);
                        if(!Constants.idfd.contains(data.optString("id"))) {
                        Constants.food.add(new Model_Food(data.optString("id"),data.optString("image"),data.optString("image1"),
                                data.optString("name"),data.optString("price"),
                                data.optString("rating"),data.optString("rate_number"),
                                data.optString("cafeID"),data.optString("vip")));
                            Constants.idfd.add(data.optString("id"));
                    }}
                    Log.d("Alimerdan","size:"+Constants.food.size()+"geldi:"+s);
                    if(Cafes_menu.categories.length>=1 && Cafes_menu.categories[0].equals(Constants.categoryFood)) {
if(fragment_dostawka1.s1!=null)                        fragment_dostawka1.s1.sendEmptyMessage(1);}
                    else
                    if(Cafes_menu.categories.length>=2 && Cafes_menu.categories[1].equals(Constants.categoryFood))
                        fragment_dostawka2.s1.sendEmptyMessage(1);   else
                    if(Cafes_menu.categories.length>=3 && Cafes_menu.categories[2].equals(Constants.categoryFood))

                        fragment_dostawka3.s1.sendEmptyMessage(1);   else
                    if(Cafes_menu.categories.length>=4 && Cafes_menu.categories[3].equals(Constants.categoryFood))
                        fragment_dostawka4.s1.sendEmptyMessage(1);   else
                    if(Cafes_menu.categories.length>=5 && Cafes_menu.categories[4].equals(Constants.categoryFood))
                        fragment_dostawka5.s1.sendEmptyMessage(1);   else
                    if(Cafes_menu.categories.length>=6 && Cafes_menu.categories[5].equals(Constants.categoryFood))
                        fragment_dostawka6.s1.sendEmptyMessage(1);   else
                    if(Cafes_menu.categories.length>=7 && Cafes_menu.categories[6].equals(Constants.categoryFood))
                        fragment_dostawka7.s1.sendEmptyMessage(1);
                    else
                    if(Cafes_menu.categories.length>=8 && Cafes_menu.categories[7].equals(Constants.categoryFood))
                        fragment_dostawka8.s1.sendEmptyMessage(1);
                    else
                    if(Cafes_menu.categories.length>=9 && Cafes_menu.categories[8].equals(Constants.categoryFood))
                        fragment_dostawka9.s1.sendEmptyMessage(1);
                    else
                    if(Cafes_menu.categories.length>=10 && Cafes_menu.categories[9].equals(Constants.categoryFood))
                        fragment_dostawka10.s1.sendEmptyMessage(1);
                    else
                    if(Cafes_menu.categories.length>=11 && Cafes_menu.categories[10].equals(Constants.categoryFood))
                        fragment_dostawka11.s1.sendEmptyMessage(1);
                    else
                    if(Cafes_menu.categories.length>=12 && Cafes_menu.categories[11].equals(Constants.categoryFood))
                        fragment_dostawka12.s1.sendEmptyMessage(1);
                    else
                    if(Cafes_menu.categories.length>=13 && Cafes_menu.categories[12].equals(Constants.categoryFood))
                        fragment_dostawka13.s1.sendEmptyMessage(1);
                    else
                    if(Cafes_menu.categories.length>=14 && Cafes_menu.categories[13].equals(Constants.categoryFood))
                        fragment_dostawka14.s1.sendEmptyMessage(1);
                    else
                    if(Cafes_menu.categories.length>=15 && Cafes_menu.categories[14].equals(Constants.categoryFood))
                        fragment_dostawka15.s1.sendEmptyMessage(1);
                    else
                    if(Cafes_menu.categories.length>=16 && Cafes_menu.categories[15].equals(Constants.categoryFood))
                        fragment_dostawka16.s1.sendEmptyMessage(1);
                    else
                    if(Cafes_menu.categories.length>=17 && Cafes_menu.categories[16].equals(Constants.categoryFood))
                        fragment_dostawka17.s1.sendEmptyMessage(1);
                    else
                    if(Cafes_menu.categories.length>=18 && Cafes_menu.categories[17].equals(Constants.categoryFood))
                        fragment_dostawka18.s1.sendEmptyMessage(1);
                    else
                    if(Cafes_menu.categories.length>=19 && Cafes_menu.categories[18].equals(Constants.categoryFood))
                        fragment_dostawka19.s1.sendEmptyMessage(1);
                    else
                    if(Cafes_menu.categories.length>=20 && Cafes_menu.categories[19].equals(Constants.categoryFood))
                        fragment_dostawka20.s1.sendEmptyMessage(1);

                    Log.d("conn",""+con);
                    Thread.currentThread().interrupt();
                } catch (JSONException e) {
                    e.printStackTrace();
                }}
        });
        json.start();
    }
}
