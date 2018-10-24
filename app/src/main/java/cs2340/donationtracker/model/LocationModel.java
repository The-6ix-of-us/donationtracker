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

    private List<Location> items;

    private Location _currentLocation;

    private LocationModel() {
        items = new ArrayList<>();
    }

    public void add(Location item) {
        items.add(item);
    }

    public List<Location> getItems() {
        return items;
    }

    public Location getCurrentLocation() { return _currentLocation;}

    public void setCurrentLocation(Location location) { _currentLocation = location; }

    public Location findItemById(int id) {
        for (Location d : items) {
            if (d.getKey() == id) return d;
        }
        Log.d("MYAPP", "Warning - Failed to find id: " + id);
        return null;
    }

    public Location findItemByName(String name) {
        for (Location d : items) {
            if (d.getName().equals(name)) return d;
        }
        Log.d("MYAPP", "Warning - Failed to find name: " + name);
        return null;
    }

}
