package helper;

import moduls.classes.Folder;
import moduls.classes.User;

public class Helper {

    private static final Helper HELPER = new Helper();

    private static User user;
    private static boolean FIRST_ENTER;
    private static Folder parentFolder = new Folder("root", "null");
    private static Folder currentFolder = getParentFolder();

    private Helper() {
    }

    public static void setParentFolder(Folder parentFolder) {
        Helper.parentFolder = parentFolder;
    }
    public static Helper getHELPER() {
        return HELPER;
    }
    public static User getUser() {
        return user;
    }
    public static void setUser(User user) {
        Helper.user = user;
    }
    public boolean isFirstEnter() {
        return FIRST_ENTER;
    }
    public void setFirstEnter(boolean value) {
    FIRST_ENTER = value;
    }
    public static void setCurrentFolder(Folder currentFolder) {
        Helper.currentFolder = currentFolder;
    }
    public static Folder getParentFolder() {
        return parentFolder;
    }
    public static Folder getCurrentFolder() {
        return currentFolder;
    }
}
