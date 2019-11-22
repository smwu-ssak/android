package com.example.seed.data;

import java.util.ArrayList;

// Customized by MS

public class BuyCompletedData {

    public String timePickup;
    public ArrayList<BuyCompletedProductData> data;

    public BuyCompletedData(String timePickup) {
        this.timePickup = timePickup;
        data = new ArrayList<>();
    }

    public String getTimePickup() { return timePickup; }

    public void setTimePickup(String timePickup) { this.timePickup = timePickup; }

}
