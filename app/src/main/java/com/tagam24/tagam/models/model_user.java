package com.tagam24.tagam.models;

/**
 * Created by User on 28.02.2019.
 */

public class model_user {
    String name,address,phone,date;

    public model_user(String name, String address, String phone, String date) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getDate() {
        return date;
    }
}
