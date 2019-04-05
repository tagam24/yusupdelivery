package com.tagam24.tagam.network;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.tagam24.tagam.Constants.Constants;
import com.google.gson.Gson;

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
 * Created by User on 22.01.2019.
 */

public class send_orders {

    public  static int con=0;
    static String l;
    public static   Thread get_data=new Thread();
    public static void  get_Data(){
        get_data=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String st=new Gson().toJson(Constants.cart_list);
                    URL url=new URL("http://"+ Api.url+"tagam24/send_orders.php");
                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setRequestMethod("POST");
                    conn.connect();
                    OutputStream outputStream = conn.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("orders", "UTF-8") + "=" + URLEncoder.encode(""+st, "UTF-8")+"&"+
                    URLEncoder.encode("number", "UTF-8") + "=" + URLEncoder.encode(""+Constants.number, "UTF-8")+"&"+
                            URLEncoder.encode("tt_price", "UTF-8") + "=" + URLEncoder.encode(""+Constants.tt_price, "UTF-8")+"&"+
                            URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(""+ Constants.address, "UTF-8")+"&"+
                            URLEncoder.encode("cafeName", "UTF-8") + "=" + URLEncoder.encode(""+Constants.cafeName, "UTF-8")
                            +"&"+
                            URLEncoder.encode("cafeID", "UTF-8") + "=" + URLEncoder.encode(""+Constants.cafeID, "UTF-8")
                    +"&"+
                            URLEncoder.encode("token", "UTF-8") + "=" + URLEncoder.encode(FirebaseInstanceId.getInstance().getToken(), "UTF-8")
                            +"&"+
                            URLEncoder.encode("size", "UTF-8") + "=" + URLEncoder.encode(""+Constants.cart_list.size(), "UTF-8");
                    ;
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream in = conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    StringBuilder sb = new StringBuilder();
                    String row = "";
                    while ((row = br.readLine()) != null) {
                        sb.append(row + "\n");}
                    Log.d("orders",sb.toString());
                    br.close();
                    in.close();
                    conn.disconnect();
                    Constants.cart_list.clear();
                    Constants.ids.clear();
                    Constants.tt_price=0;
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
        if(con==0) get_data.start();}
    
    public static void GetJson(final String s){

    }
}
