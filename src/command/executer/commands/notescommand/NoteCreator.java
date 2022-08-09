package command.executer.commands.notescommand;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import helper.Helper;
import moduls.classes.Folder;
import moduls.classes.Note;
import repository.impl.FolderRepositoryImpl;

import java.util.Optional;

public class NoteCreator extends AbstractCommandExecutor {
    @Override
    public int execute(String command) {
        return createNote(command);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_NOTE;
    }

    private int createNote(String command){

        String[] lines = command.split(" ");

        String nameOfNote = lines[2];
//        String nameOfFolder = lines[3];

        StringBuilder noteTextSb = new StringBuilder();
        for (int i = 3; i < lines.length; i++) {
            noteTextSb.append(lines[i] + " ");
        }
        var noteText = noteTextSb.toString();

//        Folder folder = FolderRepositoryImpl.GET_FOLDER_REPOSITORY().findFolder(nameOfFolder);
//        if(folder == null){
//            System.out.println("Folder not found");
//            return -1;
//        }
        Note otherNote = noteRepository.findNote(nameOfNote);
        if(!(otherNote == null)){
            System.out.println("Note with this name already exist");
            return -1;
        }
        Note newNote = new Note(nameOfNote, noteText, Helper.getStaff().getUserId(), Helper.getCurrentFolder().getName());
        noteRepository.saveNote(newNote);

        return 1;
    }
}
