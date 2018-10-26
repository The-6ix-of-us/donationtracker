package cs2340.donationtracker.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import cs2340.donationtracker.R;
import cs2340.donationtracker.model.DonationItem;
import cs2340.donationtracker.model.Location;
import cs2340.donationtracker.model.LocationModel;
import cs2340.donationtracker.model.OnItemClickListener;

public class LocationDetailActivity extends AppCompatActivity {

    private Location location;
    private ArrayList<DonationItem> items;

    private RecyclerView mRecyclerView;
    private LocationDetailActivity.ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String locationName = getIntent().getStringExtra("location_name");
        TextView name = findViewById(R.id.location_detail_name);
        name.setText("Name: " + locationName);
        location = LocationModel.getInstance().findItemByName(locationName);
        items = new ArrayList<>();
        items.addAll(location.getItems());

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
        address.setText("Address: " + locationAddress);

        String locationPhone = getIntent().getStringExtra("location_phone");
        TextView phone = findViewById(R.id.location_detail_phone);
        phone.setText("Phone: " + locationPhone);

        Button addItem = findViewById(R.id.add_item);
        addItem.setOnClickListener(v -> startActivity(new Intent(LocationDetailActivity.this, AddDonationActivity.class)));

        Button signOut = findViewById(R.id.sign_out);
        signOut.setOnClickListener(v -> startActivity(new Intent(LocationDetailActivity.this, HomeActivity.class)));

        mRecyclerView = findViewById(R.id.item_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        adapter = new LocationDetailActivity.ItemAdapter(items);
        this.mRecyclerView.setAdapter(adapter);
    }

    public class ItemAdapter extends RecyclerView.Adapter<LocationDetailActivity.ItemViewHolder> {

        private ArrayList<DonationItem> items;
        private OnItemClickListener clickListener;

        private ItemAdapter(ArrayList<DonationItem> items) {
            this.items = items;

        }

        @NonNull
        @Override
        public LocationDetailActivity.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_content, parent, false);
            return new LocationDetailActivity.ItemViewHolder(v);
        }

        @Override
        public void onBindViewHolder(LocationDetailActivity.ItemViewHolder holder, int position) {
            DonationItem item = items.get(position);

            holder.name.setText(item.getName());

        }

        @Override
        public int getItemCount() {
            if (items != null) {
                return items.size();
            } else {
                return 0;
            }
        }

    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView name;
        private DonationItem targetItem;

        private ItemViewHolder(View view) {
            super(view);
            this.view = view;
            name = view.findViewById(R.id.item_name);
            System.out.println(String.valueOf(name.getText()));
            String item_name = String.valueOf(name.getText());
            ArrayList<DonationItem> targetItems = new ArrayList<>();
            if (location != null) {
                targetItems.addAll(location.getItems());
            }
            System.out.println(targetItems.toString());
            for (int i = 0; i < targetItems.size(); i++) {
                if (item_name.equals(targetItems.get(i).getName())) {
                    targetItem = targetItems.get(i);
                }
            }
            itemView.setOnClickListener(view1 -> {
                System.out.println(String.valueOf(name.getText()));
                String item_name1 = String.valueOf(name.getText());
                ArrayList<DonationItem> targetItems1 = new ArrayList<>();
                if (location != null) {
                    targetItems1.addAll(location.getItems());
                }
                System.out.println(targetItems1.toString());
                for (int i = 0; i < targetItems1.size(); i++) {
                    if (item_name1.equals(targetItems1.get(i).getName())) {
                        targetItem = targetItems1.get(i);
                    }
                }
                if (targetItem != null) {
                    Context context = view1.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);
                    intent.putExtra("item_name", targetItem.getName());
                    intent.putExtra("item_description", targetItem.getDescription());
                    intent.putExtra("item_description_full", targetItem.getDescriptionFull());
                    intent.putExtra("item_location", targetItem.getLocation().getName());
                    intent.putExtra("item_category", targetItem.getCategory().toString());
                    intent.putExtra("item_value", targetItem.getValue());
                    context.startActivity(intent);
                } else {
                    System.out.println("targetItem is null");
                }
            });
        }
    }

}
