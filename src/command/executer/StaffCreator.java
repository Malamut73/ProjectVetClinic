package command.executer;

import command.CommandType;
import users.Staff;

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

        if(findUser(fullName).isPresent()){
            System.out.println("Client already exists");
            return -1;
        }
        var newClient = new Staff(lastName, firstName, middleName);
        userRepository.save(newClient);

        System.out.println(findUser(fullName));

        System.out.println("New client was created");

        return 1;
    }

}
