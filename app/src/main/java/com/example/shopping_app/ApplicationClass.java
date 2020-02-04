package com.example.shopping_app;

import android.app.Application;

import java.util.ArrayList;

public class ApplicationClass extends Application {

    public static ArrayList<Product> products;
    @Override
    public void onCreate() {
        super.onCreate();

        products = new ArrayList<Product>();
        products.add(new Product("Painting Brush","$20","Description for this product."));
        products.add(new Product("Watercolor Pencils","$50","Description for this product."));
        products.add(new Product("Colored Pencils","$15","Description for this product."));
        products.add(new Product("Watercolor Paper","$20","Description for this product."));
    }
}
