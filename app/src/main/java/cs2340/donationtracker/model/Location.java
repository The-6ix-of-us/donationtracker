package cs2340.donationtracker.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Location implements Parcelable {
        //could have used a string array to store all attributes
        //mostly just busy work and good coding practice.
    private int key;
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
    public Location (String [] details) {

        key = Integer.parseInt(details[0]);
        name = details[1];

        latitude = Double.parseDouble(details[2]);
        longitude = Double.parseDouble(details[3]);

        address = details[4];
        city = details[5];
        state = details[6];
        zip = Integer.parseInt(details[7]);

        type = details[8];
        phone = details[9];
        website = details[10];

    }


    protected int getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getZip() {
        return zip;
    }

    public String getType() {
        return type;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public String getAddress() { return address; }

    private Location(Parcel in) {
        key = in.readInt();
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
        dest.writeInt(key);
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
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}
