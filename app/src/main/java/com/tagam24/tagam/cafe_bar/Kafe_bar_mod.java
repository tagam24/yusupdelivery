package com.tagam24.tagam.cafe_bar;

/**
 * Created by AkshayeJH on 19/06/17.
 */

public class Kafe_bar_mod{

 String id,name,image,image1,image2,image3,content,watch,
        n_people,s_people,rating,address,work_time,work_data,karta_image,number;

    public Kafe_bar_mod(String id, String name, String image,String image1,
                        String image2, String image3, String content, String watch,
                        String n_people, String s_people, String rating, String address,
                        String work_time, String work_data,String karta_image,String number) {
        this.number=number;
        this.id = id;
        this.image=image;
        this.name = name;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.content = content;
        this.watch = watch;
        this.n_people = n_people;
        this.s_people = s_people;
        this.rating = rating;
        this.address = address;
        this.work_time = work_time;
        this.work_data = work_data;
        this.karta_image=karta_image;
    }

    public String getNumber() {
        return number;
    }

    public String getImage() {
        return image;
    }

    public String getKarta_image() {
        return karta_image;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage1() {
        return image1;
    }

    public String getImage2() {
        return image2;
    }

    public String getImage3() {
        return image3;
    }

    public String getContent() {
        return content;
    }

    public String getWatch() {
        return watch;
    }

    public String getN_people() {
        return n_people;
    }

    public String getS_people() {
        return s_people;
    }

    public String getRating() {
        return rating;
    }

    public String getAddress() {
        return address;
    }

    public String getWork_time() {
        return work_time;
    }

    public String getWork_data() {
        return work_data;
    }
}

