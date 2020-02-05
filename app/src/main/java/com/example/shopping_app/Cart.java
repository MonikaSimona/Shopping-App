package com.example.shopping_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        String name  = getIntent().getStringExtra("name");
        String price = getIntent().getStringExtra("price");
        ArrayList<CartProduct> cartProducts = new ArrayList<CartProduct>();
        cartProducts.add(new CartProduct(name,price));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        menu.getItem(1).setIcon(R.drawable.cart_grey).setEnabled(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        if (item.getItemId() == R.id.home){
            Intent intentHome = new Intent(this,MainActivity.class);
            startActivity(intentHome);
        }
        else{
            return super.onOptionsItemSelected(item);
        }
        return true;

    }

    public void goToCheckOut(View view) {
        Intent checkOutIntent = new Intent(this,CheckOut.class);
        startActivity(checkOutIntent);
    }
}
