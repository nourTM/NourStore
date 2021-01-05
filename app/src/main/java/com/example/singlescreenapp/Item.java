package com.example.singlescreenapp;

public class Item {
    private String name;
    private int image;
    private float price;

    public Item(String name, int image,float price) {
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
