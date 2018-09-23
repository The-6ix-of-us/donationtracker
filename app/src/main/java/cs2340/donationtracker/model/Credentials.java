package cs2340.donationtracker.model;

import java.util.ArrayList;
import java.util.List;

public class Credentials {

    private List<User> _users;

    public Credentials(ArrayList<User> users) {
        _users = new ArrayList<>();
        _users = users;
    }

    public List<User> getUsers() {
        return _users;
    }

    public boolean addUser(User user) {
        for (User u : _users) {
            if (u.getUsername().equals(user.getUsername())) {
                return false;
            }
        }
        _users.add(user);
        return true;
    }

}
