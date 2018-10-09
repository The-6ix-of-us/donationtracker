package cs2340.donationtracker.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a collection of locations
 */

public class LocationModel {
    public static final LocationModel INSTANCE = new LocationModel();

    private List<Location> items;

    private LocationModel() {
        items = new ArrayList<>();
    }

    public void add(Location item) {
        items.add(item);
    }

    public List<Location> getItems() {
        return items;
    }

    public Location findItemById(int id) {
        for (Location d : items) {
            if (d.getKey() == id) return d;
        }
        Log.d("MYAPP", "Warning - Failed to find id: " + id);
        return null;
    }
}
