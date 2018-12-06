package cs2340.donationtracker;

import com.google.protobuf.DescriptorProtos;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import cs2340.donationtracker.model.Location;
import cs2340.donationtracker.model.LocationModel;

import static org.junit.Assert.*;

public class LocationAndLocationModelUnitTests {
    private Location firstLoc;
    private Location secondLoc;
    private LocationModel testModel;

    @Before
    public void initialiseVariables() {
        String [] details = {"1234abc", "CharityPlace", "66.765", "97.234", "56 Woodruff Drive",
                "Atlanta", "GA", "33311", "Good", "4441349832", "www.charityplace.com" };
        String [] details2 = {"1234abcd", "CharityPlace2", "76.765", "88.234", "80 UGA Drive",
                "Athens", "GA", "33221", "Neutral", "4422349832", "www.charityplace2.com" };
        firstLoc = new Location(details);
        secondLoc = new Location(details2);
        testModel = LocationModel.getInstance();
    }

    @Test
    public void constructorAndGettersWork () {
        assertEquals("1234abc",firstLoc.getKey());
        assertEquals ("CharityPlace", firstLoc.getName());
        assertEquals(66.765, firstLoc.getLatitude(), 0.0000000000000001);
        assertEquals (97.234, firstLoc.getLongitude(), 0.000000000000001);
        assertEquals("56 Woodruff Drive", firstLoc.getAddress());
        assertEquals("Atlanta", firstLoc.getCity());
        assertEquals("GA", firstLoc.getState());
        assertEquals(33311, firstLoc.getZip());
        assertEquals("Good", firstLoc.getType());
        assertEquals("4441349832", firstLoc.getPhone());
        assertEquals("www.charityplace.com", firstLoc.getWebsite());

    }

    @Test
    public void findLocationByID_LocationDNE () {

        Location found = testModel.findLocationById("1234abc66");
        assertNull(found);
    }

    @Test
    public void findLocationByID_LocationExists() {

            testModel.add(firstLoc);
            Location found = testModel.findLocationById("1234abc");

            assertNotNull(found);

    }
    @Test
    public void findLocationByName_LocationDNE () {

        Location found = testModel.findLocationByName("BadWill");
        assertNull(found);
    }

    @Test
    public void findLocationByName_LocationExists () {

        testModel.add(firstLoc);
        Location found = testModel.findLocationByName("CharityPlace");

        assertNotNull(found);
        assertEquals("Good", found.getType());
    }


    @Test
    public void iterateLocationList_findLocationByID () {

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
    public void iterateLocationList_findLocationByName () {

        testModel.add(firstLoc);
        testModel.add(secondLoc);

        Location foundFirst = testModel.findLocationByName("CharityPlace2");

        assertEquals("Athens", foundFirst.getCity());

        Location foundSecond = testModel.findLocationByName("CharityPlace");



        assertNotEquals("Athens", foundSecond.getCity());

        assertEquals("Atlanta", foundSecond.getCity());


    }

    @Test
    public void getCurrentLocation_setCurrentLocation_work () {
        Location noCurrent = testModel.getCurrentLocation();
        assertNull(noCurrent);

        testModel.setCurrentLocation(firstLoc);

        Location current = testModel.getCurrentLocation();

        assertNotNull(current);

        assertEquals("CharityPlace", current.getName());
    }

    @Test
    public void getLocations_works () {
        List<Location> listLocations = testModel.getLocations();

        Location first = listLocations.get(0);

        assertEquals("CharityPlace" ,first.getName());
    }
    @Test
    public void testTest () {
        assertEquals(4, 2+2);
    }
}