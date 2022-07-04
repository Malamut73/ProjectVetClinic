package users;

import users.Appointment.Appointment;

import java.io.Serializable;
import java.util.*;

public class Client extends AbstractClient implements Serializable {


    public Client(String lastName, String firstName, String middleName) {
        super(lastName, firstName, middleName);
    }
}
