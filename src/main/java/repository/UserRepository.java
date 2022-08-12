package repository;

import moduls.classes.User;

import java.util.List;

public interface UserRepository {

    void saveUser(User staff);
    void removeUser(User staff);
    void editUser(User staff);
    List<User> findAll();
    User findUser(User staff);
    User findUser(int id);
    void saveLogAndPass(User staff);
    boolean validate(String login, String password);
    void setNewPassword(String newPassword);
    boolean loginTrue(String login);
    boolean passwordTrue(String login, String pass);



}
