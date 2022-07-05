package command.executer.commands.appointmnentCommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import Appointment.Appointment;
import users.Client;
import users.Staff;
import users.User;

import java.util.Optional;

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

        var staffFullName = staffLastName + " " + staffFirstName + " " + staffMiddleName;
        var fullName = lastName + " " + firstName + " " + middleName;
        String dateOfAppointment = wordArray[10] + " " + wordArray[11];
        String commandToStatus = "new appointment";

        Optional<User> clientCheck = findUser(fullName);
        if(clientCheck.isEmpty()){
            System.out.println("Client not found");
            return -1;
        }
        Client lookingUserToCreateAppointment = (Client)clientCheck.get();

        Optional<User> staffCheck = findUser(staffFullName);
        if(staffCheck.isEmpty()){
            System.out.println("Staff not found");
            return -1;
        }
        Staff staffForAppointment = (Staff)staffCheck.get();

//        for (User user :
//                userRepository.findAll()) {
//            if (user.getFullName().equals(fullName)) {
//                lookingUserToCreateAppointment = (Client)user;
//            }
//        }
//        for (User user :
//                userRepository.findAll()) {
//            if(user.getFullName().equals(staffFullName)){
//                staffForAppointment
//            }
//        }

        Appointment appointment = new Appointment(commandToStatus, staffForAppointment, dateOfAppointment, lookingUserToCreateAppointment);
        lookingUserToCreateAppointment.addClientsAppointment(appointment);

        System.out.println("Appointment was created");

        return 1;
    }

}
