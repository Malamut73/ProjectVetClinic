package repository.impl;

import connector.Connector;
import moduls.Appointment;
import moduls.Client;
import moduls.Staff;
import moduls.User;
import repository.AppointmentRepository;
import repository.config.ConfigAppointment;
import repository.config.ConfigClient;
import repository.config.ConfigStaff;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class AppointmentRepositoryImpl implements AppointmentRepository {

    private final static Set<Appointment> APPOINTMENTS = new HashSet<>();
    private final static AppointmentRepository APPOINTMENT_REPOSITORY = new AppointmentRepositoryImpl();

    private AppointmentRepositoryImpl(){}

    public static AppointmentRepository GET_APPOINTMENT_REPOSITORY_SQL(){

        return APPOINTMENT_REPOSITORY;
    }

    @Override
    public void saveAppointment(Appointment appointment) {

        String insert = "INSERT INTO " + ConfigAppointment.APPOINTMENT_TABLE + "(" +
                ConfigAppointment.ID_STAFF + "," +
                ConfigAppointment.ID_CLIENT + "," +
                ConfigAppointment.STATUS + "," +
                ConfigAppointment.DATE_OF_APPOINTMENT +
                ")" +
                "VALUES(?,?,?,?)";

        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(insert);
            preparedStatement.setInt(1, appointment.getStaff().getUserId());
            preparedStatement.setInt(2, appointment.getClient().getUserId());
            preparedStatement.setString(3, appointment.getStatus());
            preparedStatement.setTimestamp(4, appointment.getSqlDate());

            preparedStatement.executeUpdate();
            System.out.println("New appointment was created");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeAppointment(Appointment appointment) {

    }

    @Override
    public void editAppointment(Appointment appointment) {


        String select = " UPDATE " +
                ConfigAppointment.APPOINTMENT_TABLE +
                " SET " +
                ConfigAppointment.APPOINTMENT_TABLE + "." + ConfigAppointment.STATUS +
                " = " + "'" + appointment.getStatus() + "'" +
                " WHERE " +
                ConfigAppointment.APPOINTMENT_TABLE + "." + ConfigAppointment.ID_APPOINTMENT +
                " = " + appointment.getIdAppointment();
        System.out.println(select);

        try {
            Statement statement = Connector.getConnection().createStatement();
            statement.executeUpdate(select);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Appointment was change");

    }

    @Override
    public Set<Appointment> findAll() {

        String select = "SELECT " +
                ConfigAppointment.APPOINTMENT_TABLE + "." + ConfigAppointment.ID_APPOINTMENT + ", "  +
                ConfigClient.CLIENT_TABLE + "." + ConfigClient.LASTNAME + ", " +
                ConfigClient.CLIENT_TABLE + "." + ConfigClient.FIRSTNAME + ", " +
                ConfigClient.CLIENT_TABLE + "." + ConfigClient.MIDDLE_NAME + ", " +
                ConfigStaff.STAFF_TABLE + "." + ConfigStaff.FIRSTNAME + ", " +
                ConfigStaff.STAFF_TABLE + "." + ConfigStaff.LASTNAME + ", " +
                ConfigStaff.STAFF_TABLE + "." + ConfigStaff.MIDDLE_NAME + ", " +
                ConfigAppointment.APPOINTMENT_TABLE + "." + ConfigAppointment.DATE_OF_APPOINTMENT  + ", "  +
                ConfigAppointment.APPOINTMENT_TABLE + "." + ConfigAppointment.STATUS  +
                " FROM " + ConfigClient.CLIENT_TABLE +
                " INNER JOIN " + ConfigAppointment.APPOINTMENT_TABLE +
                " ON " + ConfigClient.CLIENT_TABLE + "." + ConfigClient.ID_CLIENT +
                " = " + ConfigAppointment.APPOINTMENT_TABLE + "." + ConfigAppointment.ID_CLIENT +
                " INNER JOIN " + ConfigStaff.STAFF_TABLE +
                " ON " + ConfigStaff.STAFF_TABLE + "." + ConfigStaff.ID_STAFF +
                " = " + ConfigAppointment.APPOINTMENT_TABLE + "." + ConfigAppointment.ID_STAFF;
        System.out.println(select);


        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            ResultSet resultSet =  preparedStatement.executeQuery();

            while (resultSet.next()){
                int appointmentId = resultSet.getInt(ConfigAppointment.ID_APPOINTMENT);
                String clientLastName = resultSet.getString(ConfigClient.LASTNAME);
                String clientFirstName = resultSet.getString(ConfigClient.FIRSTNAME);
                String clientMiddleName = resultSet.getString(ConfigClient.MIDDLE_NAME);

                String staffFirstName = resultSet.getString(ConfigStaff.FIRSTNAME);
                String staffLastName = resultSet.getString(ConfigStaff.LASTNAME);
                String staffMiddleName = resultSet.getString(ConfigStaff.MIDDLE_NAME);

                Date dateOfAppointment = resultSet.getDate(ConfigAppointment.DATE_OF_APPOINTMENT);
                String status = resultSet.getString(ConfigAppointment.STATUS);

//                Client clietn = ClientRepositoryImpl.GET_CLIENT_REPOSITORY_SQL().getClient(new Client(clientLastName, clientFirstName, clientMiddleName));
                Client client = new Client(clientLastName, clientFirstName, clientMiddleName);
//                Staff staff = StaffRepositoryImpl.GET_STAFF_REPOSITORY_SQL().getStaff(new Staff(staffLastName, staffFirstName, staffMiddleName));
                Staff staff = new Staff(staffLastName, staffFirstName, staffMiddleName);

                APPOINTMENTS.add(new Appointment(appointmentId, status, staff, dateOfAppointment, client, dateOfAppointment));

            }
        }catch (SQLException  e) {
            e.printStackTrace();
        }

        return APPOINTMENTS;
    }

    @Override
    public Set<Appointment> getAppointment(User user) {

        Set<Appointment> clientAppointment = new HashSet<>();

        String select = "SELECT " +
                ConfigAppointment.APPOINTMENT_TABLE + "." + ConfigAppointment.ID_APPOINTMENT + ", "  +
                ConfigClient.CLIENT_TABLE + "." + ConfigClient.LASTNAME + ", " +
                ConfigClient.CLIENT_TABLE + "." + ConfigClient.FIRSTNAME + ", " +
                ConfigClient.CLIENT_TABLE + "." + ConfigClient.MIDDLE_NAME + ", " +
                ConfigStaff.STAFF_TABLE + "." + ConfigStaff.FIRSTNAME + ", " +
                ConfigStaff.STAFF_TABLE + "." + ConfigStaff.LASTNAME + ", " +
                ConfigStaff.STAFF_TABLE + "." + ConfigStaff.MIDDLE_NAME + ", " +
                ConfigAppointment.APPOINTMENT_TABLE + "." + ConfigAppointment.DATE_OF_APPOINTMENT  + ", "  +
                ConfigAppointment.APPOINTMENT_TABLE + "." + ConfigAppointment.STATUS  +
                " FROM " + ConfigClient.CLIENT_TABLE +
                " INNER JOIN " + ConfigAppointment.APPOINTMENT_TABLE +
                " ON " + ConfigClient.CLIENT_TABLE + "." + ConfigClient.ID_CLIENT +
                " = " + ConfigAppointment.APPOINTMENT_TABLE + "." + ConfigAppointment.ID_CLIENT +
                " INNER JOIN " + ConfigStaff.STAFF_TABLE +
                " ON " + ConfigStaff.STAFF_TABLE + "." + ConfigStaff.ID_STAFF +
                " = " + ConfigAppointment.APPOINTMENT_TABLE + "." + ConfigAppointment.ID_STAFF +
                " WHERE " + ConfigClient.CLIENT_TABLE + "." + ConfigClient.LASTNAME + " = '" +
                user.getLastName() + "'";

        System.out.println(select);


        try {
            PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            ResultSet resultSet =  preparedStatement.executeQuery();

            while (resultSet.next()){
                int appointmentId = resultSet.getInt(ConfigAppointment.ID_APPOINTMENT);
                String clientLastName = resultSet.getString(ConfigClient.LASTNAME);
                String clientFirstName = resultSet.getString(ConfigClient.FIRSTNAME);
                String clientMiddleName = resultSet.getString(ConfigClient.MIDDLE_NAME);

                String staffFirstName = resultSet.getString(ConfigStaff.FIRSTNAME);
                String staffLastName = resultSet.getString(ConfigStaff.LASTNAME);
                String staffMiddleName = resultSet.getString(ConfigStaff.MIDDLE_NAME);

                Date dateOfAppointment = resultSet.getDate(ConfigAppointment.DATE_OF_APPOINTMENT);
                String status = resultSet.getString(ConfigAppointment.STATUS);

                Client tmpClient = new Client(clientLastName, clientFirstName, clientMiddleName);
                Staff tmpStaff = new Staff(staffLastName, staffFirstName, staffMiddleName);

                Appointment appointment = new Appointment(appointmentId, status, tmpStaff, dateOfAppointment, tmpClient, dateOfAppointment);
                clientAppointment.add(appointment);
            }
        }catch (SQLException  e) {
            e.printStackTrace();
        }

        return clientAppointment;
    }



}
