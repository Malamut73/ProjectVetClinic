package command.executer;

import command.CommandType;

public interface CommandExecutor {

    int execute(String command);

    CommandType getCommandType();

}
