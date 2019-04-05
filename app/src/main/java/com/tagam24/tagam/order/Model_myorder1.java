package com.tagam24.tagam.order;

import java.util.ArrayList;

/**
 * Created by AkshayeJH on 19/06/17.
 */

public class Model_myorder1 {

    public String id,name, price, date, status,place;


    public Model_myorder1(ArrayList<String> name) {

    }

    public Model_myorder1(String id,String name, String price, String date, String status,String place) {
        this.id=id;
        this.name = name;
        this.price = price;
        this.date = date;
        this.status=status;
        this.place=place;

    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {

        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}

