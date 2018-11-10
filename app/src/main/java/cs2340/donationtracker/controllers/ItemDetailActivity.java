package cs2340.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import cs2340.donationtracker.R;
import cs2340.donationtracker.model.Location;
import cs2340.donationtracker.model.LocationModel;

/**
 * Created by Emily Wilson
 *
 * Creates the item detail activity which shows the item's attributes
 */
public class ItemDetailActivity extends AppCompatActivity {

    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        Intent intent = getIntent();

        String itemName = intent.getStringExtra("item_name");
        TextView name = findViewById(R.id.item_detail_name);
        name.setText(String.format("Name: %s", itemName));

        String itemLocation = intent.getStringExtra("item_location");
        TextView type = findViewById(R.id.item_detail_location);
        type.setText(String.format("Location: %s", itemLocation));
        LocationModel locationModel = LocationModel.getInstance();
        location = locationModel.findLocationByName(itemLocation);

        String itemValue = intent.getStringExtra("item_value");
        TextView phone = findViewById(R.id.item_detail_value);
        phone.setText(String.format("Value: %s", itemValue));

        String itemDescription = intent.getStringExtra("item_description");
        TextView shortDescription = findViewById(R.id.item_detail_description);
        shortDescription.setText(String.format("Brief Description: %s", itemDescription));

        String itemCategory = intent.getStringExtra("item_category");
        TextView category = findViewById(R.id.item_detail_category);
        category.setText(String.format("Category: %s", itemCategory));

        String itemDescriptionFull = intent.getStringExtra("item_description_full");
        TextView longDescription = findViewById(R.id.item_detail_description_full);
        longDescription.setText(String.format("Full Description: %s", itemDescriptionFull));

        Button back = findViewById(R.id.back_to_location);
        back.setOnClickListener(v -> onGoBack());
    }

    private void onGoBack() {
        Intent intent = new Intent(this, LocationDetailActivity.class);
        intent.putExtra("location_name", location.getName());
        intent.putExtra("location_type", location.getType());
        intent.putExtra("location_longitude", String.valueOf(location.getLongitude()));
        intent.putExtra("location_latitude", String.valueOf(location.getLatitude()));
        intent.putExtra("location_address", location.getAddress());
        intent.putExtra("location_phone", location.getPhone());
        startActivity(intent);
    }
}
