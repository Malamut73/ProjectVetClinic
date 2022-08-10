package command.executer.commands.usercommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import moduls.classes.Staff;

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


        if(staffRepository.findAll().isEmpty()){
            System.out.println("Any staff was found.");
        }else{
            for (Staff staff :
                    staffRepository.findAll()) {
                System.out.println(staff.toString());
            }
        }
        return 1;

    }

}
