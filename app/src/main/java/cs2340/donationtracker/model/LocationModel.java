package cs2340.donationtracker.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a collection of locations
 */

public class LocationModel {
    private static final LocationModel _instance = new LocationModel();
    public static LocationModel getInstance() { return _instance; }

    private List<Location> locations;

    private Location _currentLocation;

    private LocationModel() {
        locations = new ArrayList<>();
    }

    public void add(Location location) {
        locations.add(location);
    }

    public List<Location> getLocations() {
        return locations;
    }

    public Location getCurrentLocation() { return _currentLocation;}

    public void setCurrentLocation(Location location) { _currentLocation = location; }

    public Location findLocationById(int id) {
        for (Location d : locations) {
            if (d.getKey() == id) return d;
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
