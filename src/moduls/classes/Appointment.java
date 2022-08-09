package moduls.classes;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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


    public Appointment() {
    }

    public Appointment(Date dateOfAppointment, Staff staff, String status, Client client) {
        this.idAppointment = idAppointment;
        this.dateOfAppointment = dateOfAppointment;
        this.staff = staff;
        this.status = status;
        this.client = client;
    }
    public Appointment(String dateOfAppointment, Staff staff, String status, Client client) {
        this.idAppointment = idAppointment;
        this.dateOfAppointment = getDate(dateOfAppointment);
        this.staff = staff;
        this.status = status;
        this.client = client;
    }
    public Appointment(int idAppointment, String status) {
        this.idAppointment = idAppointment;
        this.status = status;
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

    public void setIdAppointment(int idAppointment) {
        this.idAppointment = idAppointment;
    }
    public void setDateOfAppointment(Date dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }
    public void setDateAndTimeAppointment(String dateAndTimeAppointment){
        this.dateOfAppointment = getDate(dateAndTimeAppointment);
    }
    public void setStaff(Staff staff) {
        this.staff = staff;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public String getStatus() {
        return status;
    }
    public int getIdAppointment() {
        return idAppointment;
    }

    public String printInfo(){
        return "Appointment №: " + idAppointment + " " + status + " " +
                client.getFullName() + " appointment to " +
                staff.getFullName() + " " + dateAndTimeAppointment.format(dateOfAppointment);
    }

    public Timestamp getSqlDate(){

        java.sql.Timestamp dateForSql = new java.sql.Timestamp(dateOfAppointment.getTime());
        return dateForSql;

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
