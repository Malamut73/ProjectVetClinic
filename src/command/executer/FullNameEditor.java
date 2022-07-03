package command.executer;

import command.CommandType;
import users.Client;
import users.User;

import java.util.Optional;

public class FullNameEditor extends AbstractCommandExecutor{

    @Override
    public int execute(String command) {
        return editFullName(command);
    }
    @Override
    public CommandType getCommandType() {
        return CommandType.EDIT_FULL_NAME;
    }

    private int editFullName(String command) {
        var wordArray = command.split(" ");
        var lastName = wordArray[2];
        var firstName = wordArray[3];
        var middleName = wordArray[4];
        var fullName = lastName + " " + firstName + " " + middleName;

        var newLastName = wordArray[6];
        var newFirstName = wordArray[7];
        var newMiddleName = wordArray[8];
        var newFullName = newLastName + " " + newFirstName + " " + newMiddleName;

        Optional<User> clientToEdit = findUser(fullName);
        if(!(clientToEdit.isPresent())){
            System.out.println("Client not find");
            return -1;
        }

        Client lookingUserToEdit = new Client(lastName, firstName, middleName);
        for (User user :
                userRepository.findAll()) {
            if (user.equals(lookingUserToEdit)) {
                user.setFullName(newFullName);
                System.out.println("Client was edited");
            }
        }
        return 1;

    }

}
