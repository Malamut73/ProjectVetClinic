package users;

import java.io.Serializable;
import java.util.Objects;

public class Staff extends User implements Serializable {


    public Staff(String lastName, String firstName, String middleName) {
        super(lastName, firstName, middleName);

    }

}
