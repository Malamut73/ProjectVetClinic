package command.executer.commands.usercommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;


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

        if(!clientRepository.findAll()){
            System.out.println("Any clients was found.");
        }
        return 1;
    }
}
