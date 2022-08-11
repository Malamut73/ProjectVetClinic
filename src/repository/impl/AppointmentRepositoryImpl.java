package repository.impl;

import connector.Connector;
import moduls.classes.Appointment;
import moduls.classes.User;
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
    public List<Appointment> getClientAppointments(User client) {

        List<Appointment> clientAppointments = new LinkedList<>();
        User staff;
        Appointment appointment = null;
        ResultSet resultSet;

        String select = "SELECT * FROM " + ConfigAppointments.APPOINTMENT_TABLE +
                " WHERE " +
                ConfigAppointments.APPOINTMENT_TABLE + "." + ConfigAppointments.CLIENT_ID + " =?";

        try{PreparedStatement preparedStatement = Connector.getConnection().prepareStatement(select);
            preparedStatement.setInt(1, client.getUserId());

            resultSet = preparedStatement.executeQuery();

            while  (resultSet.next()){
                int idAppointment = resultSet.getInt(ConfigAppointments.ID_APPOINTMENT);
                Date dateOfAppointment = resultSet.getDate(ConfigAppointments.DATE_OF_APPOINTMENT);
                String status = resultSet.getString(ConfigAppointments.STATUS);
                Date dateOfregistration = resultSet.getDate(ConfigAppointments.DATE_OF_CREATION);
                int staffId = resultSet.getInt(ConfigAppointments.STAFF_ID);

                staff = UserRepositoryImpl.GET_USER_REPOSITORY_SQL().findUser(staffId);
                if(staff == null){
                    System.out.println("Staff not found");
                }else{
                    appointment = new Appointment(dateOfAppointment, staff, status, client);
                    appointment.setIdAppointment(idAppointment);
                    clientAppointments.add(appointment);
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return clientAppointments;
    }

}
