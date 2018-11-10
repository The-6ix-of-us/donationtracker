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

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import cs2340.donationtracker.R;
import cs2340.donationtracker.model.Location;
import cs2340.donationtracker.model.LocationModel;

/**
 * Created by Peter Franzek
 *
 * Creates the Location list activity which shows the list of locations
 * which can be clicked on to access their specific attributes
 */
public class LocationListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LocationAdapter adapter;
    private Button viewMap;
    private Intent mapIntent;
    private ArrayList<Location> locations;

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private final LocationModel locationModel = LocationModel.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        Button signOut = findViewById(R.id.sign_out);
        signOut.setOnClickListener(v -> startActivity(
                new Intent(LocationListActivity.this, HomeActivity.class)));

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

        CollectionReference collection = db.collection("location-data");
        Task<QuerySnapshot> _task = collection.get();
        _task.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                int i = 0;
                for (QueryDocumentSnapshot doc : task.getResult()) {
                    Location loc = new Location(doc);
                    locations.add(loc);
                }
            }
            mapIntent.putParcelableArrayListExtra("locations", locations);
            viewMap.setOnClickListener(v -> startActivity(mapIntent));
            this.mRecyclerView.setAdapter(adapter);
        });
    }

    final class LocationAdapter extends RecyclerView.Adapter<LocationViewHolder> {

        private final ArrayList<Location> locations;

        private LocationAdapter(ArrayList<Location> locations) {
            this.locations = locations;

        }

        @NonNull
        @Override
        public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(
                    R.layout.location_list_content, parent, false);
            return new LocationViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
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

    final class LocationViewHolder extends RecyclerView.ViewHolder {
        final TextView name;
        private Location targetLocation;

        private LocationViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.location_name);
            String item_name = String.valueOf(name.getText());
            List<Location> targetLocations = new ArrayList<>(locationModel.getLocations());
            for (int i = 0; i < targetLocations.size(); i++) {
                Location _location = targetLocations.get(i);
                if (item_name.equals(_location.getName())) {
                    targetLocation = targetLocations.get(i);
                }
            }
            itemView.setOnClickListener(view1 -> {
                String item_name1 = String.valueOf(name.getText());
                List<Location> targetLocations1 = new ArrayList<>(
                        locationModel.getLocations());
                for (int i = 0; i < targetLocations1.size(); i++) {
                    Location _location = targetLocations1.get(i);
                    if (item_name1.equals(_location.getName())) {
                        targetLocation = targetLocations1.get(i);
                    }
                }
                if (targetLocation != null) {
                    Context context = view1.getContext();
                    Intent intent = new Intent(context, LocationDetailActivity.class);
                    intent.putExtra("location_name", targetLocation.getName());
                    intent.putExtra("location_type", targetLocation.getType());
                    intent.putExtra("location_longitude",
                            String.valueOf(targetLocation.getLongitude()));
                    intent.putExtra("location_latitude",
                            String.valueOf(targetLocation.getLatitude()));
                    intent.putExtra("location_address", targetLocation.getAddress());
                    intent.putExtra("location_phone", targetLocation.getPhone());
                    context.startActivity(intent);
                }
            });
        }
    }
}
