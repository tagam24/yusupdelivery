package com.tagam24.tagam.models;

/**
 * Created by User on 25.02.2019.
 */

public class model_banner {
    String image,cafe,vip,content,number,price,name;

    public model_banner(String image,String name,String content, String number, String price) {
        this.name=name;
        this.image = image;
        this.cafe = cafe;
        this.vip = vip;
        this.content = content;
        this.number = number;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getCafe() {
        return cafe;
    }

    public String getVip() {
        return vip;
    }

    public String getContent() {
        return content;
    }

    public String getNumber() {
        return number;
    }

    public String getPrice() {
        return price;
    }
}
