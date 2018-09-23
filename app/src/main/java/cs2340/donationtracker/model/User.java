package cs2340.donationtracker.model;

public class User {

    private String _username;
    private String _password;

    public User(String username, String password) {
        _username = username;
        _password = password;
    }

    @Override
    public boolean equals(Object o) {
        User u = (User) o;
        return (u.getUsername().equals(_username) && u.getPassword().equals(_password));
    }

    public String getUsername() {
        return _username;
    }
    private void setUsername(String username) {
        _username = username;
    }

    private String getPassword() {
        return _password;
    }
    private void setPassword(String password) {
        _password = password;
    }


}
