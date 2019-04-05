package com.tagam24.tagam.models;

/**
 * Created by AkshayeJH on 19/06/17.
 */

public class Model_Cafe {

    public String id, image, name, address, work_start, logo, dostawka_price, n_people;
    public String rating;
    String category;
    String aksiya;

    public String getAksiya() {
        return aksiya;
    }

    public void setAksiya(String aksiya) {
        this.aksiya = aksiya;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Model_Cafe() {
    }

    public Model_Cafe(String id, String image, String name, String address, String rating, String n_people, String work_start, String logo, String dostawka_price, String category) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.address = address;
        this.rating = rating;
        this.work_start = work_start;
        this.logo = logo;
        this.n_people = n_people;
        this.dostawka_price = dostawka_price;
        this.category = category;


    }

    public String getDostawka_price() {
        return dostawka_price;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

