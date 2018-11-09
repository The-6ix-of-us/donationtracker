package cs2340.donationtracker;

import org.junit.Before;
import org.junit.Test;

import cs2340.donationtracker.model.Location;
import cs2340.donationtracker.model.LocationModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class m10MattWebster {
    private Location firstLoc;
    private Location secondLoc;
    private LocationModel testModel;

    @Before
    public void initialiseVariables() {
        String [] details = {"885afafaf", "GoodWill", "72.999", "97.234", "1234 Atl Ave",
                "Atlanta", "GA", "33311", "Good", "4441349832", "www.GoodWillAtl.com" };
        String [] details2 = {"12340", "BadWill", "55.649", "88.234", "1290 5th Street",
                "Atlanta", "GA", "33221", "Neutral", "4422349832", "www.SalvationArmyAtl.com" };
        firstLoc = new Location(details);
        secondLoc = new Location(details2);
        testModel = LocationModel.getInstance();
    }

    @Test
    public void locationDoesNotExist () {
        Location found = testModel.findLocationByName("BadWill");
        assertNull(found);
    }

    @Test
    public void locationExists() {
        testModel.add(firstLoc);
        Location found = testModel.findLocationByName("GoodWill");
        assertNotNull(found);

    }


    @Test
    public void iterateLocationList () {

        testModel.add(firstLoc);
        testModel.add(secondLoc);

        Location foundFirst = testModel.findLocationByName("GoodWill");
        double firstLat = foundFirst.getLatitude();
        assertEquals(72.999, firstLat, 1e-15);

        Location foundSecond = testModel.findLocationByName("BadWill");
        double secondLat = foundSecond.getLatitude();
        assertNotEquals(72.999, secondLat);

        assertEquals(55.649, secondLat, 1e-15);


    }

    @Test
    public void testTest () {
        assertEquals(4, 2+2);
    }
}

