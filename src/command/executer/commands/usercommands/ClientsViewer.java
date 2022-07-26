package command.executer.commands.usercommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import moduls.Client;


public class ClientsViewer extends AbstractCommandExecutor {

    @Override
    public int execute(String command) {
        return viewClients(command);
    }
    @Override
    public CommandType getCommandType() {
        return CommandType.VIEW_CLIENTS;
    }

    private int viewClients(String command){

    int countClients = 0;

        for (Client client :
                clientRepository.findAll()) {
            countClients++;
            System.out.println(client.toString());
        }
        if(countClients == 0){
            System.out.println("Any clients was found.");
        }
        return 1;
    }
}
