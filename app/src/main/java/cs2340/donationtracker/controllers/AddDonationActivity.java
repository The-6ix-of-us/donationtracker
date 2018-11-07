package cs2340.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import cs2340.donationtracker.R;
import cs2340.donationtracker.model.DonationItem;
import cs2340.donationtracker.model.ItemCategory;
import cs2340.donationtracker.model.Location;
import cs2340.donationtracker.model.LocationModel;

public class AddDonationActivity extends AppCompatActivity {

    private Spinner locationSpinner;
    private Spinner categorySpinner;

    private final LocationModel locationModel = LocationModel.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);

        Button addItem = findViewById(R.id.add_item);
        addItem.setOnClickListener(v -> onAddItem());
        Button cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(v -> startActivity(new Intent(AddDonationActivity.this, LocationListActivity.class)));

        locationSpinner = findViewById(R.id.location);
        ArrayAdapter<String> locationAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getLocationNames());
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);
        categorySpinner = findViewById(R.id.category);
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ItemCategory.values());
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);
    }

    protected void onAddItem() {
        String[] itemInfo = new String[6];
        EditText name = findViewById(R.id.name);
        itemInfo[0] = name.getText().toString();
        EditText description = findViewById(R.id.description);
        itemInfo[1] = description.getText().toString();
        EditText descriptionFull = findViewById(R.id.description_full);
        itemInfo[2] = descriptionFull.getText().toString();
        itemInfo[3] = locationSpinner.getSelectedItem().toString();
        EditText value = findViewById(R.id.value);
        itemInfo[4] = value.getText().toString();
        itemInfo[5] = categorySpinner.getSelectedItem().toString();
        DonationItem item = new DonationItem(itemInfo);
        Location loc = locationModel.findLocationByName(locationSpinner.getSelectedItem().toString());
        loc.addItem(item);
        Intent intent = new Intent(this, LocationDetailActivity.class);
        intent.putExtra("location_name", loc.getName());
        intent.putExtra("location_type", loc.getType());
        intent.putExtra("location_longitude", String.valueOf(loc.getLongitude()));
        intent.putExtra("location_latitude", String.valueOf(loc.getLatitude()));
        intent.putExtra("location_address", loc.getAddress());
        intent.putExtra("location_phone", loc.getPhone());
        startActivity(intent);
    }

    private List<String> getLocationNames() {
        List<String> locationNames = new ArrayList<>();
        for (Location loc: locationModel.getLocations()) {
            locationNames.add(loc.getName());
        }
        return locationNames;
    }
}
