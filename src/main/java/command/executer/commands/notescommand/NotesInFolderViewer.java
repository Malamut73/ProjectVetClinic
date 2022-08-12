package command.executer.commands.notescommand;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import helper.Helper;
import moduls.classes.Note;
import repository.impl.FolderRepositoryImpl;

import java.util.LinkedList;
import java.util.List;

public class NotesInFolderViewer extends AbstractCommandExecutor {
    @Override
    public int execute(String command) {
        return viewUsersNotesInFolder(command);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.FIND_USERS_NOTE_WITH_PATH;
    }

    private int viewUsersNotesInFolder(String command) {

        String[] lines = command.split(" ");
        var folderName = lines[3];
        var staff = Helper.getUser();
        var findFolder = FolderRepositoryImpl.GET_FOLDER_REPOSITORY().findFolder(folderName);
        var notes = noteRepository.findNotes(staff);


        if (findFolder == null) {
            System.out.println("Folder not found");
            return -1;
        }
        if (notes.isEmpty()) {
            System.out.println("Any notes was found");
            return -1;
        }

        viewNotes(folderName, notes);

        return 1;
    }

    private void viewNotes(String folderName, List<Note> notes){

        List<Note> notesInFolder = new LinkedList<>();

        for (Note note : notes) {
            if (note.getParentFolderName().equals(folderName)) {
                notesInFolder.add(note);
            }
            if(notesInFolder.isEmpty()){
                System.out.println("Any notes in current folder not found");
                break;
            }

            System.out.println(note.description());
            printDescription(folderName);
        }
    }

    private void printDescription (String folderName){

        for (String path : folderRepository.findFolderPath(folderName)) {
            System.out.print(path + "/");
        }
        System.out.println("\n--------------------");
    }

}
