package command.executer.commands.usercommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import moduls.classes.User;

import java.util.List;

public class UserViewer extends AbstractCommandExecutor {
    @Override
    public int execute(String command) {
        return viewStaff(command);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.VIEW_STAFF;
    }

    private int viewStaff(String command) {

        String[] lines = command.split(" ");
        String role = null;
        switch (lines[1]){
            case "clients":
                role = "client";
                break;
            default:
                role = "staff";
        }

        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            System.out.printf("Any %s was found", lines[1]);
            return -1;
        }

        for (User user : users) {
            if(user.getRole().equals(role)) {
                System.out.println(user.toString());
            }
        }

        return 1;

    }



}
