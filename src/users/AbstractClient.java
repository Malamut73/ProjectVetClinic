package users;

import Appointment.Appointment;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public abstract class AbstractClient extends User implements Serializable {

    private final Set<Appointment> appointments = new HashSet<>();
    private static int appointmentCountNumber = 1;

    public AbstractClient(String lastName, String firstName, String middleName) {
        super(lastName, firstName, middleName);
    }

    public Set<Appointment> getClientsAppointments(){
        return appointments;
    }
    public void addClientsAppointment(Appointment appointment){
        appointments.add(appointment);
    }
    public Appointment getClientsAppointment(int number){

        Appointment searchingAppointment = null;

        Optional<Appointment> appointmentCheck = findAppointment(number);
        if(!(appointmentCheck.isPresent())){
            System.out.println("Appointment not found");
        }

        for (Appointment appointment : getClientsAppointments()){
            if(appointment.getNumber() == number){
                searchingAppointment = appointment;
            }
        }
        return searchingAppointment;
    }
    public static int getAppointmentCountNumber() {
        return appointmentCountNumber;
    }
    public static void setAppointmentCountNumber(int appointmentCountNumber) {
        AbstractClient.appointmentCountNumber = appointmentCountNumber;
    }


    private Optional<Appointment> findAppointment(int number){
        for (Appointment appointment :
                getClientsAppointments()) {
            if(appointment.getNumber() == number){
                return Optional.of(appointment);

            }
        }
        return Optional.empty();
    }

}
