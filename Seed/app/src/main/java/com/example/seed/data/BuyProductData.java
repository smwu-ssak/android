package com.example.seed.data;

// Customized by MS

public class BuyProductData {

    private int image;     // 추후 통신 시 자료형 수정할 것
    private String name;
    private int quantity;
    //    private Date timePickup;
    private String optionSelected;
    private int totalPrice;

    public BuyProductData(int image, String name, int quantity, String optionSelected, int totalPrice) {
        this.image = image;
        this.name = name;
        this.quantity = quantity;
//        this.timePickup = timePickup;
        this.optionSelected = optionSelected;
        this.totalPrice = totalPrice;
    }

    public int getImage() { return image; }

    public String getName() { return name; }

    public int getQuantity() { return quantity; }

//    public Date getTimePickup() { return timePickup; }

    public String getOptionSelected() { return optionSelected; }

    public int getTotalPrice() { return totalPrice; }

    public void setImage(int image) { this.image = image; }

    public void setName(String name) { this.name = name; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

//    public void setTimePickup(Date timePickup) { this.timePickup = timePickup; }

    public void setOptionSelected(String optionSelected) { this.optionSelected = optionSelected; }

    public void setTotalPrice(int totalPrice) { this.totalPrice = totalPrice; }

}
