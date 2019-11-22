package com.example.seed.data;

// Customized by MS

import java.util.ArrayList;

public class BuyCompletedProductData {

    public String name;
    public int totalPrice;
    public String timePickup;

    public BuyCompletedProductData(String name, int totalPrice, String timePickup) {
        this.name = name;
        this.totalPrice = totalPrice;
        this.timePickup = timePickup;
    }

    public String getName() { return name; }

    public int getTotalPrice() { return totalPrice; }

    public String getTimePickup() { return timePickup; }

    public void setName(String name) { this.name = name; }

    public void setTotalPrice(int totalPrice) { this.totalPrice = totalPrice; }

    public void setTimePickup(String timePickup) { this.timePickup = timePickup; }

}
