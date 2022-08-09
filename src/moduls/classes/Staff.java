package moduls.classes;

import moduls.AbstractStaff;

import java.io.Serializable;
import java.util.Date;

public class Staff extends AbstractStaff {

    public Staff() {
    }

    public Staff(String lastName, String firstName, String middleName) {
        super(lastName, firstName, middleName);
    }

    public Staff(int userId, String lastName, String firstName, String middleName, String login, String password, String role) {
        super(userId, lastName, firstName, middleName, login, password, role);
    }

    public Staff(String lastName, String firstName, String middleName, String login, String password, String role) {
        super(lastName, firstName, middleName, login, password, role);
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
