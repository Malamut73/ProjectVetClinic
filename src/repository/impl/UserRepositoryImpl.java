package repository.impl;

import connector.Connector;
import helper.Helper;
import repository.UserRepository;
import moduls.classes.User;
import repository.config.ConfigLogPass;
import repository.config.ConfigUsers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class UserRepositoryImpl implements UserRepository {

    private final static Set<User> STAFF = new HashSet<>();
    private final static UserRepository USER_REPOSITORY = new UserRepositoryImpl();

    private UserRepositoryImpl(){}

    public static UserRepository GET_USER_REPOSITORY_SQL(){

        return USER_REPOSITORY;
    }

    @Override
    public void saveUser(User user) {

        String insert = "INSERT INTO " + ConfigUsers.USERS_TABLE + " (" +
                ConfigUsers.LASTNAME + ", " +
                ConfigUsers.FIRSTNAME + ", " +
                ConfigUsers.MIDDLE_NAME + ", " +
                ConfigUsers.USER_ROLE + ")" +
                "VALUES(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(insert);
            preparedStatement.setString(1, user.getLastName());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getMiddleName());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.printf("New %s was created\n", user.getRole());

    }
    @Override
    public void removeUser(User user) {

        String delete =
                "DELETE FROM " + ConfigUsers.USERS_TABLE +
                        " WHERE " +
                        ConfigUsers.USERS_TABLE + "." + ConfigUsers.LASTNAME + " = ? AND " +
                        ConfigUsers.USERS_TABLE + "." + ConfigUsers.FIRSTNAME + " = ? AND " +
                        ConfigUsers.USERS_TABLE + "." + ConfigUsers.MIDDLE_NAME + " = ?";

        ;
        try{PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(delete);
            preparedStatement.setString(1, user.getLastName());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getMiddleName());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.printf("%s was deleted\n", user.getRole());
    }
    @Override
    public void editUser(User user) {

        String select =
                "UPDATE " + ConfigUsers.USERS_TABLE +
                        " SET " +
                        ConfigUsers.USERS_TABLE + "." + ConfigUsers.LASTNAME + " = ?, " +
                        ConfigUsers.USERS_TABLE + "." + ConfigUsers.FIRSTNAME +  " = ?, " +
                        ConfigUsers.USERS_TABLE + "." + ConfigUsers.MIDDLE_NAME + " = ? " +
                        " WHERE " +
                        ConfigUsers.USERS_TABLE + "." + ConfigUsers.ID_USER + " = ?";

        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            preparedStatement.setString(1, user.getLastName());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getMiddleName());
            preparedStatement.setInt(4, user.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public List<User> findAll() {

        List<User> staffs = new LinkedList<>();
        User staff = null;
        ResultSet resultSet;

        String select = "SELECT * FROM " +
                ConfigUsers.USERS_TABLE +
                " ORDER BY " + ConfigUsers.USERS_TABLE + "." + ConfigUsers.FIRSTNAME +
                " ASC";
        try{
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int userId = resultSet.getInt(ConfigUsers.ID_USER);
                String lastName = resultSet.getString(ConfigUsers.LASTNAME);
                String firstName = resultSet.getString(ConfigUsers.FIRSTNAME);
                String middleName = resultSet.getString(ConfigUsers.MIDDLE_NAME);
                String role = resultSet.getString(ConfigUsers.USER_ROLE);
                staff = new User(lastName, firstName, middleName);
                staff.setUserId(userId);
                staff.setRole(role);
                staffs.add(staff);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return staffs;
    }
    @Override
    public User findUser(User user) {

        User tmpStaff = null;
        ResultSet resultSet;

        String selectFIO = "SELECT * FROM " + ConfigUsers.USERS_TABLE +
                " WHERE " +
                ConfigUsers.LASTNAME + " =? AND " +
                ConfigUsers.FIRSTNAME + " =? AND " +
                ConfigUsers.MIDDLE_NAME + " =?";
        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(selectFIO);
            preparedStatement.setString(1, user.getLastName());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getMiddleName());
            resultSet =  preparedStatement.executeQuery();

            while (resultSet.next()){
                int userId = resultSet.getInt(ConfigUsers.ID_USER);
                String lastName = resultSet.getString(ConfigUsers.LASTNAME);
                String firstName = resultSet.getString(ConfigUsers.FIRSTNAME);
                String middleName = resultSet.getString(ConfigUsers.MIDDLE_NAME);
                String role = resultSet.getString(ConfigUsers.USER_ROLE);
                tmpStaff = new User(lastName, firstName, middleName);
                tmpStaff.setUserId(userId);
                tmpStaff.setRole(role);
            }
        }catch (SQLException  e) {
            e.printStackTrace();
        }
        return tmpStaff;
    }
    @Override
    public User findUser(int id){

        User tmpStaff = null;
        ResultSet resultSet;

        String selectFIO = "SELECT * FROM " + ConfigUsers.USERS_TABLE +
                " WHERE " +
                ConfigUsers.ID_USER + " =?";
        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(selectFIO);
            preparedStatement.setInt(1, id);
            resultSet =  preparedStatement.executeQuery();

            while (resultSet.next()){
                int userId = resultSet.getInt(ConfigUsers.ID_USER);
                String lastName = resultSet.getString(ConfigUsers.LASTNAME);
                String firstName = resultSet.getString(ConfigUsers.FIRSTNAME);
                String middleName = resultSet.getString(ConfigUsers.MIDDLE_NAME);
                String role = resultSet.getString(ConfigUsers.USER_ROLE);
                tmpStaff = new User(lastName, firstName, middleName);
                tmpStaff.setUserId(userId);
                tmpStaff.setRole(role);
            }
        }catch (SQLException  e) {
            e.printStackTrace();
        }

        return tmpStaff;
    }
    @Override
    public void saveLogAndPass(User user) {

        String insertLogPass =
                "INSERT INTO " + ConfigLogPass.LOG_PASS_TABLE + " (" +
                        ConfigLogPass.LOGIN + ", " +
                        ConfigLogPass.PASSWORD + ", " +
                        ConfigLogPass.USER_ID + ")" +
                        "VALUES(?, ?, ?)";
        User userDB = findUser(user);
        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(insertLogPass);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, userDB.getUserId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean validate(String login, String password) {

        User user = null;
        ResultSet resultSet;

        String select = "SELECT * FROM " + ConfigLogPass.LOG_PASS_TABLE +
                " WHERE " +
                ConfigLogPass.LOGIN + " =? AND " +
                ConfigLogPass.PASSWORD + " =?";

        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                var loginDB = resultSet.getString(ConfigLogPass.LOGIN);
                var passwordDB = resultSet.getString(ConfigLogPass.PASSWORD);
                int userId = resultSet.getInt(ConfigLogPass.USER_ID);

                user = findUser(userId);
                Helper.setUser(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(user == null){
            return false;
        }

        if (Helper.getUser().getPassword().equals("")) {
            Helper.getHELPER().setFirstEnter(true);
            return true;
        }

        return ((Helper.getUser().getLogin().equals(login)) && Helper.getUser().getPassword().equals(password));

    }
    @Override
    public void setNewPassword(String newPassword) {

        String select =
                "UPDATE " + ConfigLogPass.LOG_PASS_TABLE +
                        " SET " +
                        ConfigLogPass.LOG_PASS_TABLE + "." + ConfigLogPass.PASSWORD + " = ?" +
                        " WHERE " +
                        ConfigLogPass.LOG_PASS_TABLE + "." + ConfigLogPass.USER_ID + " = ?";

        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            preparedStatement.setString(1,newPassword);
            preparedStatement.setInt(2, Helper.getUser().getUserId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("A password has been set");

    }
    @Override
    public boolean loginTrue(String login){

        User user = null;
        boolean isTrue = false;
        ResultSet resultSet;

        String select = "SELECT * FROM " + ConfigLogPass.LOG_PASS_TABLE +
                " WHERE " + ConfigLogPass.LOGIN + " =?";

        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                var loginDB = resultSet.getString(ConfigLogPass.LOGIN);
                var userIdDB = resultSet.getInt(ConfigLogPass.USER_ID);
                var passwordDB = resultSet.getString(ConfigLogPass.PASSWORD);
                user = UserRepositoryImpl.GET_USER_REPOSITORY_SQL().findUser(userIdDB);
                Helper.setUser(user);

                if(passwordDB.equals("")){
                    Helper.getHELPER().setFirstEnter(true);
                }

                isTrue = true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isTrue;
    }

    @Override
    public boolean passwordTrue(String login, String pass) {
        boolean isTrue = false;
        User user = null;
        ResultSet resultSet;

        String select = "SELECT * FROM " + ConfigLogPass.LOG_PASS_TABLE +
                " WHERE " + ConfigLogPass.LOGIN + " =? AND " +
                ConfigLogPass.PASSWORD + " =?";

        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, pass);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt(ConfigLogPass.USER_ID);

                isTrue = true;
                user = UserRepositoryImpl.GET_USER_REPOSITORY_SQL().findUser(userId);
                Helper.setUser(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isTrue;    }


}
