package command.executer;

import command.CommandType;
import users.Staff;
import users.User;

import java.util.Optional;

public class StaffCreator extends AbstractCommandExecutor{

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
        var fullName = lastName + " " + firstName + " " + middleName;

        Optional<User> staffToCreate = findUser(fullName);
        if(staffToCreate.isPresent()){
            System.out.println("Client already exists");
            return -1;
        }
        var newClient = new Staff(lastName, firstName, middleName);
        userRepository.save(newClient);

        System.out.println(findUser(fullName));

        System.out.println("New staff was created");

        return 1;
    }

}
