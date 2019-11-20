package com.example.seed.data;

// Customized by SY

public class MainProductData {

    private String proName;
    private int quantity;
    private String image;
    private int originPrice;
    private int salePrice;
    private int idProduct;
    private String storName;

    public MainProductData(String proName, int quantity, String image, int originPrice, int salePrice, String storName) {
        this.proName = proName;
        this.quantity = quantity;
        this.image = image;
        this.originPrice = originPrice;
        this.salePrice = salePrice;
        this.storName = storName;
    }

    public String getImage() { return image; }

    public String getName() { return proName; }

    public int getQuantity() { return quantity; }

    public int getOriginPrice() { return originPrice; }

    public int getSalePrice() { return salePrice; }

    public int getIdProduct() { return idProduct; }

    public String getPlace() { return storName; }

    public void setImage(String image) { this.image = image; }

    public void setName(String proName) { this.proName = proName; }

    public void setOriginPrice(int originPrice) { this.originPrice = originPrice; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public void setSalePrice(int salePrice) { this.salePrice = salePrice; }

    public void setIdProduct(int idProduct)  { this.idProduct = idProduct; }

    public void setPlace(String storName) { this.storName = storName; }

}
