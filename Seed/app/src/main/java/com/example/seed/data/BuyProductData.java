package com.example.seed.data;

import java.util.Date;

// Customized by MS

public class BuyProductData {

    private int image;     // 추후 통신 시 자료형 수정할 것
    private String name;
    private int quantity;
    private Date timePickup;
    private int salePrice;

    private boolean vinyl;
    private boolean paper;
    private boolean box;

    public BuyProductData(int image, String name, int quantity, Date timePickup, int salePrice, boolean vinyl, boolean paper, boolean box) {
        this.image = image;
        this.name = name;
        this.quantity = quantity;
        this.timePickup = timePickup;
        this.salePrice = salePrice;

        this.vinyl = vinyl;
        this.paper = paper;
        this.box = box;
    }

    public int getImage() { return image; }

    public String getName() { return name; }

    public int getQuantity() { return quantity; }

    public Date getTimePickup() { return timePickup; }

    public int getSalePrice() { return salePrice; }

    public boolean isVinyl() { return vinyl; }

    public boolean isPaper() { return paper; }

    public boolean isBox() { return box; }

    public void setImage(int image) { this.image = image; }

    public void setName(String name) { this.name = name; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public void setTimePickup(Date timePickup) { this.timePickup = timePickup; }

    public void setSalePrice(int salePrice) { this.salePrice = salePrice; }

    public void setVinyl(boolean vinyl) { this.vinyl = vinyl; }

    public void setPaper(boolean paper) { this.paper = paper; }

    public void setBox(boolean box) { this.box = box; }

}
