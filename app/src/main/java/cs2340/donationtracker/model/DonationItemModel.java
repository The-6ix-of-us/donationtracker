package cs2340.donationtracker.model;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emily Wilson
 *
 * Represents a collection of donation items
 */
final class DonationItemModel {
    private static final DonationItemModel _instance = new DonationItemModel();
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    static DonationItemModel getInstance() { return _instance; }

    private final List<DonationItem> items;

    private DonationItemModel() {
        items = new ArrayList<>();
        setItems();
    }

    private void setItems() {
        CollectionReference collection = db.collection("donation-items");
        Task<QuerySnapshot> snap = collection.get();
        snap.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot doc : task.getResult()) {
                    DonationItem item = new DonationItem(doc);
                    items.add(item);
                }
            }
        });
    }


    /**
     * Getter to retrieve the list of Donation items
     * @return items The list of Donation items
     */
    public List<DonationItem> getItems() { return items; }

    void addItem(DonationItem item) {
        items.add(item);
    }
}
