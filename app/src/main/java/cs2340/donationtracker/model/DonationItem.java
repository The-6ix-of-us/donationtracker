package cs2340.donationtracker.model;

public class DonationItem {
    String name;
    String description;
    String descriptionFull;
    Location location;
    String value;
    ItemCategory category;

    public DonationItem(String[] itemInfo) {
        name = itemInfo[0];
        description = itemInfo[1];
        descriptionFull = itemInfo[2];
        location = LocationModel.getInstance().findItemByName(itemInfo[3]);
        value = itemInfo[4];
        category = ItemCategory.getCategory(itemInfo[5]);
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getDescriptionFull() { return descriptionFull; }
    public Location getLocation() { return location; }
    public String getValue() { return value; }
    public ItemCategory getCategory() { return category; }
}
