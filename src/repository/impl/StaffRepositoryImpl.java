package repository.impl;

import connector.Connector;
import repository.StaffRepository;
import repository.config.ConfigStaff;
import moduls.Staff;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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

        String insert = "INSERT INTO " +
                ConfigStaff.STAFF_TABLE + "(" +
                ConfigStaff.LASTNAME + "," +
                ConfigStaff.FIRSTNAME + "," +
                ConfigStaff.MIDDLE_NAME + "," +
                ConfigStaff.LOGIN + "," +
                ConfigStaff.PASSWORD + ")" +
                "VALUES(?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(insert);
            preparedStatement.setString(1, staff.getLastName());
            preparedStatement.setString(2, staff.getFirstName());
            preparedStatement.setString(3, staff.getMiddleName());
            preparedStatement.setString(4, staff.getLogin());
            preparedStatement.setString(5, staff.getPassword());
            preparedStatement.executeUpdate();
            System.out.println("New staff was created");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeStaff(Staff staff) {
    }
    @Override
    public void editStaff(Staff staff) {
    }
    @Override
    public Set<Staff> findAll() {

        String select = "SELECT * FROM " + ConfigStaff.STAFF_TABLE;

        try{
            PreparedStatement preparedStatement = Connector.getConnection()
                    .prepareStatement(select);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                int userId = resultSet.getInt(ConfigStaff.ID_STAFF);
                String firstName = resultSet.getString(ConfigStaff.FIRSTNAME);
                String lastName = resultSet.getString(ConfigStaff.LASTNAME);
                String middleName = resultSet.getString(ConfigStaff.MIDDLE_NAME);
                String login = resultSet.getString(ConfigStaff.LOGIN);
                String password = resultSet.getString(ConfigStaff.PASSWORD);
                Date dateOfRegistration = resultSet.getDate(ConfigStaff.DATE_OF_REGISTRATION);

                STAFF.add(new Staff(userId, lastName, firstName, middleName, login, password, dateOfRegistration));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return STAFF;
    }

    @Override
    public Staff getStaff(Staff staff) {

        Staff tmpStaff = null;
        ResultSet resultSet;

        String select = "SELECT * FROM " +
                ConfigStaff.STAFF_TABLE + " WHERE " +
                ConfigStaff.LASTNAME + " =? AND " +
                ConfigStaff.FIRSTNAME + " =? AND " +
                ConfigStaff.MIDDLE_NAME + " =?";

        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            preparedStatement.setString(1, staff.getLastName());
            preparedStatement.setString(2, staff.getFirstName());
            preparedStatement.setString(3, staff.getMiddleName());
            resultSet =  preparedStatement.executeQuery();

            while (resultSet.next()){
                int staffId = resultSet.getInt(ConfigStaff.ID_STAFF);
                String firstName = resultSet.getString(ConfigStaff.FIRSTNAME);
                String lastName = resultSet.getString(ConfigStaff.LASTNAME);
                String middleName = resultSet.getString(ConfigStaff.MIDDLE_NAME);
                Date dateOfRegistration = resultSet.getDate(ConfigStaff.DATE_OF_REGISTRATION);
                tmpStaff = new Staff(staffId, lastName, firstName, middleName, dateOfRegistration);

            }
        }catch (SQLException  e) {
            e.printStackTrace();
        }
        return tmpStaff;
    }

}
