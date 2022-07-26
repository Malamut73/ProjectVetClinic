package command.executer.commands.appointmnentcommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import moduls.*;

import java.util.Optional;

public class AppointmentViewer extends AbstractCommandExecutor {

    @Override
    public int execute(String command) {
        return viewAppointments(command);
    }
    @Override
    public CommandType getCommandType() {
        return CommandType.VIEW_ALL_APPOINTMENTS;
    }

    private int viewAppointments(String command){

        for (Appointment appointment :
                appointmentRepository.findAll()) {
            System.out.println(appointment.printInfo());
        }

//        var wordArray = command.split(" ");
//        var lastName = wordArray[2];
//        var firstName = wordArray[3];
//        var middleName = wordArray[4];
//        var fullName = lastName + " " + firstName + " " + middleName;
//
//        int countClients = 0;
//
//        for (Appointment appointment :
//                appointmentRepository.findAll()) {
//            countClients++;
//            System.out.println(appointment.toString());
//        }
//        if(countClients == 0){
//            System.out.println("Any appointments was found.");
//        }
        return 1;
    }
}
