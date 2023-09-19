package org.example.entity;

public class Product {

    private Long id;

    private String name;
    private String description;
    private double price;

    private int stockAmount;

    public void setStockAmount(int stockAmount) {
        this.stockAmount = stockAmount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
