package command.executer.commands.notescommand;

import command.CommandType;
import command.executer.AbstractCommandExecutor;

public class AllNotesViewer extends AbstractCommandExecutor {
    @Override
    public int execute(String command) {
        return viewAllNotes();
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.FIND_All_USERS_NOTE_WITH_PATH;
    }

    private int viewAllNotes(){
        noteRepository.findAllNotesAllUsers();
        return 1;
    }

}
