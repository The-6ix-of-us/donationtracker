package cs2340.donationtracker.model;

import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nadun Ranawaka
 *
 * A class representing a collection of locations
 */
public final class LocationModel {
    private static final LocationModel _instance = new LocationModel();


    /**
     * Gets the instance of the location model
     * @return the location model collection instance
     */
    public static LocationModel getInstance() { return _instance; }

    private final List<Location> locations;
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

    /**
     * Adds a location to the location list and firebase database
     * @param item    location to be added
     */
    public void add(Location item) {
        locations.add(item);
        db.collection("location-data").add(item.toMap());
    }

    /**
     * Gets the list of locations
     * @return list of locations
     */
    public List<Location> getLocations() {
        return locations;
    }

    /**
     * Gets the current location
     * @return current location
     */
    public Location getCurrentLocation() { return _currentLocation;}

    /**
     * Sets the current location to chosen location parameter
     * @param location    location to be set as current location
     */
    public void setCurrentLocation(Location location) { _currentLocation = location; }

    Location findLocationById(String id) {
        for (Location d : locations) {
            String _key = d.getKey();
            if (_key.equals(id)) { return d; }
        }
        Log.d("MYAPP", "Warning - Failed to find id: " + id);
        return null;
    }

    /**
     * Finds location using the name of the location
     * @param name    location's name
     */
    public Location findLocationByName(String name) {
        for (Location d : locations) {
            if (d.getName().equals(name)) { return d; }
        }
        Log.d("MYAPP", "Warning - Failed to find name: " + name);
        return null;
    }

}
