package cs2340.donationtracker.model;

/**
 * Created by Emily Wilson
 *
 * An enum that represents the item category options
 */
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

    /**
     * Returns the string representation which shows the category
     * @return the item category represented as a string
     */
    public String toString() {
        return itemCategory;
    }

    /**
     * Gets item category associated with the string category supplied
     * @param category      the string category that will be used to determine the item category
     * @return the item category
     */
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
