package command.executer.commands.usercommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import moduls.classes.Client;

import java.util.List;


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

        List<Client> clients = clientRepository.findAll();
        if(clients.isEmpty()){
            System.out.println("Any clients was found");
            return -1;
        }else{
            for (Client client :
                    clients) {
                System.out.println(client.toString());
            }
        }
        return 1;
    }
}
