package cs2340.donationtracker;


import org.junit.Before;
import org.junit.Test;

import java.util.List;

import cs2340.donationtracker.model.DonationItem;
import cs2340.donationtracker.model.DonationItemModel;
import cs2340.donationtracker.model.Location;
import cs2340.donationtracker.model.LocationModel;

import static org.junit.Assert.*;
public class DonationItemTests {
    private Location firstLoc;
    private Location secondLoc;
    private LocationModel testLocationModel;
    private DonationItem item1;
    private DonationItem item2;
    private DonationItemModel itemModel;

    @Before
    public void initialiseVariables() {
        String [] details = {"1234abc", "CharityPlace", "66.765", "97.234", "56 Woodruff Drive",
                "Atlanta", "GA", "33311", "Good", "4441349832", "www.charityplace.com" };
        String [] details2 = {"1234abcd", "CharityPlace2", "76.765", "88.234", "80 Woodruff Drive",
                "Atlanta", "GA", "33221", "Neutral", "4422349832", "www.charityplace2.com" };
        String [] item1Details = {"Keurig V 2.0", "Coffee Machine", "A top-of-the-line coffee machine",
               "CharityPlace", "200", "Kitchen", "YYA11322" };
        String [] item2Details = {"North Face voyager", "Winter Jacket", "Insulated winter jacket rated for -50C",
                "CharityPlace", "150", "Clothing", "OQHA78513" };
        firstLoc = new Location(details);
        secondLoc = new Location(details2);
        testLocationModel = LocationModel.getInstance();
        testLocationModel.add(firstLoc);
        testLocationModel.add(secondLoc);
        item1 = new DonationItem(item1Details);
        item2 = new DonationItem(item2Details);
        itemModel = itemModel.getInstance();

    }

    @Test
    public void constructorWorks() {
        assertEquals(item1.getCategory().toString(), "Kitchen");

        String[] newDetails = {"iPhone 7s", "Smartphone", "The newest overpriced toy for Apple Sheeple",
        "CharityPlace2", "7000", "Electronics", "JRED5638"};

        DonationItem item3 = new DonationItem(newDetails);

        assertEquals(item3.getDescription(), "Smartphone");
    }

    @Test
    public void itemDoesNotExist () {

        DonationItem notFound = itemModel.getItemByID("YYkjh9902");
        assertNull(notFound);
    }

    @Test
    public void itemExists () {
        itemModel.addItem(item1);
        DonationItem found = itemModel.getItemByID("YYA11322");
        assertNotNull(found);

        assertEquals(found.getName(), "Keurig V 2.0");
    }
    @Test
    public void iterateItemModel () {
        itemModel.addItem(item1);
        itemModel.addItem(item2);

        DonationItem expected = itemModel.getItemByID("OQHA78513");
        assertEquals(expected.getName(),"North Face voyager");
    }
    @Test
    public void getItems_works () {

        itemModel.addItem(item1);
        itemModel.addItem(item2);

        List<DonationItem> items = itemModel.getItems();

        DonationItem second = items.get(1);

        assertEquals("Winter Jacket", second.getDescription());

    }

}
