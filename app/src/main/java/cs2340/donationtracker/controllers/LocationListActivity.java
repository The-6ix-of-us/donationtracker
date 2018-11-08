package cs2340.donationtracker.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import cs2340.donationtracker.R;
import cs2340.donationtracker.model.FirebaseHelper;
import cs2340.donationtracker.model.Location;
import cs2340.donationtracker.model.LocationModel;
import cs2340.donationtracker.model.OnItemClickListener;

public class LocationListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LocationAdapter adapter;
    private Button viewMap;
    private Intent mapIntent;
    public ArrayList<Location> locations;

    private final FirebaseHelper firebase = FirebaseHelper.getInstance();

    private LocationModel locationModel = LocationModel.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        Button signOut = findViewById(R.id.sign_out);
        signOut.setOnClickListener(v -> startActivity(new Intent(LocationListActivity.this, HomeActivity.class)));

        viewMap = findViewById(R.id.view_map);
        mapIntent = new Intent(LocationListActivity.this, MapActivity.class);

        locations = new ArrayList<>();

        loadLocationData();

        mRecyclerView = findViewById(R.id.location_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        adapter = new LocationAdapter(locations);
        this.mRecyclerView.setAdapter(adapter);
    }

    private void loadLocationData() {
        locations = new ArrayList<>();

        firebase.getLocations(locations, this);
    }

    public void setAdapter(ArrayList<Location> locations) {
        mapIntent.putParcelableArrayListExtra("locations", locations);
        viewMap.setOnClickListener(v -> startActivity(mapIntent));
        this.mRecyclerView.setAdapter(adapter);
    }

    public class LocationAdapter extends RecyclerView.Adapter<LocationViewHolder> {

        private ArrayList<Location> locations;
        private OnItemClickListener clickListener;

        private LocationAdapter(ArrayList<Location> locations) {
            this.locations = locations;

        }

        @NonNull
        @Override
        public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_list_content, parent, false);
            return new LocationViewHolder(v);
        }

        @Override
        public void onBindViewHolder(LocationViewHolder holder, int position) {
            Location location = locations.get(position);

            holder.name.setText(location.getName());

        }

        @Override
        public int getItemCount() {
            if (locations != null) {
                return locations.size();
            } else {
                return 0;
            }
        }

    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView name;
        private Location targetLocation;

        private LocationViewHolder(View view) {
            super(view);
            this.view = view;
            name = view.findViewById(R.id.location_name);
            System.out.println(String.valueOf(name.getText()));
            String item_name = String.valueOf(name.getText());
            ArrayList<Location> targetLocations = new ArrayList<>(locationModel.getLocations());
            System.out.println(targetLocations.toString());
            for (int i = 0; i < targetLocations.size(); i++) {
                if (item_name.equals(targetLocations.get(i).getName())) {
                    targetLocation = targetLocations.get(i);
                }
            }
            itemView.setOnClickListener(view1 -> {
                System.out.println(String.valueOf(name.getText()));
                String item_name1 = String.valueOf(name.getText());
                ArrayList<Location> targetLocations1 = new ArrayList<>();
                targetLocations1.addAll(locationModel.getLocations());
                System.out.println(targetLocations1.toString());
                for (int i = 0; i < targetLocations1.size(); i++) {
                    if (item_name1.equals(targetLocations1.get(i).getName())) {
                        targetLocation = targetLocations1.get(i);
                    }
                }
                if (targetLocation != null) {
                    Context context = view1.getContext();
                    Intent intent = new Intent(context, LocationDetailActivity.class);
                    intent.putExtra("location_name", targetLocation.getName());
                    intent.putExtra("location_type", targetLocation.getType());
                    intent.putExtra("location_longitude", String.valueOf(targetLocation.getLongitude()));
                    intent.putExtra("location_latitude", String.valueOf(targetLocation.getLatitude()));
                    intent.putExtra("location_address", targetLocation.getAddress());
                    intent.putExtra("location_phone", targetLocation.getPhone());
                    context.startActivity(intent);
                } else {
                    System.out.println("targetLocation is null");
                }
            });
        }
    }
}
