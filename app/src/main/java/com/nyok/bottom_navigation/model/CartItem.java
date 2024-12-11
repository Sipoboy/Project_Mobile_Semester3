package com.nyok.bottom_navigation.model;


public class CartItem {
    private String productName;
    private int productImageId;  // ID drawable, bukan URL
    private int quantity;
    private double totalPrice;

    // Konstruktor
    public CartItem(String productName, int productImageId, int quantity, double totalPrice) {
        this.productName = productName;
        this.productImageId = productImageId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    // Getter dan Setter
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductImageId() {
        return productImageId;
    }

    public void setProductImageId(int productImageId) {
        this.productImageId = productImageId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
