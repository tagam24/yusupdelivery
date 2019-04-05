package com.tagam24.tagam.order;

import java.util.ArrayList;

/**
 * Created by AkshayeJH on 19/06/17.
 */

public class Model_myorder2 {
    public String name, price,time,count,image;


    public Model_myorder2(ArrayList<String> name) {

    }

    public Model_myorder2(String id, String name, String price, String count,String image) {


        this.name = name;
        this.image = image;
        this.price=price;

        this.count = count;

    }


    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }



    public String getTime() {
        return time;
    }

    public String getImage() {
        return image;
    }

    public String getCount() {
        return count;
    }

    public void setTime(String time) {
        this.time = time;
    }





    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }



}

