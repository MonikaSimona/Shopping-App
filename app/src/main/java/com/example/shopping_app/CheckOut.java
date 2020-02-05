package com.example.shopping_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CheckOut extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    EditText name;
    EditText surname;
    EditText cardNumber;
    ImageView cardImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.cards,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
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

        String nameS = name.getText().toString();
        String surnameS = surname.getText().toString();
        String cardNumberS = cardNumber.getText().toString();

//        TextView text = findViewById(R.id.text);
//        text.setText(nameS);

        if(nameS.equals("") || surnameS.equals("") || cardNumberS.equals("") ){

            Toast.makeText(this,"All fields are required",Toast.LENGTH_SHORT).show();

        }else{
            name.setText("");
            surname.setText("");
            cardNumber.setText("");

            Toast.makeText(this,"Your order has been proceeded!",Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        cardImage = findViewById(R.id.cardImage);
        String text = parent.getItemAtPosition(position).toString();
        if( text == "MasterCard"){
            Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
            cardImage.setImageResource(R.drawable.mastercard_logo);

        }else if(text != "Visa"){
            Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
            cardImage.setImageResource(R.drawable.visa_logo);

        }else{
            Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
//            cardImage.setImageResource(R.drawable.aelogo);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        cardImage.setImageResource(R.drawable.credit_card);

    }
}
