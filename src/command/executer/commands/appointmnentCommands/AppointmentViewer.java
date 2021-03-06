package command.executer.commands.appointmnentCommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import Appointment.Appointment;
import users.Client;
import users.User;

import java.util.Optional;

public class AppointmentViewer extends AbstractCommandExecutor {

    @Override
    public int execute(String command) {
        return viewAppointments(command);
    }
    @Override
    public CommandType getCommandType() {
        return CommandType.VIEW_APPOINTMENTS;
    }

    private int viewAppointments(String command){

        var wordArray = command.split(" ");
        var lastName = wordArray[2];
        var firstName = wordArray[3];
        var middleName = wordArray[4];
        var fullName = lastName + " " + firstName + " " + middleName;

        Optional<User> clientCheck = findUser(fullName);
        if(!(clientCheck.isPresent())){
            System.out.println("Client not found");
            return -1;
        }
        Client lookingUserToCreateAppointment = (Client) clientCheck.get();
//        for (User user :
//                userRepository.findAll()) {
//            if (user.getFullName().equals(fullName)) {
//                lookingUserToCreateAppointment = (Client)user;
//            }
//        }

        for (Appointment str : lookingUserToCreateAppointment.getClientsAppointments()
        ) {
            str.printInfo();
        }

        return 1;
    }
}
