package moduls;

import java.util.Date;

public abstract class AbstractStaff extends User {
    public AbstractStaff() {
    }

    public AbstractStaff(String lastName, String firstName, String middleName) {
        super(lastName, firstName, middleName);
    }

    public AbstractStaff(int userId, String lastName, String firstName, String middleName, String login, String password, String role) {
        super(userId, lastName, firstName, middleName, login, password, role);
    }

    public AbstractStaff(String lastName, String firstName, String middleName, String login, String password, String role) {
        super(lastName, firstName, middleName, login, password, role);
    }
}
