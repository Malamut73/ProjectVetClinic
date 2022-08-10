package command.executer.commands.usercommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import moduls.classes.Staff;

public class StaffCreator extends AbstractCommandExecutor {

    @Override
    public int execute(String command) {
        return createStaff(command);
    }
    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_STAFF;
    }

    public int createStaff(String command){
// create staff Skitin Artem Mihailovich admin
        var wordArray = command.split(" ");
        var lastName = wordArray[2];
        var firstName = wordArray[3];
        var middleName = wordArray[4];
        var login = wordArray[5];
        var password = "";

        Staff newStaff = new Staff(lastName, firstName, middleName, login, password, "admin");

        if(!(staffRepository.findStaff(newStaff) == null)){

            System.out.println("Staff already exists");
            return -1;

        }else{
            staffRepository.saveStaff(newStaff);

        }

        return 1;
    }

}
