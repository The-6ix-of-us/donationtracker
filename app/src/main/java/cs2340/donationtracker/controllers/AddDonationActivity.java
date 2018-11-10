package cs2340.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
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

/**
 * Created by Emily Wilson
 *
 * Creates the add donation activity which allows the user to add a donation item
 * location's list of items
 */
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
        cancel.setOnClickListener(v ->
                startActivity(new Intent(AddDonationActivity.this,
                        LocationListActivity.class)));

        locationSpinner = findViewById(R.id.location);
        ArrayAdapter<String> locationAdapter =
                new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                        getLocationNames());
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);
        categorySpinner = findViewById(R.id.category);
        ArrayAdapter<String> categoryAdapter =
                new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                        ItemCategory.values());
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);
    }

    private void onAddItem() {
        String[] itemInfo = new String[6];

        EditText name = findViewById(R.id.name);
        Editable _name = name.getText();
        itemInfo[0] = _name.toString();

        EditText description = findViewById(R.id.description);
        Editable _description = description.getText();
        itemInfo[1] = _description.toString();

        EditText descriptionFull = findViewById(R.id.description_full);
        Editable _descriptionFull = descriptionFull.getText();
        itemInfo[2] = _descriptionFull.toString();

        Object _locationSelected = locationSpinner.getSelectedItem();
        itemInfo[3] = _locationSelected.toString();

        EditText value = findViewById(R.id.value);
        Editable _value = value.getText();
        itemInfo[4] = _value.toString();

        Object _categorySelected = categorySpinner.getSelectedItem();
        itemInfo[5] = _categorySelected.toString();

        DonationItem item = new DonationItem(itemInfo);

        Object _selected = locationSpinner.getSelectedItem();
        Location loc = locationModel.findLocationByName(_selected.toString());
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
