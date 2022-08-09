package command.executer.commands.usercommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import moduls.classes.Staff;
import repository.impl.StaffRepositoryImpl;

public class StaffDeleter extends AbstractCommandExecutor {
    @Override
    public int execute(String command) {
        return deleteStaff(command);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.DELETE_STAFF;
    }

    private int deleteStaff(String command){
        //         delete client Rodionov Ivan Vladimirovich

        var wordArray = command.split(" ");
        var lastName = wordArray[2];
        var firstName = wordArray[3];
        var middleName = wordArray[4];

        Staff staffDB = StaffRepositoryImpl.GET_STAFF_REPOSITORY_SQL().getStaff(new Staff(lastName, firstName, middleName));
        if(staffDB == null){
            System.out.println("Staff not found");
            return -1;
        }
        StaffRepositoryImpl.GET_STAFF_REPOSITORY_SQL().removeStaff(staffDB);

        return 1;
    }
}
