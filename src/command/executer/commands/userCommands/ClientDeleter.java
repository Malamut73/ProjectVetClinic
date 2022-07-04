package command.executer.commands.userCommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import users.User;

import java.util.Optional;

public class ClientDeleter extends AbstractCommandExecutor {

    @Override
    public int execute(String command) {
        return deleteClient(command);
    }
    @Override
    public CommandType getCommandType() {
        return CommandType.DELETE_CLIENT;
    }

    public int deleteClient(String command){

        var wordArray = command.split(" ");
        var lastName = wordArray[2];
        var firstName = wordArray[3];
        var middleName = wordArray[4];
        var fullName = lastName + " " + firstName + " " + middleName;

        Optional<User> userToRemove = findUser(fullName);
        if(userToRemove.isPresent()){
            userRepository.remove(userToRemove.get());
            System.out.println("Client was deleted");
        }else{
            System.out.println("Client not found");
        }

        return 1;
    }

}
