package com.example.seed.data;

// Customized by MS

import android.widget.RadioButton;

public class BasketData {

    private int image;     // 추후 통신 시 자료형 수정할 것
    private String name;
    private int quantity;
    private String timePickup;
    private int salePrice;

    private RadioButton vinyl;
    private RadioButton paper;
    private RadioButton box;

    public BasketData(int image, String name, String timePickup, int quantity, int salePrice, RadioButton vinyl, RadioButton paper, RadioButton box) {
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

    public String getTimePickup() { return timePickup; }

    public int getSalePrice() { return salePrice; }

    public RadioButton isVinyl() { return vinyl; }

    public RadioButton isPaper() { return paper; }

    public RadioButton isBox() { return box; }

    public void setImage(int image) { this.image = image; }

    public void setName(String name) { this.name = name; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public void setTimePickup(String timePickup) { this.timePickup = timePickup; }

    public void setSalePrice(int salePrice) { this.salePrice = salePrice; }

    public void setVinyl(RadioButton vinyl) { this.vinyl = vinyl; }

    public void setPaper(RadioButton paper) { this.paper = paper; }

    public void setBox(RadioButton box) { this.box = box; }

}

