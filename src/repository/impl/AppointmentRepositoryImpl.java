package repository.impl;

import connector.Connector;
import moduls.classes.Appointment;
import moduls.classes.Client;
import moduls.classes.Staff;
import repository.AppointmentRepository;
import repository.config.ConfigAppointments;


import java.sql.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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

        String insert = "INSERT INTO " + ConfigAppointments.APPOINTMENT_TABLE + "( " +
                ConfigAppointments.DATE_OF_APPOINTMENT + ", " +
                ConfigAppointments.STAFF_ID + ", " +
                ConfigAppointments.CLIENT_ID + ", " +
                ConfigAppointments.STATUS + ")" +
                " VALUES(?, ?, ?, ?)";


        try{PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(insert);
            preparedStatement.setTimestamp(1, appointment.getSqlDate());
            preparedStatement.setInt(2, appointment.getStaff().getUserId());
            preparedStatement.setInt(3, appointment.getClient().getUserId());
            preparedStatement.setString(4, appointment.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("Appointment was created");
    }

    @Override
    public void editAppointment(Appointment appointment) {
                String select = " UPDATE " +
                ConfigAppointments.APPOINTMENT_TABLE +
                " SET " +
                ConfigAppointments.APPOINTMENT_TABLE + "." + ConfigAppointments.STATUS + " = ?" +
                " WHERE " +
                ConfigAppointments.APPOINTMENT_TABLE + "." + ConfigAppointments.ID_APPOINTMENT + " =?";
//        System.out.println(select);

        try {PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            preparedStatement.setString(1, appointment.getStatus());
            preparedStatement.setInt(2, appointment.getIdAppointment());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Appointment was change");
    }

    @Override
    public List<Appointment> getClientAppointments(Client client) {

        List<Appointment> clientAppointments = new LinkedList<>();
        Staff staff;
        Appointment appointment = null;
        ResultSet resultSet;

        String select = "SELECT * FROM " + ConfigAppointments.APPOINTMENT_TABLE +
                " WHERE " +
                ConfigAppointments.APPOINTMENT_TABLE + "." + ConfigAppointments.CLIENT_ID + " =?";

//                ConfigAppointments.APPOINTMENT_TABLE + "." + ConfigAppointments.ID_APPOINTMENT + ", "  +
//                ConfigUsers.USERS_TABLE + "." + ConfigUsers.LASTNAME + ", " +
//                ConfigUsers.USERS_TABLE + "." + ConfigUsers.FIRSTNAME + ", " +
//                ConfigUsers.USERS_TABLE + "." + ConfigUsers.MIDDLE_NAME + ", " +
//                ConfigAppointments.APPOINTMENT_TABLE + "." + ConfigAppointments.DATE_OF_APPOINTMENT  + ", "  +
//                ConfigAppointments.APPOINTMENT_TABLE + "." + ConfigAppointments.STATUS + ", "  +
//                ConfigAppointments.APPOINTMENT_TABLE + "." + ConfigAppointments.DATE_OF_CREATION +
//                " FROM " + ConfigUsers.USERS_TABLE +
//                " INNER JOIN " + ConfigAppointments.APPOINTMENT_TABLE +
//                " ON " + ConfigUsers.USERS_TABLE + "." + ConfigUsers.ID_USER +
//                " = " + ConfigAppointments.APPOINTMENT_TABLE + "." + ConfigAppointments.CLIENT_ID +
//                " WHERE " + ConfigUsers.USERS_TABLE + "." + ConfigUsers.LASTNAME + " =? AND " +
//                ConfigUsers.USERS_TABLE + "." + ConfigUsers.FIRSTNAME + " =? AND " +
//                ConfigUsers.USERS_TABLE + "." + ConfigUsers.MIDDLE_NAME + " =?";

        try{PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            preparedStatement.setInt(1, client.getUserId());
//            preparedStatement.setString(2, client.getFirstName());
//            preparedStatement.setString(3, client.getMiddleName());
            resultSet = preparedStatement.executeQuery();

            while  (resultSet.next()){
                int idAppointment = resultSet.getInt(ConfigAppointments.ID_APPOINTMENT);
//                String lastName = resultSet.getString(ConfigUsers.LASTNAME);
//                String firstName = resultSet.getString(ConfigUsers.FIRSTNAME);
//                String middleName = resultSet.getString(ConfigUsers.MIDDLE_NAME);
                Date dateOfAppointment = resultSet.getDate(ConfigAppointments.DATE_OF_APPOINTMENT);
                String status = resultSet.getString(ConfigAppointments.STATUS);
                Date dateOfregistration = resultSet.getDate(ConfigAppointments.DATE_OF_CREATION);
                int staffId = resultSet.getInt(ConfigAppointments.STAFF_ID);

                staff = StaffRepositoryImpl.GET_STAFF_REPOSITORY_SQL().findStaff(staffId);
                if(staff == null){
                    System.out.println("Any appointments was found");
                }else{
                    appointment = new Appointment(idAppointment, status, staff, dateOfAppointment, client, dateOfregistration);
                    clientAppointments.add(appointment);
                }

//                System.out.println(appointment.printInfo());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return clientAppointments;
    }

//    @Override
//    public Appointment getAppointment(Appointment appointment) {
//
//        ResultSet resultSet = null;
//        Staff staff = null;
//
//        String select = "SELECT " +
//                ConfigAppointments.APPOINTMENT_TABLE + "." + ConfigAppointments.ID_APPOINTMENT + ", "  +
//                ConfigUsers.USERS_TABLE + "." + ConfigUsers.LASTNAME + ", " +
//                ConfigUsers.USERS_TABLE + "." + ConfigUsers.FIRSTNAME + ", " +
//                ConfigUsers.USERS_TABLE + "." + ConfigUsers.MIDDLE_NAME + ", " +
//                ConfigAppointments.APPOINTMENT_TABLE + "." + ConfigAppointments.DATE_OF_APPOINTMENT  + ", "  +
//                ConfigAppointments.APPOINTMENT_TABLE + "." + ConfigAppointments.STATUS + ", "  +
//                ConfigAppointments.APPOINTMENT_TABLE + "." + ConfigAppointments.STATUS +
//                " FROM " + ConfigUsers.USERS_TABLE +
//                " INNER JOIN " + ConfigAppointments.APPOINTMENT_TABLE +
//                " ON " + ConfigUsers.USERS_TABLE + "." + ConfigUsers.ID_USER +
//                " = " + ConfigAppointments.APPOINTMENT_TABLE + "." + ConfigAppointments.CLIENT_ID +
//                " WHERE " + ConfigAppointments.APPOINTMENT_TABLE + "." + ConfigAppointments.ID_APPOINTMENT + " =?";
//
//        try{PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
//            preparedStatement.setInt(1, appointment.getIdAppointment());
//            resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()){
//                int idAppointment = resultSet.getInt(ConfigAppointments.ID_APPOINTMENT);
//                String lastName = resultSet.getString(ConfigUsers.LASTNAME);
//                String firstName = resultSet.getString(ConfigUsers.FIRSTNAME);
//                String middleName = resultSet.getString(ConfigUsers.MIDDLE_NAME);
//                Date dateOfAppointment = resultSet.getDate(ConfigAppointments.DATE_OF_APPOINTMENT);
//                String status = resultSet.getString(ConfigAppointments.STATUS);
//                Date dateOfregistration = resultSet.getDate(ConfigAppointments.DATE_OF_CREATION);
//
//                staff = StaffRepositoryImpl.GET_STAFF_REPOSITORY_SQL().findStaff(new Staff(lastName, firstName, middleName));
//                appointment = new Appointment(idAppointment, status, staff, dateOfAppointment, appointment.getClient(), dateOfregistration);
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//
//        return appointment;
//    }

}
