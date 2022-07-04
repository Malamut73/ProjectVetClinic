package command.executer.commands.userCommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import users.Client;
import users.User;

public class ClientsView extends AbstractCommandExecutor {

    @Override
    public int execute(String command) {
        return viewClients(command);
    }
    @Override
    public CommandType getCommandType() {
        return CommandType.VIEW_CLIENTS;
    }

    private int viewClients(String command){
        for (User user :
                userRepository.findAll()) {
            if(user instanceof Client){
                System.out.println(user);
            }
        }
        return 1;
    }
}
