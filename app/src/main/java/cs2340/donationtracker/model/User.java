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
        String _username = u.getUsername();
        String _password = u.getPassword();
        return (_username.equals(username) && _password.equals(password));
    }

    @Override
    public int hashCode() {
        return username.hashCode() + password.hashCode();
    }

    /**
     * Gets the username of the user
     * @return user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user
     * @param username User's new username
     */
    private void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user
     * @return user's password
     */
    private String getPassword() { return password; }

    /**
     * Sets the password of the user
     * @param password User's new password
     */
    private void setPassword(String password) {
        this.password = password;
    }

}
