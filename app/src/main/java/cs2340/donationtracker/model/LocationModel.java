package cs2340.donationtracker.model;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import cs2340.donationtracker.controllers.LocationListActivity;

/**
 * A class representing a collection of locations
 */

public class LocationModel {
    private static final LocationModel _instance = new LocationModel();
    public static LocationModel getInstance() { return _instance; }

    private List<Location> locations;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private Location _currentLocation;

    private LocationModel() {
        locations = new ArrayList<>();
        setLocations();
    }

    private void setLocations() {
        db.collection("location-data").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot doc : task.getResult()) {
                    Location loc = new Location(doc);
                    locations.add(loc);
                }
            }
        });
    }

    public void add(Location item) {
        locations.add(item);
        db.collection("location-data").add(item.toMap());
    }

    public List<Location> getLocations() {
        return locations;
    }

    public Location getCurrentLocation() { return _currentLocation;}

    public void setCurrentLocation(Location location) { _currentLocation = location; }

    public Location findLocationById(String id) {
        for (Location d : locations) {
            if (d.getKey().equals(id)) return d;
        }
        Log.d("MYAPP", "Warning - Failed to find id: " + id);
        return null;
    }

    public Location findLocationByName(String name) {
        for (Location d : locations) {
            if (d.getName().equals(name)) return d;
        }
        Log.d("MYAPP", "Warning - Failed to find name: " + name);
        return null;
    }

}
