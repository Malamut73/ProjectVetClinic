package command.executer.commands.folderscommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import helper.Helper;
import repository.impl.NoteRepositoryImpl;

public class FolderAndNoteViewer extends AbstractCommandExecutor {
    @Override
    public int execute(String command) {
        return viewFolderAndNote(command);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.FOLDER_AND_NOTE_VIEWER;
    }

    private int viewFolderAndNote(String command){

        System.out.println("Current folder is: " + Helper.getCurrentFolder().getName());
        for (var folder :
                folderRepository.findFolderInFolder()) {
            System.out.println("Folder: " + folder.getName());
        }
        for (var note :
                NoteRepositoryImpl.GET_NOTE_REPOSITORY().findNoteInFolder()) {
            System.out.println("Note: " + note.getName());
        }

        return 1;
    }

}
