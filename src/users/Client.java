package users;

import java.io.Serializable;

public class Client extends AbstractClient implements Serializable {


    public Client(String lastName, String firstName, String middleName) {
        super(lastName, firstName, middleName);
    }
}
