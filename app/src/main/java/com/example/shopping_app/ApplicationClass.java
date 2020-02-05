package com.example.shopping_app;

import android.app.Application;
import android.content.res.TypedArray;

import java.util.ArrayList;

public class ApplicationClass extends Application {

//    public static String [] products;
    public static ArrayList<Product> products;
    @Override
    public void onCreate() {
        super.onCreate();

//        String [] titles = getResources().getStringArray(R.array.product_titles);
//        String [] prices = getResources().getStringArray(R.array.product_prices);
//        String [] descriptions = getResources().getStringArray(R.array.product_details);
//        TypedArray images = getResources().obtainTypedArray(R.array.sports_images);
//        images.recycle();
//
//        for(int i = 0; i<titles.length; i++){
//            products.add(new Product(titles[i],images.getResourceId(i,0),prices[i],descriptions[i]));
//        }


        products = new ArrayList<Product>();
        products.add(new Product("Painting Brush 1",R.drawable.brush1,"$20","Description for this product."));
        products.add(new Product("Painting Brush 2",R.drawable.brush2,"$50","Description for this product."));
        products.add(new Product("Watercolor Paper 1",R.drawable.paper1,"$5","Description for this product."));
        products.add(new Product("Watercolor Paper 2",R.drawable.paper2,"$55","Description for this product."));


    }
}
