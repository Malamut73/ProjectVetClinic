package command.executer.commands.appointmnentCommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import users.Client;
import users.User;

import java.util.Optional;

public class AppointmentEditor extends AbstractCommandExecutor {

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

        var wordArray = command.split(" ");
        var lastName = wordArray[3];
        var firstName = wordArray[4];
        var middleName = wordArray[5];
        var fullName = lastName + " " + firstName + " " + middleName;
        int number = Integer.parseInt(wordArray[2]);

        StringBuilder nameOfStatus = new StringBuilder();
        for (int i = 7; i < wordArray.length; i++) {
            nameOfStatus.append(wordArray[i] + " ");
        }

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

        client.getClientsAppointment(number).setStatus(nameOfStatus.toString().trim());
        System.out.println("Appointment was change");


        return 1;
    }
}
