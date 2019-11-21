package com.example.seed.data;

// Customized by MS

import java.util.Date;

public class BasketData {

    private int idBasket;
    private String image;
    private String name;
    private int buyNum;
    private int quantity;
    private Date timePickup;
    private int salePrice;
    private int packing;
    private int sumPrice;

    public BasketData(String image, String name, Date timePickup, int buyNum, int quantity, int salePrice, int packing, int sumPrice) {
        this.image = image;
        this.name = name;
        this.buyNum = buyNum;
        this.quantity = quantity;
        this.timePickup = timePickup;
        this.salePrice = salePrice;
        this.packing = packing;
        this.sumPrice = sumPrice;
    }

    public int getIdBasket() { return idBasket; }

    public String getImage() { return image; }

    public String getName() { return name; }

    public int getBuyNum() { return buyNum; }

    public int getQuantity() { return quantity; }

    public Date getTimePickup() { return timePickup; }

    public int getSalePrice() { return salePrice; }

    public int getPacking() { return packing; }

    public int getSumPrice() { return sumPrice; }

    public void setIdBasket(int idBasket) { this.idBasket = idBasket; }

    public void setImage(String image) { this.image = image; }

    public void setName(String name) { this.name = name; }

    public void setBuyNum(int buyNum) { this.buyNum = buyNum; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public void setTimePickup(Date timePickup) { this.timePickup = timePickup; }

    public void setSalePrice(int salePrice) { this.salePrice = salePrice; }

    public void setPacking(int packing) { this.packing = packing; }

    public void setSumPrice(int sumPrice) { this.sumPrice = sumPrice; }

}

