package com.example.shopping_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {

    ArrayList<CartProduct> cartProducts;
    RecyclerView recyclerView;
    CartAdapter  myAdapter;
    RecyclerView.LayoutManager layoutManager;
    View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        String name  = getIntent().getStringExtra("name");
        String price = getIntent().getStringExtra("price");
        cartProducts = new ArrayList<>();
        cartProducts.add(new CartProduct(name,price));



        recyclerView = findViewById(R.id.rel);
        recyclerView.setHasFixedSize(true);
        layoutManager =  new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        myAdapter = new CartAdapter(this,cartProducts);
        recyclerView.setAdapter(myAdapter);
        myAdapter.setOnItemDeleteListener(new CartAdapter.onItemDelete() {
            @Override
            public void onItemDelete(int index) {

                cartProducts.remove(index);
                myAdapter.notifyItemRemoved(index);

            }
        });
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
        else if(item.getItemId()== R.id.guide){
            Intent intent = new Intent(this,HelpGuide.class);
            startActivity(intent);
        }
        else{
            return super.onOptionsItemSelected(item);
        }
        return true;

    }

    public void goToCheckOut(View view) {
        Intent checkOutIntent = new Intent(this,CheckOut.class);
        int num = cartProducts.size();
        String number = String.valueOf(num);
        checkOutIntent.putExtra("number", number);
        startActivity(checkOutIntent);
    }


}
