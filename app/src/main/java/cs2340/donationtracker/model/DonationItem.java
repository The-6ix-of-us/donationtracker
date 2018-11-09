package cs2340.donationtracker.model;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DonationItem {
    private String key;
    private final String name;
    private final String description;
    private final String descriptionFull;
    private final Location location;
    private int value;
    private final ItemCategory category;

    public DonationItem(String[] itemInfo) {
        name = itemInfo[0];
        description = itemInfo[1];
        descriptionFull = itemInfo[2];
        location = LocationModel.getInstance().findLocationByName(itemInfo[3]);
        value = Integer.parseInt(itemInfo[4]);
        category = ItemCategory.getCategory(itemInfo[5]);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("donation-items").document();
        key = docRef.getId();
        docRef.set(toMap());
    }

    public DonationItem(DocumentSnapshot item) {
        key = item.getId();
        Map<String, Object> docData = item.getData();
        name = Objects.requireNonNull(docData).get("Name").toString();
        description = docData.get("Description").toString();
        descriptionFull = docData.get("Description Full").toString();
        LocationModel locationModel = LocationModel.getInstance();
        location = locationModel.findLocationById(docData.get("Location ID").toString());
        category = ItemCategory.getCategory(docData.get("Category").toString());
    }

    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getDescriptionFull() { return descriptionFull; }
    public Location getLocation() { return location; }
    public int getValue() { return value; }
    public ItemCategory getCategory() { return category; }


    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("Name", name);
        result.put("Description", description);
        result.put("Description Full", descriptionFull);
        result.put("Location ID", location.getKey());
        result.put("Value", value);
        result.put("Category", category.toString());
        return result;
    }
}
