package cs2340.donationtracker.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A class that holds the information for a store
 */

@SuppressWarnings("FieldMayBeFinal")
public class Location implements Parcelable {
        //could have used a string array to store all attributes
        //mostly just busy work and good coding practice.

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final DonationItemModel itemModel = DonationItemModel.getInstance();

    private String key;
    private String name;
    private double latitude;
    private double longitude;

    private String address;
    private String city;
    private String state;
    private int zip;

    private String type;
    private String phone;
    private String website;

    private List<DonationItem> items;
    private List<String> itemIDs;

    @Override
    public String toString() {
        return "Location{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                ", type='" + type + '\'' +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                '}';
    }

// --Commented out by Inspection START (11/08/2018 7:20 PM):
//    /**
//     * Constructor for a location
//     * @param details the details of the location
//     */
//    public Location (String [] details) {
//
//        /* Key is the auto-generated document ID from firebase */
//        key = details[0];
//        name = details[1];
//
//        latitude = Double.parseDouble(details[2]);
//        longitude = Double.parseDouble(details[3]);
//
//        address = details[4];
//        city = details[5];
//        state = details[6];
//        zip = Integer.parseInt(details[7]);
//
//        type = details[8];
//        phone = details[9];
//        website = details[10];
//
//        items = new ArrayList<>();
//        itemIDs = new ArrayList<>();
//
//    }
// --Commented out by Inspection STOP (11/08/2018 7:20 PM)

    /**
     * Getter for the Key field
     * @return Key of the Location
     */
    protected String getKey() {
        return key;
    }

    /**
     * Getter for the name field
     * @return the name of the location
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the latitude of the location
     * @return the latitude of the location
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Getter for the Longitude of the location
     * @return the longitude of the location
     */
    public double getLongitude() {
        return longitude;
    }

// --Commented out by Inspection START (11/08/2018 7:20 PM):
//    /**
//     * Getter for the City of the location
//     * @return the city of the location
//     */
//    public String getCity() {
//        return city;
//    }
// --Commented out by Inspection STOP (11/08/2018 7:20 PM)

// --Commented out by Inspection START (11/08/2018 7:20 PM):
//    /**
//     * Getter for the State of the location
//     * @return the state of the location
//     */
//    public String getState() {
//        return state;
//    }
// --Commented out by Inspection STOP (11/08/2018 7:20 PM)

// --Commented out by Inspection START (11/08/2018 7:21 PM):
//    /**
//     * Getter for the Zip of the location
//     * @return the zip of the location
//     */
//    public int getZip() {
//        return zip;
//    }
// --Commented out by Inspection STOP (11/08/2018 7:21 PM)

    /**
     * Getter for the type of the location
     * @return the type of the location
     */
    public String getType() {
        return type;
    }

    /**
     * Getter for the phone number of the location
     * @return the phone number of the location
     */
    public String getPhone() {
        return phone;
    }

// --Commented out by Inspection START (11/08/2018 7:21 PM):
//    /**
//     * Getter for the website of the location
//     * @return the website of the location
//     */
//    public String getWebsite() {
//        return website;
//    }
// --Commented out by Inspection STOP (11/08/2018 7:21 PM)

    /**
     * Getter for the address of the location
     * @return the address of the location
     */
    public String getAddress() { return address; }

    /**
     * Getter for the list of items at the location
     * @return A list of the donated items at the location
     */
    public List<DonationItem> getItems() { return items; }

    /**
     * Getter for the list of item ids
     * @return a list of item ids.
     */
    private List<String> getItemIDs() { return itemIDs; }

    /**
     * Sets the list of items at the location
     * @param ids the list of item ids
     */
    private void setItems(List<String> ids) {
        items = new ArrayList<>();
        if ((ids != null) && (!ids.isEmpty()) ) {
            for (String id : ids) {
                db.collection("donation-item").document(id).get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DonationItem item = new DonationItem(task.getResult());
                        itemModel.addItem(item);
                        items.add(item);
                    }
                });
            }
        }
    }

    /**
     * Adds an item to the donation list of the location
     * @param item the item to be added to the donation list
     */
    public void addItem(DonationItem item) {
        itemModel.addItem(item);
        items.add(item);
        DocumentReference docRef = db.collection("donation-item").document();
        item.setKey(docRef.getId());
        itemIDs.add(docRef.getId());
        docRef.set(item.toMap());
        db.collection("location-data").document(key).set(toMap());
    }

    private Location(Parcel in) {
        key = in.readString();
        name = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        city = in.readString();
        state = in.readString();
        zip = in.readInt();
        type = in.readString();
        phone = in.readString();
        website = in.readString();
        address = in.readString();
    }

    /**
     * Method to create a map representing the location
     * @return a map with the fields of the location.
     */

    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("Name", name);
        result.put("City", city);
        result.put("State", state);
        result.put("Zip", zip);
        result.put("Type", type);
        result.put("Phone", phone);
        result.put("Website", website);
        result.put("Street Address", address);
        GeoPoint coordinates = new GeoPoint(latitude, longitude);
        result.put("Coordinates", coordinates);
        result.put("Items", getItemIDs());
        return result;
    }

    /**
     * constructor using a document Snapshot
     * @param doc DocumentSnapshot with the location details.
     */
    @SuppressWarnings("unchecked")
    public Location(DocumentSnapshot doc) {
        key = doc.getId();
        Map<String, Object> docData = doc.getData();
        name = Objects.requireNonNull(docData).get("Name").toString();
        GeoPoint coordinates = (GeoPoint)docData.get("Coordinates");
        latitude = coordinates.getLatitude();
        longitude = coordinates.getLongitude();
        city = docData.get("City").toString();
        state = docData.get("State").toString();
        zip = Integer.parseInt(docData.get("Zip").toString());
        type = docData.get("Type").toString();
        phone = docData.get("Phone").toString();
        website = docData.get("Website").toString();
        address = docData.get("Street Address").toString();
        itemIDs = (docData.get("Items") == null) ? new ArrayList<>() :
                (List<String>) docData.get("Items");
        setItems(itemIDs);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /* *************************
       If you add new instance vars to Student, you will need to add them to the write
       Be sure the order you write information matches the order that the constructor above
       reads them.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(name);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeInt(zip);
        dest.writeString(type);
        dest.writeString(phone);
        dest.writeString(website);
        dest.writeString(address);

    }

    /**
     * Should not have to edit this method if the constructor and write method are
     * working correctly.
     */

    public static final Parcelable.Creator<Location> CREATOR
            = new Parcelable.Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}
