package command.executer.commands.notescommand;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import helper.Helper;
import moduls.classes.Folder;
import moduls.classes.Note;
import moduls.classes.Staff;
import repository.impl.FolderRepositoryImpl;

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
        String folderName = lines[3];

        Staff staff = Helper.getStaff();
        Folder folder = FolderRepositoryImpl.GET_FOLDER_REPOSITORY().findFolder(folderName);

        if (folder == null) {
            System.out.println("Folder not found");
            return -1;
        }else{
            List<Note> notes = noteRepository.findNotes(staff);
            if (notes.isEmpty()) {
                System.out.println("Any notes was found");
                return -1;
            } else {
                for (Note note :
                        notes) {
                    if (note.getParentFolderName().equals(folderName)) {
                        System.out.println(note.description());
                        for (String path :
                                folderRepository.findFolderPath(folderName)) {
                            System.out.print(path + "/");
                        }
                        System.out.println("\n--------------------");
                    }
                    System.out.println("--------------------");
                }
            }
            return 1;
        }
    }
}
