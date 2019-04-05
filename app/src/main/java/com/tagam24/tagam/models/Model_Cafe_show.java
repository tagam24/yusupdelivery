package com.tagam24.tagam.models;

/**
 * Created by AkshayeJH on 19/06/17.
 */

public class Model_Cafe_show {



    public String id, image, name, address,watch, work_start,karta_image,content,min_order,work_days, delivery_price,number, n_people,s_rating;
    public String rating;
    String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Model_Cafe_show() {
    }

    public Model_Cafe_show(String id, String image, String name, String address, String rating, String watch, String n_people, String work_start, String delivery_price,String content,String min_order,String karta_image, String work_days,String s_rating,String number,String category) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.address = address;
        this.rating = rating;
        this.work_start = work_start;
        this.content=content;
        this.delivery_price=delivery_price;
        this.karta_image=karta_image;
        this.number=number;
        this.min_order=min_order;
        this.watch=watch;
        this.work_days=work_days;
        this.s_rating=s_rating;
        this.n_people = n_people;
        this.category = category;


    }

    public String getContent() {
        return content;
    }

    public String getWork_days() {
        return work_days;
    }

    public String getS_rating() {
        return s_rating;
    }

    public String getWatch() {
        return watch;
    }

    public String getNumber() {
        return number;
    }

    public String getKarta_image() {
        return karta_image;
    }

    public String getDelivery_price() {
        return delivery_price;
    }

    public String getMin_order() {
        return min_order;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public String getRating() {
        return rating;
    }

    public String getAddress() {
        return address;
    }

    public String getWork_start() {
        return work_start;
    }

    public String getN_people() {
        return n_people;
    }
}

