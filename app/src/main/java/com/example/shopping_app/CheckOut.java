package com.example.shopping_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
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
    TextView buyText;
    public String number;
    private final String CHANNEL_ID = "personal_notifications";
    private final int NOTIFICATION_ID = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.cards,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        buyText = findViewById(R.id.buytext);
        number = getIntent().getStringExtra("number");
        if(number.equals("1")){
            buyText.setText("Buy one item");
        }else{
            buyText.setText("Buy "+number+" items");
        }
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




        if(nameS.equals("") || surnameS.equals("") || cardNumberS.equals("") ){

            Toast.makeText(this,"All fields are required",Toast.LENGTH_SHORT).show();

        }else{
            name.setText("");
            surname.setText("");
            cardNumber.setText("");

            Toast.makeText(this,"Your order has been proceeded!",Toast.LENGTH_SHORT).show();

            createNotificationChannel();
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID);
            builder.setSmallIcon(R.drawable.ic_sms_black_24dp);
            builder.setContentTitle("ShopUp");
            builder.setContentText("Your order was successful!");
            builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
            notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());


        }


    }
    private void createNotificationChannel(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            CharSequence name = "Personal Notifications";
            String description = "Include all the notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,name,importance);

            notificationChannel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        cardImage = findViewById(R.id.cardImage);
        String text = parent.getItemAtPosition(position).toString();
        if( text != "MasterCard"){
            Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
//            cardImage.setImageResource(R.drawable.visa_logo);

        }else if(text != "Visa"){
            Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
//            cardImage.setImageResource(R.drawable.mclogo);

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
