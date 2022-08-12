package command.executer.commands.notescommand;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import moduls.classes.Note;
import moduls.classes.User;
import repository.impl.FolderRepositoryImpl;
import repository.impl.UserRepositoryImpl;

import java.util.List;

public class NotesViewer extends AbstractCommandExecutor {
    @Override
    public int execute(String command) {
        return viewAllNotes();
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.FIND_All_USERS_NOTE_WITH_PATH;
    }

    private int viewAllNotes(){

        List<User> users = UserRepositoryImpl.GET_USER_REPOSITORY_SQL().findAll();

        if(users.isEmpty()){
            System.out.println("Any staff was found");
            return -1;
        }

        findNotes(users);

        return 1;
    }

    private int findNotes(List<User> users){

        for (User user : users) {

            List<Note> notes = noteRepository.findNotes(user);

            if (notes.isEmpty()) {
                System.out.println("Any notes was found");
                return -1;
            }

            printNotesPath(notes);
        }
        return 1;
    }

    private int printNotesPath(List<Note> notes){

        for (Note note : notes) {
            System.out.println(note.description());
            for (String path : FolderRepositoryImpl.GET_FOLDER_REPOSITORY().findFolderPath(note.getParentFolderName())) {
                System.out.print(path + "/");
            }
            System.out.println("\n--------------------");
        }
        System.out.println("--------------------");

        return 1;
    }
}
