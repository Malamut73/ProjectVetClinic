package command.executer.commands.userCommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import users.Client;
import users.User;

import java.util.Optional;

public class ClientCreator extends AbstractCommandExecutor {
    @Override
    public int execute(String command) {
        return createClient(command);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_CLIENT;
    }

    private int createClient(String command){

        var wordArray = command.split(" ");
        var lastName = wordArray[2];
        var firstName = wordArray[3];
        var middleName = wordArray[4];
        var fullName = lastName + " " + firstName + " " + middleName;

        Optional<User> clientToCreate = findUser(fullName);
        if(clientToCreate.isPresent()){
            System.out.println("Client already exists");
            return -1;
        }
        var newClient = new Client(lastName, firstName, middleName);
        userRepository.save(newClient);

        System.out.println("New client was created");

        return 1;

    }

}
