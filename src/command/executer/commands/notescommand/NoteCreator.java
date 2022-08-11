package command.executer.commands.notescommand;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import helper.Helper;
import moduls.classes.Note;

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

        var nameOfNote = lines[2];
        var noteTextSb = new StringBuilder();
            for (int i = 3; i < lines.length; i++) {
            noteTextSb.append(lines[i] + " ");
        }
        var noteText = noteTextSb.toString();
        var otherNote = noteRepository.findNote(nameOfNote);
        var newNote = new Note(nameOfNote, noteText, Helper.getUser().getUserId(), Helper.getCurrentFolder().getName());

        if(!(otherNote == null)){
            System.out.println("Note with this name already exist");
            return -1;
        }

        noteRepository.saveNote(newNote);

        return 1;
    }
}
