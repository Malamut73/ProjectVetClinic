package command.executer.commands.usercommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import moduls.classes.User;
import repository.impl.UserRepositoryImpl;

public class UserDeleter extends AbstractCommandExecutor {
    @Override
    public int execute(String command) {
        return deleteStaff(command);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.DELETE_USER;
    }

    private int deleteStaff(String command){

        var wordArray = command.split(" ");
        var lastName = wordArray[2];
        var firstName = wordArray[3];
        var middleName = wordArray[4];
        var userDB = UserRepositoryImpl.GET_USER_REPOSITORY_SQL().findUser(new User(lastName, firstName, middleName));

        if(userDB == null){
            System.out.printf("%s not found \n", userDB.getRole());
            return -1;
        }

        UserRepositoryImpl.GET_USER_REPOSITORY_SQL().removeUser(userDB);

        return 1;
    }
}
