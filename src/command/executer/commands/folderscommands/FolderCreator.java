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
    // create folder folderName
    private int createFolder(String command){

        String[] lines = command.split(" ");
        String folderName = lines[2];
//        String parentFolderName = lines[3];

        var folder = folderRepository.findFolder(folderName);
        if(folder != null){
            System.out.println("Folder already exist");
            return -1;
        }
//        var parentFolder = folderRepository.findFolder(parentFolderName);
//        if(parentFolder == null){
//            System.out.println("Parent folder not exist");
//            return -1;
//        }

        Folder newFolder = new Folder(folderName, Helper.getCurrentFolder().getName());
        folderRepository.saveFolder(newFolder);


        return 1;
    }

}
