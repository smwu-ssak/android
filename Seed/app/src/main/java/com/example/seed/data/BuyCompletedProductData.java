package com.example.seed.data;

// Customized by MS

public class BuyCompletedProductData {

    public int sum;
    public String time;
    public String name;

    public BuyCompletedProductData(int sum, String time, String name) {
        this.sum = sum;
        this.time = time;
        this.name = name;
    }

    public String getName() { return name; }

    public int getSum() { return sum; }

    public String getTime() { return time; }

    public void setName(String name) { this.name = name; }

    public void setSum(int totalPrice) { this.sum = sum; }

    public void setTime(String time) { this.time = time; }

}
