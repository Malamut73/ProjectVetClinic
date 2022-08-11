package command.executer.commands.folderscommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import helper.Helper;
import moduls.classes.Folder;

public class MoverOnFolder extends AbstractCommandExecutor {
    @Override
    public int execute(String command) {
        return moveToFolder(command);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.MOVE_TO_OTHER_FOLDER;
    }

    private int moveToFolder(String command){

        String[] lines = command.split(" ");
        var fileName = lines[1];
        var folder = folderRepository.findFolder(fileName);

        if( folder == null){
            System.out.println("Folder not exist");
        }
        Helper.setCurrentFolder(folder);
        System.out.println("Current Folder is: " + folder.getName());


        return 1;

    }

}
