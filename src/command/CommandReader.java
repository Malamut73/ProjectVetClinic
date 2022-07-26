package command;

import command.executer.CommandExecutor;
import command.executer.commands.appointmnentcommands.AppointmentCreator;
import command.executer.commands.appointmnentcommands.AppointmentStatusChanger;
import command.executer.commands.appointmnentcommands.AppointmentViewer;
import command.executer.commands.appointmnentcommands.ClientAppointmentViewer;
import command.executer.commands.usercommands.*;

import java.util.Map;
import java.util.Scanner;

public class CommandReader {

    private static final Map<CommandType, CommandExecutor>  COMMAND_EXECUTOR_MAP = Map.of(
            CommandType.VIEW_CLIENTS, new ClientsViewer(),
            CommandType.CREATE_CLIENT, new ClientCreator(),
            CommandType.CREATE_STAFF, new StaffCreator(),
            CommandType.VIEW_STAFF, new StaffViewer(),
            CommandType.CREATE_APPOINTMENT, new AppointmentCreator(),
            CommandType.VIEW_ALL_APPOINTMENTS, new AppointmentViewer(),
            CommandType.VIEW_CLIENT_APPOINTMENTS, new ClientAppointmentViewer(),
            CommandType.EDIT_APPOINTMENT, new AppointmentStatusChanger(),
            CommandType.EDIT_CLIENT, new ClientEditor()

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
        }else if(commandString.contains("view appointments")){
            return CommandType.VIEW_ALL_APPOINTMENTS;
        }else if(commandString.contains("view client appointments")){
            return CommandType.VIEW_CLIENT_APPOINTMENTS;
        }else if(commandString.contains("view staff")){
            return CommandType.VIEW_STAFF;
        }

        return CommandType.UNDEFINED;
    }


}
