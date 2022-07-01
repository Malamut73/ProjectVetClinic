package repository;

import users.User;

import java.util.HashSet;
import java.util.Set;

public class UserRepositoryImpl implements UserRepository{

    private final static Set<User> USERS = new HashSet<>();

    private final static UserRepositoryImpl SINGLETON = new UserRepositoryImpl();


    public static UserRepositoryImpl getSingleton(){
        return SINGLETON;
    }


    @Override
    public void save(User user) {
        USERS.add(user);
    }

    @Override
    public void remove(User user) {

    }

    @Override
    public Set<User> findAll() {
        return USERS;
    }
}
