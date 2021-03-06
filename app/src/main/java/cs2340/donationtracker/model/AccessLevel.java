package cs2340.donationtracker.model;

public enum AccessLevel {

    ADMIN ("Admin"),
    LOCATION_EMPLOYEE ("Location Employee"),
    USER ("User");

    /** the two letter representation of the class standing */
    private final String accessLevel;

    /**
     * Constructor for the enumeration
     *
     * @param paccessLevel   class standing abbreviation
     */
    AccessLevel(String paccessLevel) {
        accessLevel = paccessLevel;
    }

    /**
     *
     * @return the abbreviation for the class standing
     */
    public String getAccessLevel() { return accessLevel; }

    /**
     *
     * @return the display string representation of the class standing
     */
     public String toString() { return accessLevel; }

}

