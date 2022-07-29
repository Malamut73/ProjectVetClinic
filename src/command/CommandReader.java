package command;

import command.executer.CommandExecutor;
import command.executer.commands.appointmnentcommands.AppointmentCreator;
import command.executer.commands.appointmnentcommands.AppointmentStatusChanger;
import command.executer.commands.appointmnentcommands.ClientAppointmentViewer;
import command.executer.commands.usercommands.*;
import helper.Helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandReader {


    private static final Map<CommandType, CommandExecutor>  COMMAND_EXECUTOR_MAP = new HashMap<>();
    static {
        COMMAND_EXECUTOR_MAP.put(CommandType.VIEW_CLIENTS, new ClientsViewer());
        COMMAND_EXECUTOR_MAP.put(CommandType.CREATE_CLIENT, new ClientCreator());
        COMMAND_EXECUTOR_MAP.put(CommandType.CREATE_STAFF, new StaffCreator());
        COMMAND_EXECUTOR_MAP.put(CommandType.VIEW_STAFF, new StaffViewer());
        COMMAND_EXECUTOR_MAP.put(CommandType.CREATE_APPOINTMENT, new AppointmentCreator());
        COMMAND_EXECUTOR_MAP.put(CommandType.VIEW_CLIENT_APPOINTMENTS, new ClientAppointmentViewer());
        COMMAND_EXECUTOR_MAP.put(CommandType.EDIT_APPOINTMENT, new AppointmentStatusChanger());
        COMMAND_EXECUTOR_MAP.put(CommandType.EDIT_CLIENT, new ClientEditor());
        COMMAND_EXECUTOR_MAP.put(CommandType.DELETE_CLIENT, new ClientDeleter());
        COMMAND_EXECUTOR_MAP.put(CommandType.DELETE_STAFF, new StaffDeleter());
//        COMMAND_EXECUTOR_MAP.put();


    }


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
            return CommandType.EDIT_CLIENT;
        }else if(commandString.contains("create appointment")){
            return CommandType.CREATE_APPOINTMENT;
        }else if(commandString.contains("change status")){
            return CommandType.EDIT_APPOINTMENT;
        }else if(commandString.contains("view client appointments")){
            return CommandType.VIEW_CLIENT_APPOINTMENTS;
        }else if(commandString.contains("view staff")){
            return CommandType.VIEW_STAFF;
        }else if(commandString.contains("delete staff")){
            return CommandType.DELETE_STAFF;
        }

        return CommandType.UNDEFINED;
    }


}
