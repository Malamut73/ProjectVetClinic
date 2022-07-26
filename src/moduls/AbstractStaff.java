package moduls;

import java.util.Date;

public abstract class AbstractStaff extends User {

    private String login;
    private String password;

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public AbstractStaff(int userId, String lastName, String firstName, String middleName, String login, String password, Date dateOfRegistration) {
        super(userId, lastName, firstName, middleName, dateOfRegistration);
        this.login = login;
        this.password = password;
    }

    public AbstractStaff(int userId, String lastName, String firstName, String middleName, Date dateOfRegistration) {
        super(userId, lastName, firstName, middleName, dateOfRegistration);
    }

    public AbstractStaff(String lastName, String firstName, String middleName, String login, String password) {
        super(lastName, firstName, middleName);
        this.login = login;
        this.password = password;
    }

    public AbstractStaff(String lastName, String firstName, String middleName) {
        super(lastName, firstName, middleName);
    }
}
