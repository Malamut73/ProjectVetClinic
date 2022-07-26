package repository;

import moduls.Appointment;
import moduls.User;

import java.util.Set;

public interface AppointmentRepository {

    void saveAppointment (Appointment appointment);
    void removeAppointment (Appointment appointment);
    void editAppointment (Appointment appointment);
    Set<Appointment> findAll();
    Set<Appointment> getAppointment(User user);



}
