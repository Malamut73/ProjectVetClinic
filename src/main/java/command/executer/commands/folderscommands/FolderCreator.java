package command.executer.commands.folderscommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import helper.Helper;
import moduls.classes.Folder;

public class FolderCreator extends AbstractCommandExecutor {
    @Override
    public int execute(String command) {
        return createFolder(command);
    }

    @Override
    public CommandType getCommandType() {
        return null;
    }

    private int createFolder(String command){

        String[] lines = command.split(" ");
        var folderName = lines[2];
        var newFolder = new Folder(folderName, Helper.getCurrentFolder().getName());
        var folder = folderRepository.findFolder(folderName);

        if(folder != null){
            System.out.println("Folder already exist");
            return -1;
        }

        folderRepository.saveFolder(newFolder);

        return 1;
    }

}
