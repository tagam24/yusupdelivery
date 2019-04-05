package com.tagam24.tagam.models;

import java.util.ArrayList;

/**
 * Created by AkshayeJH on 19/06/17.
 */

public class Model_Food {
    int count;
    public String id,name, price,rate_number,time,image,image1,rating,cafeID,vip,aksiya;

    public String getAksiya() {
        return aksiya;
    }

    public void setAksiya(String aksiya) {
        this.aksiya = aksiya;
    }

    public Model_Food(ArrayList<String> name) {

    }

    public Model_Food(String id, String image,String image1, String name, String price, String rating, String rate_number, String cafeID, String vip) {

        this.cafeID=cafeID;
        this.id=id;
        this.name = name;
        this.image = image;
        this.image1 = image1;
        this.price=price;
        this.rate_number=rate_number;
        this.rating = rating;
        this.count=1;
        this.vip=vip;
    }

    public String getVip() {
        return vip;
    }

    public String getCafeID() {
        return cafeID;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getRate_number() {
        return rate_number;
    }

    public String getTime() {
        return time;
    }

    public String getImage() {
        return image;
    }

    public String getImage1() {
        return image1;
    }

    public String getRating() {
        return rating;
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setRate_number(String rate_number) {
        this.rate_number = rate_number;
    }

    public void setCafeID(String cafeID) {
        this.cafeID = cafeID;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }
}

