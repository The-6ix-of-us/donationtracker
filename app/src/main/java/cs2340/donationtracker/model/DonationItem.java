package cs2340.donationtracker.model;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Emily Wilson
 *
 * A class representing a donation item
 */
public class DonationItem {
    private String key;
    private final String name;
    private final String description;
    private final String descriptionFull;
    private final Location location;
    private int value;
    private final ItemCategory category;

    /**
     * Constructor for a donation item that takes in a string array of an item's attributes
     * @param itemInfo      string array of donation item's attributes
     */
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

    /**
     * Constructor for a donation item that takes in a firebase document snapshot
     * @param item      firebase document snapshot
     */
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

    String getKey() { return key; }
    void setKey(String key) { this.key = key; }

    /**
     * Gets the name of the item
     * @return item's name
     */
    public String getName() { return name; }

    /**
     * Gets the short description
     * @return item's short description
     */
    public String getDescription() { return description; }

    /**
     * Gets the full description
     * @return item's full description
     */
    public String getDescriptionFull() { return descriptionFull; }

    /**
     * Gets the location the item belongs to
     * @return location item belongs to
     */
    public Location getLocation() { return location; }

    /**
     * Gets the value of the item
     * @return item's value
     */
    public int getValue() { return value; }

    /**
     * Gets the category of the item
     * @return item's category
     */
    public ItemCategory getCategory() { return category; }


    Map<String, Object> toMap() {
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
