package command.executer.commands.notescommand;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import moduls.classes.Folder;
import repository.impl.FolderRepositoryImpl;

public class UsersNotesViewer extends AbstractCommandExecutor {
    @Override
    public int execute(String command) {
        return viewUsersNotesInFolder(command);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.FIND_USERS_NOTE_WITH_PATH;
    }

    private int viewUsersNotesInFolder(String command){

        String[] lines = command.split(" ");
        String folderName = lines[3];

        Folder folder = FolderRepositoryImpl.GET_FOLDER_REPOSITORY().findFolder(folderName);
        if(folder == null){
            System.out.println("Folder not found");
            return -1;
        }

        if(noteRepository.findAllUserNotesInFolder(folder)){
            System.out.println("Notes not found");
            return -1;
        }

        return 1;
    }

}
