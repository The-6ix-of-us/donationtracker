package cs2340.donationtracker.model;

import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a collection of locations
 */

public class LocationModel {
    private static final LocationModel _instance = new LocationModel();

    /**
     * getter for the current Location Model
     * @return the instance of the Location Model
     */
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

    /**
     * Adds a location to the Location Model
     * @param item the location tp be added to the model
     */
    public void add(Location item) {
        locations.add(item);
        db.collection("location-data").add(item.toMap());
    }

    /**
     * Gets a list of all locations
     * @return A list of all locations
     */
    public List<Location> getLocations() {
        return locations;
    }

    /**
     * The current location being looked at
     * @return The current location
     */
    public Location getCurrentLocation() { return _currentLocation;}

    /**
     * Sets the current location being looked at. Needed for activities
     * @param location The new current locations
     */
    public void setCurrentLocation(Location location) { _currentLocation = location; }

    /**
     * Finds a location, given an ID
     * @param id The ID of the location to look for
     * @return The Location, if found. Or else, null.
     */
    public Location findLocationById(String id) {
        for (Location d : locations) {
            if (d.getKey().equals(id)) return d;
        }
        Log.d("MYAPP", "Warning - Failed to find id: " + id);
        return null;
    }

    /**
     * Looks for a location by name
     * @param name The name of the location to look for
     * @return The location, if found. Or else, null
     */
    public Location findLocationByName(String name) {
        for (Location d : locations) {
            if (d.getName().equals(name)) return d;
        }
        Log.d("MYAPP", "Warning - Failed to find name: " + name);
        return null;
    }

}
