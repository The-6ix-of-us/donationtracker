package cs2340.donationtracker.controllers;

import cs2340.donationtracker.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LocationDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String locationName = getIntent().getStringExtra("location_name");
        TextView name = findViewById(R.id.location_detail_name);
        name.setText("Name: " + locationName);

        String locationType = getIntent().getStringExtra("location_type");
        TextView type = findViewById(R.id.location_detail_type);
        type.setText("Type: " + locationType);

        String locationLongitude = getIntent().getStringExtra("location_longitude");
        TextView longitude = findViewById(R.id.location_detail_longitude);
        longitude.setText("Longitude: " + locationLongitude);

        String locationLatitude = getIntent().getStringExtra("location_latitude");
        TextView latitude = findViewById(R.id.location_detail_latitude);
        latitude.setText("Latitude: " + locationLatitude);

        String locationAddress = getIntent().getStringExtra("location_address");
        TextView address = findViewById(R.id.location_detail_address);
        address.setText("Adress: " + locationAddress);

        String locationPhone = getIntent().getStringExtra("location_phone");
        TextView phone = findViewById(R.id.location_detail_phone);
        phone.setText("Phone: " + locationPhone);

        Button signOut = findViewById(R.id.sign_out);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LocationDetailActivity.this, HomeActivity.class));
            }
        });
    }

}
