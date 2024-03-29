package command;

import command.executer.CommandExecutor;
import command.executer.commands.appointmnentcommands.AppointmentCreator;
import command.executer.commands.appointmnentcommands.AppointmentStatusChanger;
import command.executer.commands.appointmnentcommands.ClientAppointmentViewer;
import command.executer.commands.folderscommands.*;
import command.executer.commands.notescommand.NotesViewer;
import command.executer.commands.notescommand.NoteCreator;
import command.executer.commands.notescommand.NotesInFolderViewer;
import command.executer.commands.usercommands.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandReader {


    private static final Map<CommandType, CommandExecutor>  COMMAND_EXECUTOR_MAP = new HashMap<>();

    static {
        COMMAND_EXECUTOR_MAP.put(CommandType.CREATE_USER, new UserCreator());
        COMMAND_EXECUTOR_MAP.put(CommandType.VIEW_USERS, new UserViewer());
        COMMAND_EXECUTOR_MAP.put(CommandType.CREATE_APPOINTMENT, new AppointmentCreator());
        COMMAND_EXECUTOR_MAP.put(CommandType.VIEW_CLIENT_APPOINTMENTS, new ClientAppointmentViewer());
        COMMAND_EXECUTOR_MAP.put(CommandType.EDIT_APPOINTMENT, new AppointmentStatusChanger());
        COMMAND_EXECUTOR_MAP.put(CommandType.DELETE_USER, new UserDeleter());
        COMMAND_EXECUTOR_MAP.put(CommandType.CREATE_FOLDER, new FolderCreator());
        COMMAND_EXECUTOR_MAP.put(CommandType.CREATE_NOTE, new NoteCreator());
        COMMAND_EXECUTOR_MAP.put(CommandType.FOLDER_AND_NOTE_VIEWER, new FolderAndNoteViewer());
        COMMAND_EXECUTOR_MAP.put(CommandType.MOVE_TO_OTHER_FOLDER, new MoverOnFolder());
        COMMAND_EXECUTOR_MAP.put(CommandType.MOVE_FOLDER_UP, new MoverUpFolder());
        COMMAND_EXECUTOR_MAP.put(CommandType.FIND_All_USERS_NOTE_WITH_PATH, new NotesViewer());
        COMMAND_EXECUTOR_MAP.put(CommandType.FIND_USERS_NOTE_WITH_PATH, new NotesInFolderViewer());
        COMMAND_EXECUTOR_MAP.put(CommandType.EDIT_USER, new UserEditor());
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
        var commandType = getCommandType(commandString);

        if(COMMAND_EXECUTOR_MAP.containsKey(commandType)){

            var commandExecutor = COMMAND_EXECUTOR_MAP.get(commandType);

            return commandExecutor.execute(commandString);

        }else{
            System.out.println("Incorrect command");
        }

        return -1;
    }

    private static CommandType getCommandType(String commandString){

        if(commandString.contains("edit user")){
            return CommandType.EDIT_USER;
        }else if(commandString.contains("create appointment")){
            return CommandType.CREATE_APPOINTMENT;
        }else if(commandString.contains("change status")){
            return CommandType.EDIT_APPOINTMENT;
        }else if(commandString.contains("view client appointments")){
            return CommandType.VIEW_CLIENT_APPOINTMENTS;
        }else if(commandString.contains("create folder")){
            return CommandType.CREATE_FOLDER;
        }else if(commandString.contains("create note")){
            return CommandType.CREATE_NOTE;
        }else if(commandString.contains("ls")){
            return CommandType.FOLDER_AND_NOTE_VIEWER;
        }else if(commandString.contains("cd..")){
            return CommandType.MOVE_FOLDER_UP;
        }else if(commandString.contains("cd")){
            return CommandType.MOVE_TO_OTHER_FOLDER;
        }else if(commandString.contains("view all notes")){
            return CommandType.FIND_All_USERS_NOTE_WITH_PATH;
        }else if(commandString.contains("view notes in")){
            return CommandType.FIND_USERS_NOTE_WITH_PATH;
        }else if(commandString.contains("view")) {
            return CommandType.VIEW_USERS;
        }else if(commandString.contains("create")) {
            return CommandType.CREATE_USER;
        }else if(commandString.contains("delete user")){
            return CommandType.DELETE_USER;
        }

        return CommandType.UNDEFINED;
    }


}
