package command.executer.commands.notescommand;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import moduls.classes.Note;
import moduls.classes.Staff;
import repository.impl.FolderRepositoryImpl;
import repository.impl.StaffRepositoryImpl;

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

        List<Staff> staffs = StaffRepositoryImpl.GET_STAFF_REPOSITORY_SQL().findAll();

        if(staffs.isEmpty()){
            System.out.println("Any staff was found");
            return -1;
        }else{
            for (Staff staff :
                    staffs) {
                List<Note> notes = noteRepository.findNotes(staff);
                if (notes.isEmpty()) {
                    System.out.println("Any notes was found");
                    return -1;
                } else {
                    for (Note note :
                            notes) {
                        System.out.println(note.description());
                        for (String path :
                                FolderRepositoryImpl.GET_FOLDER_REPOSITORY().findFolderPath(note.getParentFolderName())) {
                            System.out.print(path + "/");
                        }
                        System.out.println("\n--------------------");
                    }
                    System.out.println("--------------------");
                }
            }
        }
        return 1;
    }
}
