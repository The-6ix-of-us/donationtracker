package cs2340.donationtracker;

import org.junit.Before;
import org.junit.Test;

import cs2340.donationtracker.model.Location;
import cs2340.donationtracker.model.LocationModel;

import static org.junit.Assert.*;

public class M10Nadun {
    private Location firstLoc;
    private Location secondLoc;
    private LocationModel testModel;

    @Before
    public void initialiseVariables() {
        String [] details = {"1234abc", "CharityPlace", "66.765", "97.234", "56 Woodruff Drive",
                "Atlanta", "GA", "33311", "Good", "4441349832", "www.charityplace.com" };
        String [] details2 = {"1234abcd", "CharityPlace2", "76.765", "88.234", "80 Woodruff Drive",
                "Atlanta", "GA", "33221", "Neutral", "4422349832", "www.charityplace2.com" };
        firstLoc = new Location(details);
        secondLoc = new Location(details2);
        testModel = LocationModel.getInstance();
    }

    @Test
    public void locationDoesNotExist () {

        Location found = testModel.findLocationById("1234abc66");
        assertNull(found);
    }

    @Test
    public void locationExists() {

            testModel.add(firstLoc);
            Location found = testModel.findLocationById("1234abc");

            assertNotNull(found);

    }


    @Test
    public void iterateLocationList () {

        testModel.add(firstLoc);
        testModel.add(secondLoc);

        Location foundFirst = testModel.findLocationById("1234abc");
        double firstLat = foundFirst.getLatitude();

        assertEquals(66.765, firstLat, 1e-15);

        Location foundSecond = testModel.findLocationById("1234abcd");

        double secondLat = foundSecond.getLatitude();

        assertNotEquals(66.765, secondLat);

        assertEquals(76.765, secondLat, 1e-15);


    }

    @Test
    public void testTest () {
        assertEquals(4, 2+2);
    }
}