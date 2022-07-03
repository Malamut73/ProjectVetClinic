package command.executer;

import command.CommandType;
import serialization.ObjectWriter;


public class Exister extends AbstractCommandExecutor{
    @Override
    public int execute(String command) {
        return existCommand();
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.EXIT;
    }

    private int existCommand(){

        ObjectWriter.writeObject();

        return 0;
    }
}
