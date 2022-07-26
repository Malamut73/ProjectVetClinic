package moduls;

import java.util.Date;

public class Client extends AbstractClient {

    public Client(int userId, String lastName, String firstName, String middleName, Date dateOfRegistration) {
        super(userId, lastName, firstName, middleName, dateOfRegistration);
    }

    public Client(String lastName, String firstName, String middleName) {
        super(lastName, firstName, middleName);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
