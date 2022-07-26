package command.executer.commands.appointmnentcommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import moduls.Appointment;
import moduls.Client;
import moduls.Staff;

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

        String dateOfAppointment = wordArray[10] + " " + wordArray[11];

        String status = "new appointment";

        Client newClient = new Client (lastName, firstName, middleName);
        Client client = clientRepository.getClient(new Client(lastName, firstName, middleName));
        Staff staff = staffRepository.getStaff(new Staff(staffLastName, staffFirstName, staffMiddleName));
//        Staff newStaff = new Staff(lastName, firstName, middleName);

        if(client == null){
            System.out.println("Client not found");
            return -1;
        }
        if(staff == null){
            System.out.println("Staff not found");
            return -1;
        }


        Appointment appointment = new Appointment(dateOfAppointment, staff, status, client);
        String str = " ";
        appointmentRepository.saveAppointment(appointment);

        return 1;
    }

}
