package command.executer.commands.folderscommands;

import command.CommandType;
import command.executer.AbstractCommandExecutor;
import helper.Helper;
import moduls.classes.Folder;

public class MoverUpFolder extends AbstractCommandExecutor {
    @Override
    public int execute(String command) {
        return moveBack();
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.MOVE_FOLDER_UP;
    }


    private int moveBack(){
        Folder parentFolder;
        Folder folder = folderRepository.findFolder(Helper.getCurrentFolder().getName());
        if(folder == null){
            System.out.println("Current Folder is: " + Helper.getCurrentFolder().getName());
        return 1;
        }
//        if(folder.getParentFolder().getName().equals("root")){
//            Helper.setCurrentFolder(Helper.getParentFolder());
//        }else{
            parentFolder = folderRepository.findFolder(folder.getParentFolderName());
        if(parentFolder == null){
            Helper.setCurrentFolder(Helper.getParentFolder());
        }else{
            Helper.setCurrentFolder(parentFolder);
        }

        System.out.println("Current Folder is: " + Helper.getCurrentFolder().getName());

        return 1;

    }

}
