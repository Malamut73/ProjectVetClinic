package command.executer.commands.userCommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import users.Staff;
import users.User;

import java.util.Optional;

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

        var wordArray = command.split(" ");
        var lastName = wordArray[2];
        var firstName = wordArray[3];
        var middleName = wordArray[4];
        var login = wordArray[5];
        var password = wordArray[6];

        var fullName = lastName + " " + firstName + " " + middleName;

        Optional<User> staffToCreate = findUser(fullName);
        if(staffToCreate.isPresent()){
            System.out.println("Staff already exists");
            return -1;
        }
        Staff newClient = new Staff(lastName, firstName, middleName, login, password);
        userRepository.save(newClient);

        System.out.println("New staff was created");

        return 1;
    }

}
