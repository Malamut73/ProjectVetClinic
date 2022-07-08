package command.executer.commands.userCommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import users.Client;
import users.User;

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

        for (User user :
                userRepository.findAll()) {
            if(user instanceof Client){
                System.out.println(user);
                countClients++;
            }
        }
        if(countClients == 0){
            System.out.println("Any clients was found.");
        }
        return 1;
    }
}
