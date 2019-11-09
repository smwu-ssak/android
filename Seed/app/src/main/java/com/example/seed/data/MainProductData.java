package com.example.seed.data;

import android.graphics.drawable.Drawable;

// Customized by SY

public class MainProductData {

    private int image;     // 추후 통신 시 자료형 수정할 것
    private String name;
    private int quantity;
    private int originPrice;
    private int salePrice;
    private int discount;
    private String place;

    public MainProductData(int image, String name, int quantity, int originPrice, int salePrice, int discount, String place) {
        this.image = image;
        this.name = name;
        this.quantity = quantity;
        this.originPrice = originPrice;
        this.salePrice = salePrice;
        this.discount = discount;
        this.place = place;
    }

    public int getImage() { return image; }

    public String getName() { return name; }

    public int getQuantity() { return quantity; }

    public int getOriginPrice() { return originPrice; }

    public int getSalePrice() { return salePrice; }

    public int getDiscount() { return discount; }

    public String getPlace() { return place; }

    public void setImage(int image) { this.image = image; }

    public void setName(String name) { this.name = name; }

    public void setOriginPrice(int originPrice) { this.originPrice = originPrice; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public void setSalePrice(int salePrice) { this.salePrice = salePrice; }

    public void setDiscount(int discount) { this.discount = discount; }

    public void setPlace(String place) { this.place = place; }

}
