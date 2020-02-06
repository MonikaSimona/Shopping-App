package com.example.shopping_app;

import android.content.Context;
import android.content.Intent;

public class Product {
    String productName;
    int image;
    String price;
    String description;

    public Product(String productName, int image, String price,String description) {
        this.productName = productName;
        this.image = image;
        this.price = price;
        this.description = description;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    static Intent starter(Context context, String productName, String price) {
        Intent cartIntent = new Intent(context, Cart.class);
        cartIntent.putExtra("name", productName);
        cartIntent.putExtra("price", price);
        return cartIntent;
    }

}
