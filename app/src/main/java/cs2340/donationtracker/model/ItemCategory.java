package cs2340.donationtracker.model;

public enum ItemCategory {
    CLOTHING ("Clothing"),
    HAT ("Hat"),
    KITCHEN("Kitchen"),
    ELECTRONICS("Electronics"),
    HOUSEHOLD("Household"),
    OTHER("Other");

    private final String itemCategory;

    ItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String toString() {
        return itemCategory;
    }

    public static ItemCategory getCategory(String category) {
        switch (category) {
            case "Clothing": return CLOTHING;
            case "Hat": return HAT;
            case "Kitchen": return KITCHEN;
            case "Electronics": return ELECTRONICS;
            case "Household": return HOUSEHOLD;
            default: return OTHER;
        }
    }

}
