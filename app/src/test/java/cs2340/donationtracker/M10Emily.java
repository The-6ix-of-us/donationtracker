package cs2340.donationtracker;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import java.util.ArrayList;
import java.util.List;

import cs2340.donationtracker.controllers.AddDonationActivity;
import cs2340.donationtracker.model.Location;
import cs2340.donationtracker.model.LocationModel;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({ LocationModel.class })
public class M10Emily {
    List<Location> locations;
    LocationModel locationModel;
    List<String> expectedLocationNames;

    @Before
    public void setUpLocationModel() {
        locations = new ArrayList<>();
        expectedLocationNames = new ArrayList<>();

        String[] location1 = {"123abc", "Location 1", "75.123", "85.123", "123 North Avenue", "Atlanta", "GA", "30332",
                              "Drop Off", "1234567890", "www.google.com"};
        expectedLocationNames.add(location1[1]);
        locations.add(new Location(location1));

        String[] location2 = {"1234abcd", "Location 2", "85.123", "75.123", "1234 North Avenue", "Atlanta", "GA", "30332",
                              "Drop Off", "1234567891", "www.bing.com"};
        expectedLocationNames.add(location2[1]);
        locations.add(new Location(location2));

        locationModel = Mockito.mock(LocationModel.class);
        PowerMockito.mockStatic(LocationModel.class);
        when(LocationModel.getInstance()).thenReturn(locationModel);
    }

    @Test
    public void testGetLocationNamesNonEmpty() {
        when(locationModel.getLocations()).thenReturn(locations);
        AddDonationActivity activity = new AddDonationActivity();
        assertEquals(expectedLocationNames, activity.getLocationNames());
    }

    @Test
    public void testGetLocationNamesEmpty() {
        when(locationModel.getLocations()).thenReturn(new ArrayList<>());
        AddDonationActivity activity = new AddDonationActivity();
        assertEquals(new ArrayList<>(), activity.getLocationNames());
    }
}
