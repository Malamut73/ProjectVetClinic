package users;

import java.io.Serializable;

public abstract class AbstractStaff extends User implements Serializable {
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

    public AbstractStaff(String lastName, String firstName, String middleName, String login, String password) {
        super(lastName, firstName, middleName);
        this.login = login;
        this.password = password;
    }
}
