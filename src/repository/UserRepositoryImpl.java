package repository;

import users.Staff;
import users.User;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserRepositoryImpl implements UserRepository, Serializable {

    private final static Set<User> USERS = new HashSet<>();
    static{
        Staff staff = new Staff("admin", "admin","admin", "admin", "admin");
        USERS.add(staff);
    }

    private final static UserRepositoryImpl SINGLETON = new UserRepositoryImpl();

    public static UserRepositoryImpl getSingleton(){

        return SINGLETON;
    }
    private UserRepositoryImpl(){}

    @Override
    public void save(User user) {
        USERS.add(user);
    }
    @Override
    public void remove(User user) {
        USERS.remove(user);

    }
    @Override
    public Set<User> findAll() {
        return USERS;
    }
}
