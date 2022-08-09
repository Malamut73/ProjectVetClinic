package repository;

import moduls.classes.Staff;

public interface StaffRepository {

    void saveStaff (Staff staff);
    void removeStaff (Staff staff);
    void editStaff (Staff staff);
    boolean findAll();
    Staff getStaff(Staff staff);



}
