package com.tagam24.tagam.gumenje;

import java.util.ArrayList;

/**
 * Created by AkshayeJH on 19/06/17.
 */

public class Model_receipe_duzimi {

    public String name;



    public Model_receipe_duzimi(ArrayList<String> name) {

    }

    public Model_receipe_duzimi(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

