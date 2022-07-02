package users.Appointment;

import command.CommandType;
import command.executer.*;
import users.Client;
import users.Staff;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Appointment {

    SimpleDateFormat dateCreationFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat dateAndTimeAppointment = new SimpleDateFormat("dd.MM.yyyy hh:mm");

    private final Date date;
    private final Staff staff;
    // new appointment, in progress, canceled, waiting for payment, completed.
    private final String status;
    private final Date dateOfCreation;
    private final Client client;


    public Appointment(String appointmentType, Staff staff, String date, Client client){
        this.date = getDate(date);
        this.staff = staff;
        this.status = appointmentType;
        this.dateOfCreation = new Date();
        this.client = client;
    }

    private Date getDate(String date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        Date parsedDate = null;
        try {
            parsedDate = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parsedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(date, that.date);
    }
    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    public void printInfo(){
        System.out.println(status + " " + this.client.getFullName() + " appointment to " + this.staff.getFullName() + " " + dateAndTimeAppointment.format(date));
//        System.out.printf("%s, %s to %s to %s on %s", status, this.client.getFullName(), this.staff.getFullName(), date);
    }

}
