package repository.impl;

import connector.Connector;
import repository.StaffRepository;
import moduls.classes.Staff;
import repository.config.ConfigLogPass;
import repository.config.ConfigUsers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class StaffRepositoryImpl implements StaffRepository {

    private final static Set<Staff> STAFF = new HashSet<>();
    private final static StaffRepository STAFF_REPOSITORY = new StaffRepositoryImpl();

    private StaffRepositoryImpl(){}

    public static StaffRepository GET_STAFF_REPOSITORY_SQL(){

        return STAFF_REPOSITORY;
    }



    @Override
    public void saveStaff(Staff staff) {

        String insert = "INSERT INTO " + ConfigUsers.USERS_TABLE + " (" +
                ConfigUsers.LASTNAME + ", " +
                ConfigUsers.FIRSTNAME + ", " +
                ConfigUsers.MIDDLE_NAME + ", " +
                ConfigUsers.USER_ROLE + ")" +
                "VALUES(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(insert);
            preparedStatement.setString(1, staff.getLastName());
            preparedStatement.setString(2, staff.getFirstName());
            preparedStatement.setString(3, staff.getMiddleName());
            preparedStatement.setString(4, staff.getRole());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String insertLogPass =
                "INSERT INTO " + ConfigLogPass.LOG_PASS_TABLE + " (" +
                        ConfigLogPass.LOGIN + ", " +
                        ConfigLogPass.PASSWORD + ", " +
                        ConfigLogPass.USER_ID + ")" +
                        "VALUES(?, ?, ?)";

        Staff staffDB = getStaff(staff);
        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(insertLogPass);
            preparedStatement.setString(1, staff.getLogin());
            preparedStatement.setString(2, staff.getPassword());
            preparedStatement.setInt(3, staffDB.getUserId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("New staff was created");

    }
    @Override
    public void removeStaff(Staff staff) {
        String delete =
                "DELETE FROM " + ConfigUsers.USERS_TABLE +
                        " WHERE " +
                        ConfigUsers.USERS_TABLE + "." + ConfigUsers.LASTNAME + " = ? AND " +
                        ConfigUsers.USERS_TABLE + "." + ConfigUsers.FIRSTNAME + " = ? AND " +
                        ConfigUsers.USERS_TABLE + "." + ConfigUsers.MIDDLE_NAME + " = ?";

        ;
        try{PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(delete);
            preparedStatement.setString(1, staff.getLastName());
            preparedStatement.setString(2, staff.getFirstName());
            preparedStatement.setString(3, staff.getMiddleName());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Staff was deleted");
    }
    @Override
    public void editStaff(Staff staff) {

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
            preparedStatement.setString(1, staff.getLastName());
            preparedStatement.setString(2, staff.getFirstName());
            preparedStatement.setString(3, staff.getMiddleName());
            preparedStatement.setInt(4, staff.getUserId());
            preparedStatement.executeUpdate();
//            System.out.println(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Staff was edit");
    }
    @Override
    public boolean findAll() {

        Staff staff = null;
        ResultSet resultSet;

        String select = "SELECT " +
                ConfigUsers.USERS_TABLE + "." + ConfigUsers.ID_USER + ", " +
                ConfigUsers.USERS_TABLE + "." + ConfigUsers.LASTNAME + ", " +
                ConfigUsers.USERS_TABLE + "." + ConfigUsers.FIRSTNAME + ", " +
                ConfigUsers.USERS_TABLE + "." + ConfigUsers.MIDDLE_NAME + ", " +
                ConfigLogPass.LOG_PASS_TABLE + "." + ConfigLogPass.LOGIN + ", " +
                ConfigLogPass.LOG_PASS_TABLE + "." + ConfigLogPass.PASSWORD + ", " +
                ConfigUsers.USERS_TABLE + "." + ConfigUsers.USER_ROLE +
                " FROM " + ConfigUsers.USERS_TABLE +
                " INNER JOIN " + ConfigLogPass.LOG_PASS_TABLE +
                " ON " +
                ConfigUsers.USERS_TABLE + "." + ConfigUsers.ID_USER + " = " +
                ConfigLogPass.LOG_PASS_TABLE + "." + ConfigLogPass.USER_ID +
                " WHERE " + ConfigUsers.USERS_TABLE + "." + ConfigUsers.USER_ROLE +
                " = ?";
        try{
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            preparedStatement.setString(1, ConfigUsers.ADMIN_TYPE);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int userId = resultSet.getInt(ConfigUsers.ID_USER);
                String lastName = resultSet.getString(ConfigUsers.LASTNAME);
                String firstName = resultSet.getString(ConfigUsers.FIRSTNAME);
                String middleName = resultSet.getString(ConfigUsers.MIDDLE_NAME);
                String loginDB = resultSet.getString(ConfigLogPass.LOGIN);
                String passwordDB = resultSet.getString(ConfigLogPass.PASSWORD);
                String role = resultSet.getString(ConfigUsers.USER_ROLE);
                staff = new Staff(userId, lastName, firstName, middleName, loginDB, passwordDB, role);
                System.out.println(staff.toString());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return staff != null ? true : false;
    }
    @Override
    public Staff getStaff(Staff staff) {

        Staff tmpStaff = null;
        ResultSet resultSet;

        String selectFIO = "SELECT * FROM " + ConfigUsers.USERS_TABLE +
                " WHERE " +
                ConfigUsers.LASTNAME + " =? AND " +
                ConfigUsers.FIRSTNAME + " =? AND " +
                ConfigUsers.MIDDLE_NAME + " =?";
        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(selectFIO);
            preparedStatement.setString(1, staff.getLastName());
            preparedStatement.setString(2, staff.getFirstName());
            preparedStatement.setString(3, staff.getMiddleName());
            resultSet =  preparedStatement.executeQuery();

            while (resultSet.next()){
                int userId = resultSet.getInt(ConfigUsers.ID_USER);
                String lastName = resultSet.getString(ConfigUsers.LASTNAME);
                String firstName = resultSet.getString(ConfigUsers.FIRSTNAME);
                String middleName = resultSet.getString(ConfigUsers.MIDDLE_NAME);
                String role = resultSet.getString(ConfigUsers.USER_ROLE);
                tmpStaff = new Staff(userId, lastName, firstName, middleName, null, null, role);
            }
        }catch (SQLException  e) {
            e.printStackTrace();
        }

        if(!(tmpStaff == null)){
            String selectLP = "SELECT * FROM " + ConfigLogPass.LOG_PASS_TABLE +
                    " WHERE " +
                    ConfigLogPass.USER_ID + " =?";
            try {
                PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(selectLP);
                preparedStatement.setInt(1, tmpStaff.getUserId());
                resultSet =  preparedStatement.executeQuery();

                while (resultSet.next()){
                    String loginDB = resultSet.getString(ConfigLogPass.LOGIN);
                    String passwordDB = resultSet.getString(ConfigLogPass.PASSWORD);
                    tmpStaff.setLogin(loginDB);
                    tmpStaff.setPassword(passwordDB);
                }
            }catch (SQLException  e) {
                e.printStackTrace();
            }
        }



        return tmpStaff;
    }

}
