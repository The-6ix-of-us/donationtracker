package cs2340.donationtracker.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DonationItemModel {
    private static final DonationItemModel _instance = new DonationItemModel();
    private final FirebaseHelper firebase = FirebaseHelper.getInstance();

    public static DonationItemModel getInstance() { return _instance; }

    private Map<String, DonationItem> itemsMap;
    private List<DonationItem> items;

    public DonationItemModel() {
        itemsMap = new HashMap<>();
        items = new ArrayList<>();
        setItems();
    }

    private void setItems() {
        firebase.getDonationItems(items, itemsMap);
    }

    public DonationItem getItemByID(String id) { return itemsMap.get(id); }

    public List<DonationItem> getItems() { return items; }

    public void addItem(DonationItem item) {
        itemsMap.put(item.getKey(), item);
        items.add(item);
    }
}
