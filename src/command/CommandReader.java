package command;

import command.executer.*;
import command.executer.commands.*;
import command.executer.commands.appointmnentCommands.AppointmentCreator;
import command.executer.commands.appointmnentCommands.AppointmentEditor;
import command.executer.commands.appointmnentCommands.AppointmentViewer;
import command.executer.commands.userCommands.*;

import java.util.Map;
import java.util.Scanner;

public class CommandReader {
    private static final Map<CommandType, CommandExecutor>  COMMAND_EXECUTOR_MAP = Map.of(
            CommandType.CREATE_CLIENT, new ClientCreator(),
            CommandType.CREATE_STAFF, new StaffCreator(),
            CommandType.CREATE_APPOINTMENT, new AppointmentCreator(),
            CommandType.EDIT_APPOINTMENT, new AppointmentEditor(),
            CommandType.EDIT_FULL_NAME, new FullNameEditor(),
            CommandType.VIEW_CLIENTS, new ClientsView(),
            CommandType.VIEW_APPOINTMENTS, new AppointmentViewer(),
            CommandType.DELETE_CLIENT, new ClientDeleter(),
            CommandType.EXIT, new Exister()
    );

    public static void startReading(){
        Scanner scanner = new Scanner(System.in);

        int i = 1;
        while (i != 0){
            i = readCommand(scanner);
        }
        scanner.close();
    }
    private static int readCommand(Scanner scanner){
        var commandString = scanner.nextLine();
        CommandType commandType = getCommandType(commandString);

        if(COMMAND_EXECUTOR_MAP.containsKey(commandType)){
            var commandExecutor = COMMAND_EXECUTOR_MAP.get(commandType);
            return commandExecutor.execute(commandString);
        }else{
            System.out.println("Incorrect command");
        }
// else if(commandType == CommandType.EXIT){
//            return 0;
//        }
        return -1;
    }
    private static CommandType getCommandType(String commandString){
        if(commandString.contains("create client")){
            return CommandType.CREATE_CLIENT;
        }else if(commandString.contains("view clients")){
            return CommandType.VIEW_CLIENTS;
        }else if(commandString.contains("create staff")){
            return CommandType.CREATE_STAFF;
        }else if(commandString.contains("delete client")) {
            return CommandType.DELETE_CLIENT;
        }else if(commandString.contains("edit client")){
            return CommandType.EDIT_FULL_NAME;
        }else if(commandString.contains("create appointment")){
            return CommandType.CREATE_APPOINTMENT;
        }else if(commandString.contains("change status")){
            return CommandType.EDIT_APPOINTMENT;
        }else if(commandString.contains("view appointments")){
            return CommandType.VIEW_APPOINTMENTS;
        }else if(commandString.contains("exit")){
            return CommandType.EXIT;
        }

        return CommandType.UNDEFINED;
    }


}
