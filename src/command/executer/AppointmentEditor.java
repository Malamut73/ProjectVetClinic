package command.executer;

import command.CommandType;
import users.Appointment.Appointment;
import users.Client;
import users.User;

import java.util.Optional;

public class AppointmentEditor extends AbstractCommandExecutor{
    @Override
    public int execute(String command) {
        return editAppointment(command);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_APPOINTMENT;
    }

    private int editAppointment(String command){
        Client client = null;
        // change status Rodionov Ivan Vladimirovich to in progress

        var wordArray = command.split(" ");
        var lastName = wordArray[2];
        var firstName = wordArray[3];
        var middleName = wordArray[4];
        var fullName = lastName + " " + firstName + " " + middleName;

        StringBuilder nameOfStatus = new StringBuilder();
        for (int i = 6; i < wordArray.length; i++) {
            nameOfStatus.append(wordArray[i] + " ");
        }

        
//        String commandToStatus = wordArray[6] + " " + wordArray[7];


        Optional<User> clientCheck = findUser(fullName);
        if(!(clientCheck.isPresent())){
            System.out.println("Client not found");
            return -1;
        }

        for (User user :
                userRepository.findAll()) {
            if (user.getFullName().equals(fullName)) {
                client = (Client)user;
            }
        }

        client.getClientsAppointment().setStatus(nameOfStatus.toString().trim());
        System.out.println("Appointment was change");
        client.printClientsAppointments();


        return 1;
    }
}
