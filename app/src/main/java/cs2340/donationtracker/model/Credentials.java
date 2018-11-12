package cs2340.donationtracker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peter Franzek
 *
 * A class representing a credentials object which is a list of users
 */
public class Credentials {

    private final List<User> users;

    /**
     * Constructor for a credentials objects
     */
    public Credentials() {
        this.users = new ArrayList<>();
    }

    /**
     * Gets the list of users
     * @return the list of users associated with the credential
     */
    public Iterable<User> getUsers() {
        return users;
    }

    /**
     * Adds user to credential
     * @param user      adds a user to the credentials objects
     */
    public void addUser(User user) {
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                return;
            }
        }
        users.add(user);
    }

}
