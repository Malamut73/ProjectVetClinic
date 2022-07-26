package moduls;

import java.io.Serializable;
import java.util.Date;

public class Staff extends AbstractStaff  {


    public Staff(int userId, String lastName, String firstName, String middleName, String login, String password, Date dateOfRegistration) {
        super(userId, lastName, firstName, middleName, login, password, dateOfRegistration);
    }
    public Staff(int userId, String lastName, String firstName, String middleName, Date dateOfRegistration) {
        super(userId, lastName, firstName, middleName, dateOfRegistration);
    }
    public Staff(String lastName, String firstName, String middleName, String login, String password) {
        super(lastName, firstName, middleName, login, password);
    }

    public Staff(String lastName, String firstName, String middleName) {
        super(lastName, firstName, middleName);
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
