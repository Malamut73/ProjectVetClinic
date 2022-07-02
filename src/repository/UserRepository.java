package repository;

import users.User;

import java.util.Set;

public interface UserRepository {

    void save (User user);
    void remove(User user);
    Set<User> findAll();


}
