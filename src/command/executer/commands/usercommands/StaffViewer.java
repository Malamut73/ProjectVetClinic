package command.executer.commands.usercommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import moduls.Client;
import moduls.Staff;

public class StaffViewer extends AbstractCommandExecutor {
    @Override
    public int execute(String command) {
        return viewStaff(command);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.VIEW_STAFF;
    }

    private int viewStaff(String command) {
//
//        int countStaff = 0;
//
//        for (Staff staff :
//                staffRepository.findAll()) {
//            countStaff++;
//            System.out.println(staff.toString());
//        }
        if(!staffRepository.findAll()){
            System.out.println("Any staff was found.");
        }
        return 1;

    }

}
