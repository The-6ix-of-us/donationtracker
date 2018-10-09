package cs2340.donationtracker.model;

/**
 * a class representing a location
 * this is what will be displayed in the detailed view
 */

public class Location {
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


    public int getKey() {
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




}
