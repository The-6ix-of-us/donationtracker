package cs2340.donationtracker.model;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Emily Wilson
 *
 * Represents a collection of donation items
 */
public final class DonationItemModel {
    private static final DonationItemModel _instance = new DonationItemModel();
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    static DonationItemModel getInstance() { return _instance; }

    private final Map<String, DonationItem> itemsMap;
    private final List<DonationItem> items;

    private DonationItemModel() {
        itemsMap = new HashMap<>();
        items = new ArrayList<>();
        setItems();
    }

    private void setItems() {
        db.collection("donation-items").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot doc : task.getResult()) {
                    DonationItem item = new DonationItem(doc);
                    itemsMap.put(doc.getId(), item);
                    items.add(item);
                }
            }
        });
    }

    /**
     * Gets the donation item associated with the unique id
     * @param id    String that uniquely identifies an item
     * @return the donation item that has the unique id
     */
    public DonationItem getItemByID(String id) { return itemsMap.get(id); }


    /**
     * Getter to retrieve the list of Donation items
     * @return items The list of Donation items
     */
    public List<DonationItem> getItems() { return items; }

    void addItem(DonationItem item) {
        itemsMap.put(item.getKey(), item);
        items.add(item);
    }
}
