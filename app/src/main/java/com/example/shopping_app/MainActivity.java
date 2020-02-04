package com.example.shopping_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ProductAdapter.ProductClicked{

    Button toCart;
    TextView productNameDet;
    TextView priceDet;
    TextView description;
    ImageView productImageDet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toCart = (Button) findViewById(R.id.toCart);
        productNameDet = findViewById(R.id.productNameDet);
        priceDet = findViewById(R.id.priceDet);
        description = findViewById(R.id.desc);

        toCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        if(findViewById(R.id.layout_portrait) != null){

            FragmentManager fragmentManager = this.getSupportFragmentManager();
            fragmentManager.beginTransaction()
                            .hide(fragmentManager.findFragmentById(R.id.detailsFrag))
                            .show(fragmentManager.findFragmentById(R.id.productsFrag))
                            .commit();
        }

        if(findViewById(R.id.layout_land) != null){

            FragmentManager fragmentManager = this.getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .show(fragmentManager.findFragmentById(R.id.detailsFrag))
                    .show(fragmentManager.findFragmentById(R.id.productsFrag))
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        menu.getItem(0).setIcon(R.drawable.home_gray).setEnabled(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.cart){
            Intent  intent = new Intent(this, Cart.class);
            startActivity(intent);
        }

        else{
            return super.onOptionsItemSelected(item);
        }
        return true;

    }

    @Override
    public void onProductClicked(int index) {

        productNameDet.setText(ApplicationClass.products.get(index).getProductName());
        priceDet.setText(ApplicationClass.products.get(index).getPrice());
        description.setText(ApplicationClass.products.get(index).getDescription());

        if(findViewById(R.id.layout_portrait) != null){
            FragmentManager fragmentManager = this.getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .show(fragmentManager.findFragmentById(R.id.detailsFrag))
                    .hide(fragmentManager.findFragmentById(R.id.productsFrag))
                    .addToBackStack(null)
                    .commit();

        }

    }
}
