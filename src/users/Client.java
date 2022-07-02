package users;

import users.Appointment.Appointment;

import java.util.HashSet;
import java.util.Set;

public class Client extends User{

    private final Set<Appointment> appointments = new HashSet<>();

    public Client(String lastName, String firstName, String middleName) {
        super(lastName, firstName, middleName);
    }

    public Set<Appointment> getAppointments(){
        return appointments;
    }

    public void printAppointments(){
        for (Appointment appointment :
                appointments) {
            appointment.printInfo();
        }
    }

}
