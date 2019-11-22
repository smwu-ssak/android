package com.example.seed.data;

import java.util.ArrayList;

// Customized by MS

public class BuyCompletedData {

    public String time;
    public ArrayList<BuyCompletedProductData> data;

    public BuyCompletedData(String time) {
        this.time = time;
        data = new ArrayList<>();
    }

    public String getTime() { return time; }

    public void setTime(String time) { this.time = time; }

}
