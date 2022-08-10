package command.executer.commands.usercommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;

import moduls.classes.Client;

import repository.impl.ClientRepositoryImpl;


public class ClientEditor extends AbstractCommandExecutor {

    @Override
    public int execute(String command) {
        return editClient(command);
    }
    @Override
    public CommandType getCommandType() {
        return CommandType.EDIT_CLIENT;
    }

    private int editClient(String command) {
        var wordArray = command.split(" ");
        var lastName = wordArray[2];
        var firstName = wordArray[3];
        var middleName = wordArray[4];

        var newLastName = wordArray[6];
        var newFirstName = wordArray[7];
        var newMiddleName = wordArray[8];

        Client clientDB = ClientRepositoryImpl.GET_CLIENT_REPOSITORY_SQL().findClient(new Client (lastName, firstName, middleName));
        Client newClient = null;

        if(clientDB == null){
            System.out.println("None not found");
            return -1;
        }else{
            newClient = new Client (newLastName, newFirstName, newMiddleName);
            newClient.setUserId(clientDB.getUserId());
            ClientRepositoryImpl.GET_CLIENT_REPOSITORY_SQL().editClient(newClient);
        }
//        ClientRepositoryImpl.GET_CLIENT_REPOSITORY_SQL().editClient(
//                new Client (clientDB.getUserId(), newLastName, newFirstName, newMiddleName, clientDB.getLogin(), clientDB.getPassword(), clientDB.getRole()));



        return 1;

    }

}
