package users;

import users.Appointment.Appointment;

import java.util.*;

public class Client extends User{

    private final ArrayDeque<Appointment> appointments = new ArrayDeque<>();
//    private final Set<Appointment> appointments = new HashSet<>();

    public Client(String lastName, String firstName, String middleName) {
        super(lastName, firstName, middleName);
    }

    public ArrayDeque<Appointment> getClientsAppointments(){
        return appointments;
    }
    public void printClientsAppointments(){
        for (Appointment appointment :
                appointments) {
            appointment.printInfo();
        }
    }
    public void addClientsAppointment(Appointment appointment){
        appointments.add(appointment);
    }
    public Appointment getClientsAppointment(){
        return appointments.getLast();
    }
}
