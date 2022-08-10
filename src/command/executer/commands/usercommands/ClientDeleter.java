package command.executer.commands.usercommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import moduls.classes.Client;
import repository.impl.ClientRepositoryImpl;

public class ClientDeleter extends AbstractCommandExecutor {
    @Override
    public int execute(String command) {
        return deleteClient(command);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.DELETE_CLIENT;
    }

    private int deleteClient(String command){
        //         delete client Rodionov Ivan Vladimirovich

        var wordArray = command.split(" ");
        var lastName = wordArray[2];
        var firstName = wordArray[3];
        var middleName = wordArray[4];

        Client clientDB = ClientRepositoryImpl.GET_CLIENT_REPOSITORY_SQL().findClient(new Client (lastName, firstName, middleName));
        if(clientDB == null){
            System.out.println("Client not found");
            return -1;
        }
        ClientRepositoryImpl.GET_CLIENT_REPOSITORY_SQL().removeClient(clientDB);

        return 1;
    }
}
