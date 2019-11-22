package com.example.seed.data;

// Customized by MS

import java.util.Date;

public class BuyProductData {

    private String image;     // 추후 통신 시 자료형 수정할 것
    private String name;
    private int quantity;
    private String timePickup;
    private int packing;
    private int salePrice;
    private int totalPrice;

    public BuyProductData(String image, String name, int quantity, String timePickup, int packing, int totalPrice) {
        this.image = image;
        this.name = name;
        this.quantity = quantity;
        this.timePickup = timePickup;
        this.packing = packing;
        this.totalPrice = totalPrice;
    }

    public String getImage() { return image; }

    public String getName() { return name; }

    public int getQuantity() { return quantity; }

    public String getTimePickup() { return timePickup; }

    public int getPacking() { return packing; }

    public int getSalePrice() { return salePrice; }

    public int getTotalPrice() { return totalPrice; }

    public void setImage(String image) { this.image = image; }

    public void setName(String name) { this.name = name; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public void setTimePickup(String timePickup) { this.timePickup = timePickup; }

    public void setPacking(int packing) { this.packing = packing; }

    public void setSalePrice(int salePrice) { this.salePrice = salePrice; }

    public void setTotalPrice(int totalPrice) { this.totalPrice = totalPrice; }

}
