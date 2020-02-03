package com.example.shopping_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CheckOut extends AppCompatActivity {
    EditText name;
    EditText surname;
    EditText cardNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.cart){
            Intent intent = new Intent(this, Cart.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.home){
            Intent intentHome = new Intent(this,MainActivity.class);
            startActivity(intentHome);
        }
        else{
            return super.onOptionsItemSelected(item);
        }
        return true;

    }

    public void buyItems(View view) {
        name = (EditText) findViewById(R.id.nameEnter);
        surname = (EditText) findViewById(R.id.surnameEnter);
        cardNumber = (EditText) findViewById(R.id.cardNumberEnter);

        if(!(name.getText().toString().equals(" ")) || !(surname.getText().toString().equals(" ")) || !(cardNumber.getText().toString().equals(" "))){

            Toast.makeText(this,"All fields are required",Toast.LENGTH_SHORT).show();

        }else{
            name.setText("");
            surname.setText("");
            cardNumber.setText("");

            Toast.makeText(this,"Your order has been proceeded!",Toast.LENGTH_SHORT).show();

        }


    }
}
