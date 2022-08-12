package moduls.classes;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
public class Appointment {

    SimpleDateFormat dateAndTimeAppointment = new SimpleDateFormat("dd.MM.yyyy hh:mm");
    SimpleDateFormat dataTimeForSql = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    private int idAppointment;
    private Date dateOfAppointment;
    private User staff;
    private String status;
    private Date dateOfCreation;
    private User client;

    public Appointment() {
    }
    public Appointment(Date dateOfAppointment, User staff, String status, User client) {
        this.dateOfAppointment = dateOfAppointment;
        this.staff = staff;
        this.status = status;
        this.client = client;
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
