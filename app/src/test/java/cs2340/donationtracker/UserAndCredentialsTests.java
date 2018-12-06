package cs2340.donationtracker;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs2340.donationtracker.model.User;
import cs2340.donationtracker.model.Credentials;
import static org.junit.Assert.*;


public class UserAndCredentialsTests {

    private User user1;
    private User user2;

    private ArrayList<User> users;

    private Credentials UserCredentials;

    @Before
    public void initializeVariables () {
        user1 = new User("Bill", "12345");
        user2 = new User("Jill", "abcde");

    }
    @Test
    public void equalsWorks () {
        User testUser = new User("Bill", "12345");
        testUser.equals(user1);
    }

    @Test
    public void constructorAndGetterWork () {
        assertEquals("Jill", user2.getUsername());
    }

    @Test
    public void Credentials_Constructor_Works () {
        users = new ArrayList<>();

        users.add(user1);
        users.add(user2);

        UserCredentials =  new Credentials(users);

        List<User> listOfUsers = UserCredentials.getUsers();

        User first = listOfUsers.get(0);

        assertEquals("Bill",first.getUsername());

        User second = listOfUsers.get(1);

        assertEquals("Jill", second.getUsername());


    }

    @Test
    public void addUser_works () {

        users = new ArrayList<>();

        users.add(user1);
        users.add(user2);

        UserCredentials =  new Credentials(users);

        User toAdd = new User ("Will", "qwerty");



        assertNotNull(UserCredentials);

        assertEquals(true, UserCredentials.addUser(toAdd));

        assertEquals(false, UserCredentials.addUser(toAdd));

        List <User> listOfUsers = UserCredentials.getUsers();

        User newUser = listOfUsers.get(2);



        assertEquals("Will", newUser.getUsername());


    }
}
