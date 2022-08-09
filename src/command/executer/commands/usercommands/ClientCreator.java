package command.executer.commands.usercommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;

import moduls.classes.Client;

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
        var login = wordArray[5];
        var password = "";

        Client newClient = new Client (lastName, firstName, middleName, login, password, "user");

        if(!(clientRepository.getClient(newClient) == null)){

            System.out.println("Client already exists");
            return -1;

        }else{
            clientRepository.saveClient(newClient);
        }
        return 1;
    }
}
