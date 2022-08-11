package command.executer.commands.usercommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import moduls.classes.User;
import repository.impl.UserRepositoryImpl;

public class UserEditor extends AbstractCommandExecutor {
    @Override
    public int execute(String command) {
        return editUser(command);
    }
    @Override
    public CommandType getCommandType() {
        return CommandType.EDIT_USER;
    }

    private int editUser(String command) {

        var wordArray = command.split(" ");
        var lastName = wordArray[2];
        var firstName = wordArray[3];
        var middleName = wordArray[4];
        var newLastName = wordArray[6];
        var newFirstName = wordArray[7];
        var newMiddleName = wordArray[8];
        var user = UserRepositoryImpl.GET_USER_REPOSITORY_SQL().findUser(new User(lastName, firstName, middleName));

        if (user == null) {
            System.out.println("User not found");
            return -1;
        }

        user.setLastName(newLastName);
        user.setFirstName(newFirstName);
        user.setMiddleName(newMiddleName);

        UserRepositoryImpl.GET_USER_REPOSITORY_SQL().editUser(user);

        System.out.println("User was edited");

        return 1;
    }
}
