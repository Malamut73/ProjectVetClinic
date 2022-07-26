package moduls;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Appointment {

    SimpleDateFormat dateAndTimeAppointment = new SimpleDateFormat("dd.MM.yyyy hh:mm");
    SimpleDateFormat dataTimeForSql = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    private int idAppointment;
    private Date dateOfAppointment;
    private Staff staff;
    private String status;
    private Date dateOfCreation;
    private Client client;

    public Appointment(String dateOfAppointment, Staff staff, String status, Client client) {
        this.idAppointment = idAppointment;
        this.dateOfAppointment = getDate(dateOfAppointment);
        this.staff = staff;
        this.status = status;
        this.client = client;
    }


    public Appointment(int idAppointment, String appointmentType, Staff Staff, Date dateOfAppointment, Client Client, Date dateOfCreation){
        this.dateOfAppointment = dateOfAppointment;
        this.staff = Staff;
        this.status = appointmentType;
        this.client = Client;
        this.idAppointment = idAppointment;
        this.dateOfCreation = dateOfCreation;
    }




    public Staff getStaff() {
        return staff;
    }

    public Client getClient() {
        return client;
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
    public String printInfo(){
        return "Appointment №: " + idAppointment + " " + status + " " +
                client.getFullName() + " appointment to " +
                staff.getFullName() + " " + dateAndTimeAppointment.format(dateOfAppointment);
    }


    public java.sql.Timestamp getSqlDate(){

        java.sql.Timestamp dateForSql = new java.sql.Timestamp(dateOfAppointment.getTime());
        return dateForSql;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return idAppointment == that.idAppointment;
    }
    @Override
    public int hashCode() {
        return Objects.hash(idAppointment);
    }






}
