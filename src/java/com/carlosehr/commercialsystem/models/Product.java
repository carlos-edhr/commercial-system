/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carlosehr.commercialsystem.models;

/**
 *
 * @author Carlos Hoyos Rojas
 */
public class Product {
   private long productId;
    private Provider provider;
    private Category category;
    private String description;
    private double unitPrice;
    private int stock;

    public Product() {
    }

    public Product(long productId) {
        this.productId = productId;
    }

    public Product(long productId, Provider provider, Category category, String description, double unitPrice, int stock) {
        this.productId = productId;
        this.provider = provider;
        this.category = category;
        this.description = description;
        this.unitPrice = unitPrice;
        this.stock = stock;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

 

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    
    
 
    
}
