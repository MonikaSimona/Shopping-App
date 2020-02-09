package com.example.shopping_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentActivity;

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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.List;


public class CheckOut extends FragmentActivity implements AdapterView.OnItemSelectedListener, OnMapReadyCallback {
    EditText name;
    EditText surname;
    EditText cardNumber;
    ImageView cardImage;
    TextView buyText;
    TextView productNameCh;
    TextView priceCh;
    ImageView location;
    TextView address;
    String placeName;
    String addr;
    GoogleMap googleMap;
    LatLng latLng;
    private List<Place.Field> fieldList;

    String apiKey = "AIzaSyCE0OYN2AKSrawHk269AgOhwqmKdyKLeOM";
    final int PLACE_PICKER_REQUEST = 1;

    private final String CHANNEL_ID = "personal_notifications";
    private final int NOTIFICATION_ID = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFrag);
        mapFragment.getMapAsync( this);

        productNameCh = findViewById(R.id.productNameCh);
        priceCh = findViewById(R.id.priceCh);

        location = findViewById(R.id.locaiton);
        address = findViewById(R.id.address);

        Places.initialize(getApplicationContext(),apiKey);
        PlacesClient placesClient = Places.createClient(this);

        fieldList = Arrays.asList(Place.Field.ID,Place.Field.NAME,Place.Field.LAT_LNG);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN,fieldList).build(getApplicationContext());
                startActivityForResult(intent,PLACE_PICKER_REQUEST);

            }
        });

        String name = getIntent().getStringExtra("name");
        String price = getIntent().getStringExtra("price");

        productNameCh.setText(name);
        priceCh.setText(price);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.cards,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        buyText = findViewById(R.id.buytext);
        buyText.setText("Buy "+name.toUpperCase()+" for "+price.substring(1) +" dollars ");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case PLACE_PICKER_REQUEST:
                Place place = Autocomplete.getPlaceFromIntent(data);
                placeName = place.getName();
                addr = place.getName();
                address.setText(addr);
                latLng = place.getLatLng();
                googleMap.addMarker(new MarkerOptions().position(latLng).title("Mareker in"+placeName));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng,15);
                googleMap.animateCamera(update);

                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.home){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.guide){
            Intent intentHome = new Intent(this,HelpGuide.class);
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
            productNameCh.setText("");
            priceCh.setText("");
            address.setText("");

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
        if( text == "MasterCard"){

            cardImage.setImageResource(R.drawable.visa_logo);

        }else if(text == "Visa"){

            cardImage.setImageResource(R.drawable.mclogo);

        }else if(text == "American Express"){

            cardImage.setImageResource(R.drawable.aelogo);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        cardImage.setImageResource(R.drawable.credit_card);

    }

    @Override
    public void onMapReady(GoogleMap Map) {
        googleMap=Map;

        LatLng latLng1 = new LatLng(41.99646,21.43141);
        googleMap.addMarker(new MarkerOptions().position(latLng1).title("Skopje"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng1));

    }

    public void backtoshop(View view) {
        Intent backIntent = new Intent(CheckOut.this,MainActivity.class);
        startActivity(backIntent);
    }
}
