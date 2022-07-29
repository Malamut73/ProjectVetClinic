package helper;

import moduls.Staff;

public class Helper {

    private static final Helper HELPER = new Helper();

    private Helper() {
    }

    private static Staff staff;

    public static Helper getHELPER() {
        return HELPER;
    }

    private static boolean FIRST_ENTER;
    private static String ROLE ;


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

    public static Staff getStaff() {
        if(staff == null){
            staff = new Staff();
        }
        return staff;
    }

    public static void setStaff(Staff staff) {
        Helper.staff = staff;
    }
}
