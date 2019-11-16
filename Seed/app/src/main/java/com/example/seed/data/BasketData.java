package com.example.seed.data;

// Customized by MS

import android.widget.RadioButton;

public class BasketData {

    private int image;     // 추후 통신 시 자료형 수정할 것
    private String name;
    private int buyNum;
    private int quantity;
    private String timePickup;
    private int salePrice;
    private int check;
    private int sumPrice;

    public BasketData(int image, String name, String timePickup, int buyNum, int quantity, int salePrice, int check, int sumPrice) {
        this.image = image;
        this.name = name;
        this.buyNum = buyNum;
        this.quantity = quantity;
        this.timePickup = timePickup;
        this.salePrice = salePrice;
        this.check = check;
        this.sumPrice = sumPrice;
    }

    public int getImage() { return image; }

    public String getName() { return name; }

    public int getBuyNum() { return buyNum; }

    public int getQuantity() { return quantity; }

    public String getTimePickup() { return timePickup; }

    public int getSalePrice() { return salePrice; }

    public int getCheck() { return check; }

    public int getSumPrice() { return sumPrice; }

    public void setImage(int image) { this.image = image; }

    public void setName(String name) { this.name = name; }

    public void setBuyNum(int buyNum) { this.buyNum = buyNum; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public void setTimePickup(String timePickup) { this.timePickup = timePickup; }

    public void setSalePrice(int salePrice) { this.salePrice = salePrice; }

    public void setCheck(int check) { this.check = check; }

    public void setSumPrice(int sumPrice) { this.sumPrice = sumPrice; }

}

