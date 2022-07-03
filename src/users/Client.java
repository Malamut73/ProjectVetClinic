package users;

import users.Appointment.Appointment;

import java.util.*;

public class Client extends User{

    private final Set<Appointment> appointments = new HashSet<>();

    public Client(String lastName, String firstName, String middleName) {
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
