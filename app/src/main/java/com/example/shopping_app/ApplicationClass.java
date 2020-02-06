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
        products.add(new Product("Painting Brush 1",R.drawable.brush1,"$20","Detail paint brushes for artists and model makers, watercolor, acrylic, oil paints, etc.\n" +
                "Soft and polished wooden handles give you more control of the brush while painting.\n" +
                "It is secure to the handle with the metal part well attached to the wood and the brush hair, so there is no wiggling.\n" +
                "Easy to clean and brushes hold their shape after cleaning.\n" +
                "Triangular grip handle to give holding precision."));
        products.add(new Product("Painting Brush 2",R.drawable.brush2,"$50","Detail paint brushes for artists and model makers, watercolor, acrylic, oil paints, etc.\n" +
                "Soft and polished wooden handles give you more control of the brush while painting.\n" +
                "It is secure to the handle with the metal part well attached to the wood and the brush hair, so there is no wiggling.\n" +
                "Easy to clean and brushes hold their shape after cleaning.\n" +
                "Triangular grip handle to give holding precision."));
        products.add(new Product("Watercolor Paper 1",R.drawable.paper1,"$5","Made from 100 percent pure cotton, 140lb/300gsm heavyweight watercolor papers, acid-free and chlorine-free, pH-neutral and archival, durable and strong enough for whether wet, dry or mixed media painting, and less likely to curl or warp"));
        products.add(new Product("Watercolor Paper 2",R.drawable.paper2,"$55","Made from 100 percent pure cotton, 140lb/300gsm heavyweight watercolor papers, acid-free and chlorine-free, pH-neutral and archival, durable and strong enough for whether wet, dry or mixed media painting, and less likely to curl or warp"));


    }
}
