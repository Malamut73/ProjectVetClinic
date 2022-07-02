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

    SimpleDateFormat dateAndTimeAppointment = new SimpleDateFormat("dd.MM.yyyy hh:mm");

    private static final Map<String, String>  COMMAND_STATUS = Map.of(
            "create appointment", "New appointment",
            "in progress", "In progress"

    );

    private final Date date;
    private final Staff staff;
    private String status;
    private final Date dateOfCreation;
    private final Client client;


    public Appointment(String appointmentType, Staff staff, String date, Client client){
        this.date = getDate(date);
        this.staff = staff;
        this.status = determinateStatus(appointmentType);
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
    public void printInfo(){
        System.out.println(status + " " + this.client.getFullName() + " appointment to " + this.staff.getFullName() + " " + dateAndTimeAppointment.format(date));
    }
    private String determinateStatus(String string){
        if(string.contains("create appointment")){
            return COMMAND_STATUS.get(string);
        }else if(string.contains("in progress")){
            return COMMAND_STATUS.get(string);
        }
        return null;
    }

    public void setStatus(String status) {
        this.status = determinateStatus(status);
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



}
