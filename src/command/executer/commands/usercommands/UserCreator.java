package command.executer.commands.usercommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import moduls.classes.User;

public class UserCreator extends AbstractCommandExecutor {

    @Override
    public int execute(String command) {
        return createStaff(command);
    }
    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_USER;
    }

    public int createStaff(String command){

        var lines = command.split(" ");
        var role = lines[1];
        var lastName = lines[2];
        var firstName = lines[3];
        var middleName = lines[4];
        var login = lines[5];
        var password = "";

        User newUser = new User(lastName, firstName, middleName);
        newUser.setLogin(login);
        newUser.setPassword(password);
        newUser.setRole(role);

        if(userRepository.findUser(newUser) != null){

            System.out.println("User already exists");

            return -1;
        }

        userRepository.saveUser(newUser);
        userRepository.saveLogAndPass(newUser);

        return 1;
    }

}
