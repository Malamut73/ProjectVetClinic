package repository;

import moduls.classes.Appointment;
import moduls.classes.User;

import java.util.List;

public interface AppointmentRepository {

    void saveAppointment (Appointment appointment);
    void editAppointment (Appointment appointment);
    List<Appointment> getClientAppointments(User staff);



}
