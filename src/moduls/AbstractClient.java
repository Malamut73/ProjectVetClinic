package moduls;

public abstract class AbstractClient extends AbstractUser {

    public AbstractClient(String lastName, String firstName, String middleName) {
        super(lastName, firstName, middleName);
    }

    public AbstractClient(int userId, String lastName, String firstName, String middleName, String login, String password, String role) {
        super(userId, lastName, firstName, middleName, login, password, role);
    }

    public AbstractClient(String lastName, String firstName, String middleName, String login, String password, String role) {
        super(lastName, firstName, middleName, login, password, role);
    }
}
