package cs2340.donationtracker.model;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cs2340.donationtracker.controllers.LocationListActivity;

public class FirebaseHelper {
    private static final FirebaseHelper _instance = new FirebaseHelper();
    public static FirebaseHelper getInstance() { return _instance; }

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void getDonationItems(List<DonationItem> itemsList, Map<String, DonationItem> itemsMap) {
        db.collection("donation-item").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot doc : task.getResult()) {
                    DonationItem item = new DonationItem(doc);
                    itemsMap.put(doc.getId(), item);
                    itemsList.add(item);
                }
            }
        });
    }

    public void getDonationItem(String id, DonationItemModel itemModel, List<DonationItem> items) {
        db.collection("donation-item").document(id).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DonationItem item = new DonationItem(task.getResult());
                itemModel.addItem(item);
                items.add(item);
            }
        });
    }

    public void addDonationItem(DonationItem item) {
        DocumentReference docRef = db.collection("donation-item").document();
        item.setKey(docRef.getId());
        docRef.set(item.toMap());
    }

    public void addDonationItem(DonationItem item, List<String> itemIDs) {
        DocumentReference docRef = db.collection("donation-item").document();
        item.setKey(docRef.getId());
        docRef.set(item.toMap());
        itemIDs.add(docRef.getId());
    }

    public void addDonationItem(DonationItem item, Location loc) {
        addDonationItem(item, loc.getItemIDs());
        db.collection("location-data").document(loc.getKey()).set(loc.toMap());
    }

    public void getLocations(List<Location> locations) {
        db.collection("location-data").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot doc : task.getResult()) {
                    Location loc = new Location(doc);
                    locations.add(loc);
                }
            }
        });
    }

    public void getLocations(ArrayList<Location> locations, LocationListActivity activity) {
        db.collection("location-data").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot doc : task.getResult()) {
                    Location loc = new Location(doc);
                    locations.add(loc);
                }
            }
            activity.setAdapter(locations);
        });
    }

    public void addLocation(Location loc) {
        db.collection("location-data").add(loc.toMap());
    }
}
