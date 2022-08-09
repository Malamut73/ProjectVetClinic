package moduls.classes;

import moduls.AbstractClient;

import java.util.Date;

public class Client extends AbstractClient {

    public Client(String lastName, String firstName, String middleName) {
        super(lastName, firstName, middleName);
    }

    public Client(int userId, String lastName, String firstName, String middleName, String login, String password, String role) {
        super(userId, lastName, firstName, middleName, login, password, role);
    }

    public Client(String lastName, String firstName, String middleName, String login, String password, String role) {
        super(lastName, firstName, middleName, login, password, role);
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
