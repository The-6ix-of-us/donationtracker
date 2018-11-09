package cs2340.donationtracker.model;

/**
 * Created by Peter Franzek
 *
 * A class representing a user
 */
public class User {

    private String username;
    private String password;

    /**
     * Constructor for a user that takes in a username and password
     * @param username      username of the user
     * @param password      password of the user
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (getClass() != o.getClass()) { return false; }
        User u = (User) o;
        return (u.getUsername().equals(username) && u.getPassword().equals(password));
    }

    /**
     * Gets the username of the user
     * @return user's username
     */
    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private String getPassword() { return password; }

    private void setPassword(String password) {
        this.password = password;
    }

}
