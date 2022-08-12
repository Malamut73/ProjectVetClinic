package moduls.classes;

import moduls.AbstractUser;


public class User extends AbstractUser {

    public User(String lastName, String firstName, String middleName) {
        super(lastName, firstName, middleName);
    }

    public User(int userId, String lastName, String firstName, String middleName, String login, String password, String role) {
        super(userId, lastName, firstName, middleName, login, password, role);
    }

    @Override
    public String getFullName() {
        return super.getFullName();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
