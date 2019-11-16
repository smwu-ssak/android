package com.example.seed.data;

// Customized by MS

public class BuyCompletedData {

    private String name;
    private int quantity;
    private String timePickup;
    private int totalPrice;

    public BuyCompletedData(String name, int quantity, String timePickup, int totalPrice) {
        this.name = name;
        this.quantity = quantity;
        this.timePickup = timePickup;
        this.totalPrice = totalPrice;
    }

    public String getName() { return name; }

    public int getQuantity() { return quantity; }

    public String getTimePickup() { return timePickup; }

    public int getTotalPrice() { return totalPrice; }

    public void setName(String name) { this.name = name; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public void setTimePickup(String timePickup) { this.timePickup = timePickup; }

    public void setTotalPrice(int totalPrice) { this.totalPrice = totalPrice; }

}
