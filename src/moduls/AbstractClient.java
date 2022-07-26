package moduls;

import java.util.Date;

public abstract class AbstractClient extends User {


    public AbstractClient(int userId, String lastName, String firstName, String middleName, Date dateOfRegistration) {
        super(userId, lastName, firstName, middleName, dateOfRegistration);
    }

    public AbstractClient(String lastName, String firstName, String middleName) {
        super(lastName, firstName, middleName);
    }

}
