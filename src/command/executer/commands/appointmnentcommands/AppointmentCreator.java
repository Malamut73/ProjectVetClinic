package command.executer.commands.appointmnentcommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import moduls.classes.Appointment;
import moduls.classes.Client;
import moduls.classes.Staff;

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

        Client client = clientRepository.findClient(new Client(lastName, firstName, middleName));
        Staff staff = staffRepository.findStaff(new Staff(staffLastName, staffFirstName, staffMiddleName));

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

}
