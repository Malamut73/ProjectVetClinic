package users;

import java.io.Serializable;

public class Staff extends AbstractStaff implements Serializable {


    public Staff(String lastName, String firstName, String middleName, String login, String password) {
        super(lastName, firstName, middleName, login, password);
    }
}
