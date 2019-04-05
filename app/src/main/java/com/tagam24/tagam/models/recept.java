package com.tagam24.tagam.models;

public class recept {
    String id,name,image,image1,image2,liked,n_people,s_people,
    rating,watch,content,text1,tex2,date;

    public recept(String id, String name, String image, String image1, String image2,
                  String liked, String n_people, String rating, String watch, String content,
                  String text1, String tex2,String date) {
        this.date=date;
        this.id = id;
        this.name = name;
        this.image = image;
        this.image1 = image1;
        this.image2 = image2;
        this.liked = liked;
        this.n_people = n_people;
        this.s_people = s_people;
        this.rating = rating;
        this.watch = watch;
        this.content = content;
        this.text1 = text1;
        this.tex2 = tex2;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getImage1() {
        return image1;
    }

    public String getImage2() {
        return image2;
    }

    public String getLiked() {
        return liked;
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

    public String getWatch() {
        return watch;
    }

    public String getContent() {
        return content;
    }

    public String getText1() {
        return text1;
    }

    public String getTex2() {
        return tex2;
    }

    public String getDate() {
        return date;
    }
}
