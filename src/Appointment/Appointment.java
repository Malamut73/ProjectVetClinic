package Appointment;

import users.Client;
import users.Staff;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Appointment implements Serializable {

    SimpleDateFormat dateAndTimeAppointment = new SimpleDateFormat("dd.MM.yyyy hh:mm");

    private final int number;
    private final Date date;
    private final Staff staff;
    private String status;
    private final Date dateOfCreation;
    private final Client client;


    public Appointment(String appointmentType, Staff staff, String date, Client client){
        this.date = getDate(date);
        this.staff = staff;
        this.status = appointmentType;
        this.dateOfCreation = new Date();
        this.client = client;

        this.number = Client.getAppointmentCountNumber();
        Client.setAppointmentCountNumber(this.number + 1);
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
        System.out.println("Appointment №: " + number + " " + status + " " + this.client.getFullName() + " appointment to " + this.staff.getFullName() + " " + dateAndTimeAppointment.format(date));
    }


    public int getNumber(){
        return this.number;
    }
    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return number == that.number;
    }
    @Override
    public int hashCode() {
        return Objects.hash(number);
    }






}
