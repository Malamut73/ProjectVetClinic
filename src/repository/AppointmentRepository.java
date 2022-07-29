package repository;

import moduls.Appointment;
import moduls.Client;
import moduls.User;

import java.util.Set;

public interface AppointmentRepository {

    void saveAppointment (Appointment appointment);
//    void removeAppointment (Appointment appointment);
    void editAppointment (Appointment appointment);
        boolean getClientAppointments(Client client);
        Appointment getAppointment(Appointment appointment);



}
