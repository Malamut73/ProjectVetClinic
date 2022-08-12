package command.executer.commands.appointmnentcommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import moduls.classes.Appointment;
import moduls.classes.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppointmentCreator extends AbstractCommandExecutor {

    @Override
    public int execute(String command) {
        return createAppointment(command);
    }
    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_APPOINTMENT;
    }

    private int createAppointment(String command){

        var wordArray = command.split(" ");
        var lastName = wordArray[2];
        var firstName = wordArray[3];
        var middleName = wordArray[4];
        var staffLastName = wordArray[6];
        var staffFirstName = wordArray[7];
        var staffMiddleName = wordArray[8];
        var dataTime = wordArray[10] + " " + wordArray[11];
        var dateOfAppointment = getDate(dataTime);
        var status = "new appointment";

        User client = userRepository.findUser(new User(lastName, firstName, middleName));
        User staff = userRepository.findUser(new User(staffLastName, staffFirstName, staffMiddleName));

        if(client == null){
            System.out.println("Client not found");
            return -1;
        }
        if(staff == null){
            System.out.println("Staff not found");
            return -1;
        }

        appointmentRepository.saveAppointment(new Appointment(dateOfAppointment, staff, status, client));

        return 1;
    }

    private Date getDate(String dataTime){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        Date parsedDate = null;
        try {
            parsedDate = simpleDateFormat.parse(dataTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parsedDate;
    }

}
