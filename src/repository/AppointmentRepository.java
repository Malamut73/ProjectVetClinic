package repository;

import moduls.classes.Appointment;
import moduls.classes.Client;

public interface AppointmentRepository {

    void saveAppointment (Appointment appointment);
//    void removeAppointment (Appointment appointment);
    void editAppointment (Appointment appointment);
        boolean getClientAppointments(Client client);
        Appointment getAppointment(Appointment appointment);



}
