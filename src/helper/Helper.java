package helper;

import moduls.classes.Folder;
import moduls.classes.Staff;

public class Helper {

    private static final Helper HELPER = new Helper();

    private static Staff staff;
    private static boolean FIRST_ENTER;
    private static String ROLE ;
    private static Folder parentFolder = new Folder("root", "null");
    private static Folder currentFolder = getParentFolder();
    private static String email = null;

    private Helper() {
    }

    public static void setParentFolder(Folder parentFolder) {
        Helper.parentFolder = parentFolder;
    }
    public static String getEmail() {
        return email;
    }
    public static void setEmail(String email) {
        Helper.email = email;
    }
    public static Helper getHELPER() {
        return HELPER;
    }
    public static Staff getStaff() {
//        if(staff == null){
//            staff = new Staff();
//        }
        return staff;
    }
    public static void setStaff(Staff staff) {
        Helper.staff = staff;
    }
    public boolean isFirstEnter() {
        return FIRST_ENTER;
    }
    public void setFirstEnter(boolean b) {
    FIRST_ENTER = b;
    }
    public String getROLE() {
        return ROLE;
    }
    public void setROLE(String str) {
        ROLE = str;

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
